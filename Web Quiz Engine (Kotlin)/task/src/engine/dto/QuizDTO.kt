package engine.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *   DTO representation for Quiz entity
 *
 *  @author Omar Osman
 *
 *  @param id unique auto generated id
 *  @param title the title of the Quiz
 *  @param text the question of the Quiz
 *  @param options the multiple choice of answers
 */

class QuizDTO (
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("text")
    val text: String,
    @JsonProperty("options")
    val options: List<String>,
)