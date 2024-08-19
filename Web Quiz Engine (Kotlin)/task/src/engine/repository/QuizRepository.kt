package engine.repository

import engine.repository.data.QuizDataHolder
import org.springframework.stereotype.Repository

/**
 *  Repository class that interacts with data sources for persistence and retrieval.
 *  Later we can adopt it to handle database operations
 */
@Repository
class QuizRepository {

    fun getSingleQuiz() = QuizDataHolder.QUIZ_LIST[0]

    fun getAllQuizzes() = QuizDataHolder.QUIZ_LIST
}