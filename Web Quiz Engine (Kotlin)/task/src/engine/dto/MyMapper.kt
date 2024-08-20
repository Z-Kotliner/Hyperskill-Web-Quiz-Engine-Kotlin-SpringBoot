package engine.dto

import engine.model.Quiz
import org.springframework.stereotype.Component

/**
 * A mapper which maps entities to DTOs
 */

@Component
class MyMapper {
    fun convertQuizToDTO(quiz: Quiz): QuizDTO {
        return QuizDTO(id = quiz.id, title = quiz.title, text = quiz.text, options = quiz.options)
    }
}