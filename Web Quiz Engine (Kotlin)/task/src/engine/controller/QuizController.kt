package engine.controller

import engine.dto.QuizDTO
import engine.model.Quiz
import engine.model.QuizResult
import engine.service.QuizService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
   private lateinit var quizService : QuizService

   @GetMapping("/quiz")
   fun getQuiz() : Quiz {
       return quizService.getQuiz()
   }

   @PostMapping
   fun createQuiz(@RequestBody quiz: Quiz) : QuizDTO {
      return quizService.crateQuiz(quiz)
   }

   @GetMapping("/{id}")
   fun getSingleQuiz(@PathVariable id: Int) : QuizDTO {
      return quizService.getQuiz(id)
   }

   @GetMapping
   fun getAvailableQuizzes(): List<QuizDTO> {
      return quizService.getAllQuizzes()
   }

   @PostMapping("/{id}/solve")
   fun solveQuiz(@PathVariable id: Int, @RequestParam answer: Int): QuizResult {
      return quizService.solveQuiz(id, answer)
   }
}