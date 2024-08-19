package engine.exception

/**
 *  Quiz processing related exception handler class.
 *
 *  @author Omar Osman
 *  @param error error message thrown by the Exception
 */
class QuizProcessingException(error: String): RuntimeException(error)