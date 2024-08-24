package engine.model.User

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

/**
 *  A User Entity class representing a single User with email and password credentials.
 *
 *  @author Omar Osman
 *
 *  @param email an email address of the form "<username>@<domain>.<extension>"
 *  @param password a password string with at least 5 characters

 */
@Entity
@Table(name = "tb_user")
data class User(
    @Id
    @field:NotNull
    @field:Email(
        regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
        flags = [Pattern.Flag.CASE_INSENSITIVE],
        message = "Invalid email!"
    )
    @JsonProperty("email")
    val email: String,

    @field:NotNull
    @field:Size(min = 5, message = "Password must be at least 5 characters long!")
    @JsonProperty("password")
    var password: String
)
