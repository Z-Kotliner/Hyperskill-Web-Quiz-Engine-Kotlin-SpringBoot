package engine.service

import engine.dto.MyMapper
import engine.dto.QuizDTO
import engine.exception.QuizDeletionRequestException
import engine.exception.QuizNotFoundException
import engine.exception.QuizProcessingException
import engine.model.Quiz.Quiz
import engine.model.Quiz.QuizAnswer
import engine.model.Quiz.QuizResult
import engine.repository.QuizRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 *  Service class that accepts, processes and forwards requests and responses to and from controller and/or repository.
 *
 *  @author Omar Osman
 *  @param quizRepository Quiz repository that provides us with CRUD functions.
 *  @param myMapper Model to DTO mapper class.
 */
@Service
class QuizService(private val quizRepository: QuizRepository, private val myMapper: MyMapper) {

    fun getQuiz(): Quiz {
        return quizRepository.findByIdOrNull(1) ?: throw QuizProcessingException("No Quiz found!")
    }

    fun solveQuiz(id: Int, qAnswer: QuizAnswer?): QuizResult {
        val quiz = quizRepository.findById(id).orElse(null) ?: throw QuizNotFoundException("Quiz Not found. Wrong Id.")

        return if (qAnswer != null && quiz.answer == qAnswer.answer) {
            QuizResult(success = true, feedback = "Congratulations, you're right!")
        } else {
            QuizResult(success = false, feedback = "Wrong answer! Please, try again.")
        }
    }

    fun crateQuiz(quiz: Quiz, username: String): QuizDTO {
        quiz.author = username
        val rQuiz = quizRepository.save(quiz) ?: throw QuizProcessingException("Error Creating Quiz!")

        return myMapper.convertQuizToDTO(rQuiz)
    }

    fun getQuiz(id: Int): QuizDTO {
        val quiz = quizRepository.findById(id).orElseThrow { QuizNotFoundException("Quiz Not found. Wrong Id.") }

        return myMapper.convertQuizToDTO(quiz)
    }

    fun getAllQuizzes(): List<QuizDTO> {
        return quizRepository.findAll().map(myMapper::convertQuizToDTO)
    }

    fun deleteQuiz(id: Int, username: String): ResponseEntity<Quiz?> {
        // Throw 404 (NOT FOUND) if quiz with same id does not exist
        val quiz = quizRepository.findById(id).orElseThrow { QuizNotFoundException("Quiz Not found. Wrong Id.") }

        // Throw 403 (FORBIDDEN) if user is not authorized to delete
        if (quiz.author != username) throw QuizDeletionRequestException("Can not delete Quiz!")

        // Delete specified quiz
        quizRepository.deleteById(id)

        // Respond with 204 (NO CONTENT) status for successful operation
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}