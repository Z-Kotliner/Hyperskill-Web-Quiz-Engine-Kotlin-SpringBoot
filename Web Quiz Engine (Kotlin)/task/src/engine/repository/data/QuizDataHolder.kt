package engine.repository.data

import engine.model.Quiz
import java.util.*

/**
 *  A file which takes the place of data source and holds Quiz Sample data
 */

object QuizDataHolder {
     val QUIZ_LIST : MutableList<Quiz> = Collections.synchronizedList(ArrayList())

    init {
        QUIZ_LIST.add(
            Quiz(
                title = "The Java Logo",
                text = "What is depicted on the Java logo?",
                options = listOf("Robot", "Tea leaf", "Cup of coffee", "Bug")
            )
        )

        QUIZ_LIST.add(
            Quiz(
                title = "The Kotlin Logo",
                text = "What is depicted on the Kotlin logo?",
                options = listOf("Island", "Mark Typeface", "Russia", "none")
            )
        )
    }
}