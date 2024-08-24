package engine.exception

/**
*  Quiz Deletion request related exception handler class.
*
*  @author Omar Osman
*  @param error error message thrown by the Exception
*/
class QuizDeletionRequestException(error: String) : RuntimeException(error)