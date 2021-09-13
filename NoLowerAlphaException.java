import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Orion Belete
 *
 */
public class NoLowerAlphaException extends Exception {
	public NoLowerAlphaException(){
	super("The password must contain at least one lowercase alphabetic character");
		
	}
}
