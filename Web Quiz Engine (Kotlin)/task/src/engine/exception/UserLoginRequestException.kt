package engine.exception


/**
 *  User Login request related exception handler class.
 *
 *  @author Omar Osman
 *  @param error error message thrown by the Exception
 */
class UserLoginRequestException(error: String) : RuntimeException(error)