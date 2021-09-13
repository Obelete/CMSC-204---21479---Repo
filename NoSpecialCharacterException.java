import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Orion Belete
 *
 */
public class NoSpecialCharacterException extends Exception {
	public NoSpecialCharacterException(){
		super("The password must contain at least one special character");
		
	}
}

