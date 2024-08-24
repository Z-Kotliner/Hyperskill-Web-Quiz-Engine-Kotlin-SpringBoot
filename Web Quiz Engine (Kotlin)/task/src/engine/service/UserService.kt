package engine.service

import engine.exception.UserLoginRequestException
import engine.model.User.User
import engine.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.*

/**
 *  Service class implements UserDetailsService that is used to get user data from the database.
 *
 *  @author Omar Osman
 *  @param userRepository User repository that provides us with CRUD functions.
 */
@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun registerUser(user: User) {
        // Check if the user with the specified email is already registered or not
        if (userRepository.existsByEmail(user.email)) throw UserLoginRequestException("User with the same email already registered!")

        // Encode password
        user.password = passwordEncoder.encode(user.password)

        // Save user to db
        userRepository.save(user)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(email = username)
            ?: throw UserLoginRequestException("No user with username: $username")

        return User(
            user.email,
            user.password,
            true,
            true,
            true,
            true,
            mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
        )
    }
}