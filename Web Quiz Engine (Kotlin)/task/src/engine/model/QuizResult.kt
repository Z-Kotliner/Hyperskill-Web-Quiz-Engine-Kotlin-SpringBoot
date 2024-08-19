package engine.model

/**
 * A model class representing a Quiz Result with success value true indicating correct answer.
 *
 *  @author Omar Osman
 *
 *  @param success the correctness of the quiz answer
 *  @param feedback additional result message
 */

data class QuizResult(val success: Boolean, val feedback: String)
