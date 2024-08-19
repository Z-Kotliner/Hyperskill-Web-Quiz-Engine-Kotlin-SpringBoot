package engine.service

import engine.exception.QuizProcessingException
import engine.model.Quiz
import engine.model.QuizResult
import engine.repository.QuizRepository
import org.springframework.stereotype.Service

/**
 *  Service class that accepts, processes and forwards requests and responses to and from controller and/or repository.
 *
 *  @author Omar Osman
 *  @param quizRepository Quiz repository that interacts with data sources.
 */
@Service
class QuizService(private val quizRepository: QuizRepository) {

    fun getQuiz(): Quiz {
        return quizRepository.getSingleQuiz()
    }

    fun solveQuiz(answer: Int): QuizResult {
        //if (answer.isEmpty() || answer.toIntOrNull() != null) throw QuizProcessingException("Bad Request Input!")

        return if (answer == 2) {
            QuizResult(success = true, feedback = "Congratulations, you're right!")
        } else {
            QuizResult(success = false, feedback = "Wrong answer! Please, try again.")
        }
    }
}