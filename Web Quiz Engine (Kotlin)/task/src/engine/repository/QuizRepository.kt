package engine.repository

import engine.model.Quiz
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

    fun createQuiz(quiz: Quiz) : Boolean  = QuizDataHolder.QUIZ_LIST.add(quiz)

    fun getQuizById(id: Int): Quiz? = QuizDataHolder.QUIZ_LIST.find { it.id == id }

}