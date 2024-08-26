package engine.repository

import engine.model.User.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


/**
 *  CRUD Repository class allows us to use property expressions to interact with user database table.
 *
 *  @author Omar Osman
 */
@Repository
interface UserRepository : CrudRepository<User, Int> {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String?) : User?
}