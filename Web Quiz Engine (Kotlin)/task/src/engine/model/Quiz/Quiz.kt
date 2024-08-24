package engine.model.Quiz

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

/**
 *  A Quiz Entity class representing a single Quiz that will be mapped to our db table.
 *
 *  @author Omar Osman
 *
 *  @param id unique auto generated id
 *  @param title the title of the Quiz
 *  @param text the question of the Quiz
 *  @param options the multiple choice of answers
 *  @param answer the correct answer
 */

@Entity
@Table(name= "tb_quiz")
data class Quiz(
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int,

    @NotBlank
    @JsonProperty("title")
    val title: String,

    @NotBlank
    @JsonProperty("text")
    val text: String,

    @NotNull
    @Size(min = 2)
    @JsonProperty("options")
    val options: List<String>,

    @JsonProperty("answer")
    val answer: Set<Int> = emptySet(),

    var author: String?
)
