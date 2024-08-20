package engine.repository.data

import engine.model.Quiz
import java.util.*

/**
 *  A file which takes the place of data source and holds Quiz Sample data
 */

object QuizDataHolder {
     val QUIZ_LIST : MutableList<Quiz> = Collections.synchronizedList(ArrayList())
    
}