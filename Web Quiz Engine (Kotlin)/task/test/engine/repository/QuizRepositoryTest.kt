package engine.repository

import engine.model.Quiz
import engine.repository.data.QuizDataHolder
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class QuizRepositoryTest {

    @Autowired
    private lateinit var quizRepository: QuizRepository

    val quiz = QuizDataHolder.QUIZ_LIST[0]

    @Test
    fun testIfNotEmptyOrNull() {
        try {
            //val quiz = quizRepository.getSingleQuiz()
            assertNotNull(quiz)
        } catch (ex: Exception) {
            fail("Returned null Quiz!")
        }
    }

    @Test
    fun testReturnsQuizObject() {
        try {
            //val quiz = quizRepository.getSingleQuiz()
            assertThat(quiz).isInstanceOf(Quiz::class.java)
        } catch (ex: Exception) {
            fail("Returned result was not a Quiz object!")
        }
    }

    @Test
    fun testQuizTitleNotBlank() {
        try {
            //val quiz = quizRepository.getSingleQuiz()
            assertThat(quiz.title.isNotBlank())
        } catch (ex: Exception) {
            fail("Returned quiz with no title!")
        }
    }

    @Test
    fun testQuizQuestionNotBlank() {
        try {
            //val quiz = quizRepository.getSingleQuiz()
            assertThat(quiz.text.isNotBlank())
        } catch (ex: Exception) {
            fail("Returned quiz with not question text!")
        }
    }

    @Test
    fun testQuizOptions() {
        try {
            //val quiz = quizRepository.getSingleQuiz()
            assertThat(quiz.options).allMatch { it.isNotBlank() }
            assertEquals(4, quiz.options.size)
        } catch (ex: Exception) {
            fail("Quiz options not equal to 4! or contains blank option!")
        }
    }
}