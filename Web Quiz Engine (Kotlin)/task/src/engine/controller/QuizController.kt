package engine.controller

import engine.model.Quiz
import engine.model.QuizResult
import engine.service.QuizService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 *  The Quiz controller class that intercepts incoming user requests
 *
 *  @author Omar Osman
 *  @property quizService quiz service
 */
@RestController
@RequestMapping("/api")
class QuizController {
   @Autowired
   private lateinit var quizService : QuizService

   @GetMapping("/quiz")
   fun getQuiz() : Quiz {
       return quizService.getQuiz()
   }

   @PostMapping("/quiz")
   fun solveQuiz(@RequestParam answer: Int): QuizResult {
      return quizService.solveQuiz(answer)
   }
}