package engine.controller

import engine.model.User.User
import engine.service.UserService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *  The user login controller class that intercepts incoming user requests and forwards for authentication
 *
 *  @author Omar Osman
 *  @property userService User login processing service.
 */
@RestController
@Validated
@RequestMapping("/api")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/register")
    fun registerNewUser(@RequestBody @Valid user: User) {
        userService.registerUser(user)
    }
}