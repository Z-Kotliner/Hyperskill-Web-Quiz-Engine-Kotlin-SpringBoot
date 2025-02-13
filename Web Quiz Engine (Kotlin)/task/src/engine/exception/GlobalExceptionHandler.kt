package engine.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

/**
 *  Global exception handler class
 */
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(QuizProcessingException::class)
    fun handleQuizProcessingException(
        ex: QuizProcessingException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["error"] = ex.message.toString()

        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(QuizNotFoundException::class)
    fun handleQuizNotFoundException(
        ex: QuizNotFoundException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["error"] = ex.message.toString()

        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UserAuthenticationException::class)
    fun handleUserAuthenticationException(
        ex: UserAuthenticationException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["error"] = ex.message.toString()

        return ResponseEntity(body, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(UserLoginRequestException::class)
    fun handleUserLoginRequestException(
        ex: UserLoginRequestException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["error"] = ex.message.toString()

        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(QuizDeletionRequestException::class)
    fun handleQuizDeleteRequestException(
        ex: QuizDeletionRequestException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["error"] = ex.message.toString()

        return ResponseEntity(body, HttpStatus.FORBIDDEN)
    }
}