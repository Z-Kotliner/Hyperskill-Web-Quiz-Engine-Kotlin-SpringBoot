package engine.model.Quiz

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

/**
 *  A QuizCompletion Entity class representing a Quiz completions by user with timestamp.
 *
 *  @author Omar Osman
 *
 *  @param id unique auto generated id
 *  @param quizId the id of completed Quiz
 *  @param completedAt the timestamp the quiz was completed
 *  @param username the username who completed the quiz
 */
@Entity
@Table(name = "tbl_completion")
data class QuizCompletion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @NotNull
    val quizId: Int,

    @NotNull
    val completedAt: LocalDateTime,

    @NotNull
    val username: String,
)
