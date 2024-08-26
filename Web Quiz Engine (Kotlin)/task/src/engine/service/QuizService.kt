package engine.service

import engine.dto.MyMapper
import engine.dto.QuizCompletionDTO
import engine.dto.QuizDTO
import engine.exception.QuizDeletionRequestException
import engine.exception.QuizNotFoundException
import engine.exception.QuizProcessingException
import engine.exception.UserAuthenticationException
import engine.model.Quiz.Quiz
import engine.model.Quiz.QuizAnswer
import engine.model.Quiz.QuizCompletion
import engine.model.Quiz.QuizResult
import engine.repository.QuizCompletionRepository
import engine.repository.QuizRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 *  Service class that accepts, processes and forwards requests and responses to and from controller and/or repository.
 *
 *  @author Omar Osman
 *  @param quizRepository Quiz repository that provides us with CRUD functions.
 *  @param quizCompletionRepository Quiz Completion repository
 *  @param myMapper Model to DTO mapper class.
 */
@Service
class QuizService(
    private val quizRepository: QuizRepository,
    private val quizCompletionRepository: QuizCompletionRepository,
    private val myMapper: MyMapper
) {

    fun getQuiz(): Quiz {
        return quizRepository.findByIdOrNull(1) ?: throw QuizProcessingException("No Quiz found!")
    }

    fun solveQuiz(id: Int, qAnswer: QuizAnswer?, auth: Authentication): QuizResult {
        val quiz = quizRepository.findById(id).orElse(null) ?: throw QuizNotFoundException("Quiz Not found. Wrong Id.")

        return if (qAnswer != null && quiz.answer == qAnswer.answer) {
            // Save submission in the Completions table
            val completion = QuizCompletion(null, id, LocalDateTime.now(), auth.name)
            quizCompletionRepository.save(completion)

            // Respond with success message
            QuizResult(success = true, feedback = "Congratulations, you're right!")

        } else {
            QuizResult(success = false, feedback = "Wrong answer! Please, try again.")
        }
    }

    fun crateQuiz(quiz: Quiz, auth: Authentication): QuizDTO {
        quiz.author = auth.name
        val rQuiz = quizRepository.save(quiz) ?: throw QuizProcessingException("Error Creating Quiz!")

        return myMapper.convertQuizToDTO(rQuiz)
    }

    fun getQuiz(id: Int): QuizDTO {
        val quiz = quizRepository.findById(id).orElseThrow { QuizNotFoundException("Quiz Not found. Wrong Id.") }

        return myMapper.convertQuizToDTO(quiz)
    }

    fun getPagedQuizzes(page: Int, auth: Authentication): Page<QuizDTO> {
        // If the user is not authenticated return 401 ( UNAUTHORIZED)
        if (!auth.isAuthenticated) throw UserAuthenticationException("Not Authenticated! Please login first.")

        // Return 10 quizzes at once
        val pageRequest = PageRequest.of(page, 10)

        return quizRepository.findAll(pageRequest).map(myMapper::convertQuizToDTO)
    }

    fun getPagedCompletedQuizzes(page: Int, auth: Authentication): Page<QuizCompletionDTO> {
        // If the user is not authenticated return 401 (UNAUTHORIZED)
        if (!auth.isAuthenticated) throw UserAuthenticationException("Not Authenticated! Please login first.")

        // Return 10 quizzes at once sorted by completion time in descending order
        val pageRequest = PageRequest.of(page, 10, Sort.by("completed_at").descending())

        return quizCompletionRepository.findByUsername(user = auth.name, pageRequest)
            .map(myMapper::convertQuizCompletionToDTO)
    }

    fun deleteQuiz(id: Int, auth: Authentication): ResponseEntity<Quiz?> {
        // Throw 404 (NOT FOUND) if quiz with same id does not exist
        val quiz = quizRepository.findById(id).orElseThrow { QuizNotFoundException("Quiz Not found. Wrong Id.") }

        // Throw 403 (FORBIDDEN) if user is not authorized to delete
        if (quiz.author != auth.name) throw QuizDeletionRequestException("Can not delete Quiz!")

        // Delete specified quiz
        quizRepository.deleteById(id)

        // Respond with 204 (NO CONTENT) status for successful operation
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}