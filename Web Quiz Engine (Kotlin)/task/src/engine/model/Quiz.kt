package engine.model

/**
 *  A model class representing a single Quiz with title, question and answer choices.
 *
 *  @author Omar Osman
 *
 *  @param title the title of the Quiz
 *  @param text the question of the Quiz
 *  @param options the multiple choice of answers
 */

data class Quiz(
    val title: String,
    val text: String,
    val options: List<String>
)
