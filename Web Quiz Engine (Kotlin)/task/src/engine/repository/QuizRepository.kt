package engine.repository

import engine.model.Quiz.Quiz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

/**
 *  Jpa Repository class allows us to use property expressions to interact with database for persistence and retrieval.
 *
 *  @author Omar Osman
 */
@Repository
interface QuizRepository : JpaRepository<Quiz, Int>