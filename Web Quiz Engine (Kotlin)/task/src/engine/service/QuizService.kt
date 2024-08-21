package engine.service

import engine.dto.MyMapper
import engine.dto.QuizDTO
import engine.exception.QuizNotFoundException
import engine.exception.QuizProcessingException
import engine.model.Quiz
import engine.model.QuizAnswer
import engine.model.QuizResult
import engine.repository.QuizRepository
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

/**
 *  Service class that accepts, processes and forwards requests and responses to and from controller and/or repository.
 *
 *  @author Omar Osman
 *  @param quizRepository Quiz repository that interacts with data sources.
 *  @param myMapper Model to DTO mapper class.
 */
@Service
class QuizService(private val quizRepository: QuizRepository, private val myMapper: MyMapper) {
    private val idGenerator = AtomicInteger()

    fun getQuiz(): Quiz {
        return quizRepository.getSingleQuiz()
    }

    fun solveQuiz(id: Int, qAnswer: QuizAnswer?): QuizResult {
        val quiz = quizRepository.getQuizById(id) ?: throw QuizNotFoundException("Quiz Not found. Wrong Id.")

        return if (qAnswer != null && quiz.answer == qAnswer.answer) {
            QuizResult(success = true, feedback = "Congratulations, you're right!")
        } else {
            QuizResult(success = false, feedback = "Wrong answer! Please, try again.")
        }
    }

    fun crateQuiz(quiz: Quiz): QuizDTO {
        // Generate unique integer for id
        quiz.id = idGenerator.incrementAndGet()

        if (quizRepository.createQuiz(quiz)) {
            return myMapper.convertQuizToDTO(quiz)
        } else throw QuizProcessingException("Error Creating Quiz!")
    }

    fun getQuiz(id: Int): QuizDTO {
        val quiz = quizRepository.getQuizById(id)
        if (quiz != null) return myMapper.convertQuizToDTO(quiz)

        throw QuizNotFoundException("Quiz Not found. Wrong Id.")
    }

    fun getAllQuizzes(): List<QuizDTO> {
        return quizRepository.getAllQuizzes().map(myMapper::convertQuizToDTO)
    }
}