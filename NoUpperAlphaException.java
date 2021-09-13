import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Orion Belete
 *
 */

public class NoUpperAlphaException extends Exception{
	public NoUpperAlphaException(){
	
	super("The password must contain at least one uppercase alphabetic character");
		
	}
}
