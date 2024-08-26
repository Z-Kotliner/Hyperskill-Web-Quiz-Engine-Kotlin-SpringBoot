package engine.controller

import engine.dto.QuizCompletionDTO
import engine.dto.QuizDTO
import engine.model.Quiz.Quiz
import engine.model.Quiz.QuizAnswer
import engine.model.Quiz.QuizResult
import engine.service.QuizService
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

/**
 *  The Quiz controller class that intercepts incoming user requests
 *
 *  @author Omar Osman
 *  @property quizService quiz service.
 */
@RestController
@RequestMapping("/api/quizzes")
class QuizController {
    @Autowired
    private lateinit var quizService: QuizService

    @GetMapping("/quiz")
    fun getQuiz(): Quiz {
        return quizService.getQuiz()
    }

    @PostMapping
    fun createQuiz(@RequestBody @Valid quiz: Quiz, auth: Authentication): QuizDTO {
        return quizService.crateQuiz(quiz, auth)
    }

    @GetMapping("/{id}")
    fun getSingleQuiz(@PathVariable id: Int): QuizDTO {
        return quizService.getQuiz(id)
    }

    @GetMapping
    fun getPagedQuizzes(@RequestParam @Min(0) page: Int, auth: Authentication): Page<QuizDTO> {
        return quizService.getPagedQuizzes(page, auth)
    }

    @GetMapping("/completed")
    fun getPagedCompletedQuizzes(@RequestParam @Min(0) page: Int, auth: Authentication): Page<QuizCompletionDTO> {
        return quizService.getPagedCompletedQuizzes(page, auth)
    }

    @PostMapping("/{id}/solve")
    fun solveQuiz(@PathVariable id: Int, @RequestBody answer: QuizAnswer?, auth: Authentication): QuizResult {
        return quizService.solveQuiz(id, answer, auth)
    }

    @DeleteMapping("/{id}")
    fun deleteQuiz(@PathVariable id: Int, auth: Authentication): ResponseEntity<Quiz?> {
        return quizService.deleteQuiz(id, auth)
    }
}