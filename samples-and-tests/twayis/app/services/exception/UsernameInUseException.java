package services.exception;

/**
 *
 * @author luciano
 */
public class UsernameInUseException extends RegistrationUsernameException {
    
    public UsernameInUseException(String message) {
    	super(message);
    }
}
