package engine.exception

/**
 *  Quiz Not Found exception handler class.
 *
 *  @author Omar Osman
 *  @param error error message thrown by the Exception
 */

class QuizNotFoundException(error: String) : RuntimeException(error)