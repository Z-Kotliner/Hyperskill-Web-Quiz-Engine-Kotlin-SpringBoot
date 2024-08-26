package engine.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp
import java.time.LocalDateTime

/**
 *  DTO representation for Quiz Completion entity
 *
 *  @author Omar Osman
 *
 *  @param quizId completed quiz id
 *  @param completedAt the timestamp the quiz was completed
 */
class QuizCompletionDTO(
    @JsonProperty("id")
    val quizId: Int,

    @JsonProperty("completedAt")
    val completedAt: LocalDateTime
)