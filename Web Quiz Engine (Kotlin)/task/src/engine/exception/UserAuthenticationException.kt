package engine.exception

/**
 *  User authentication related exception handler class.
 *
 *  @author Omar Osman
 *  @param error error message thrown by the Exception
 */
class UserAuthenticationException(error: String) : RuntimeException(error)
