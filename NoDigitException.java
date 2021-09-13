import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Orion Belete
 *
 */
public class NoDigitException extends Exception {
	public NoDigitException(){
		super("The password must contain at least one digit");

	}
}