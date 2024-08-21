package engine.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *  A model class representing a single Quiz answer.
 *
 *  @author Omar Osman
 *  @param answer array of indexes of all chosen options as the answer.
 */
data class QuizAnswer(
    @JsonProperty("answer")
    val answer: Set<Int> = emptySet()
)
