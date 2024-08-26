package engine.repository

import engine.model.Quiz.QuizCompletion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Quiz Completion repository which manages the CRUD operations of the QuizCompletion entity.
 */
interface QuizCompletionRepository : JpaRepository<QuizCompletion, Int> {
    @Query("SELECT * FROM tbl_completion WHERE username= :user", nativeQuery = true)
    fun findByUsername(user: String, pageRequest: Pageable): Page<QuizCompletion>
}