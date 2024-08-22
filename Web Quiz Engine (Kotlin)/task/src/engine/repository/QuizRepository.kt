package engine.repository

import engine.model.Quiz
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 *  CRUD Repository class allows us to use property expressions to interact with database for persistence and retrieval.
 *
 *  @author Omar Osman
 */
@Repository
interface QuizRepository : CrudRepository<Quiz, Int>