import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * This is a password checker application
 * @author Orion Belete
 *
 */

public class PasswordCheckerUtility {
	
	 /**
	  * A constructor passwordCheckerUtility()
	  */
	public PasswordCheckerUtility() { //throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException, WeakPasswordException, FileNotFoundException, NoSpecialCharacterException {
		//isValidPassword(passwordString);
		//isWeakPassword(passwordString);
		//getInvalidPasswords(invalidPasswords);
	}
	
	/**
	 * checks if the password entered is valid
	 * @param passwordString
	 * @return returns true if the password is valid
	 * @throws LengthException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoDigitException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 */
	public static boolean isValidPassword(String passwordString) throws 
	LengthException,
	NoUpperAlphaException,
	NoLowerAlphaException,
	NoDigitException,
	NoSpecialCharacterException,
    InvalidSequenceException
     {
		
		isLongEnough(passwordString); 
		hasUpperCase(passwordString); 
		hasLowerCase(passwordString); 
		hasDigit(passwordString); 
		hasSpecialCharacter(passwordString);
		invalidSequence(passwordString);
		return true;
	}
		
		
/**
 * checks if the password entered is long enough.
 * @param passwordString
 * @return returns true if the password is long enough
 * @throws LengthException
 */
@Test
	private static boolean isLongEnough(String passwordString) throws LengthException{
		if (passwordString.length()<6) {
			throw new LengthException();
		}
		else {
			return true;
		}
	}
/**
 * checks if the password includes upper case letter
 * @param passwordString
 * @return true or false
 * @throws NoUpperAlphaException
 */
@Test
	private static boolean hasUpperCase(String passwordString) throws NoUpperAlphaException{
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(passwordString);
		if (!matcher.find()) {
			throw new NoUpperAlphaException();
		}
		else {
			return true;
		}
	}
/**
 * checks if the password includes lower case letter
 * @param passwordString
 * @return true or false
 * @throws NoLowerAlphaException
 */
@Test
	private static boolean hasLowerCase(String passwordString) throws NoLowerAlphaException {
		Pattern pattern = Pattern.compile("[a-z]");
		Matcher matcher = pattern.matcher(passwordString);
		if (!matcher.find()) {
			throw new NoLowerAlphaException();
		}
		else {
			return true;
		}
	}
/**
 * checks if the password includes a digit
 * @param passwordString
 * @return true or false
 * @throws NoDigitException
 */
@Test
	private static boolean hasDigit(String passwordString) throws NoDigitException {
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(passwordString);
		if(!matcher.find()) {
			throw new NoDigitException();
		}
		else {
			return true;
		}
	}
/**
 * checks if the password includes special characters
 * @param passwordString
 * @return true or false
 * @throws NoSpecialCharacterException
 */
@Test
	private static boolean hasSpecialCharacter(String passwordString) throws NoSpecialCharacterException{
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
		Matcher matcher = pattern.matcher(passwordString);
		if(!matcher.find()) {
			throw new NoSpecialCharacterException();
		}
		else {
			return true;
		}
	}
/**
 * checks if the password has invalid sequence
 * @param passwordString
 * @return true or false
 * @throws InvalidSequenceException
 */
@Test
	private static boolean invalidSequence(String passwordString) throws InvalidSequenceException {
		boolean validity = true;
		for(int i = 0; i < passwordString.length()-2; i++) {
				if (passwordString.charAt(i) == passwordString.charAt(i+1) &&
						passwordString.charAt(i+1) == passwordString.charAt(i+2)) {
					throw new InvalidSequenceException();
				}
				else {
					validity = false;
				}
				}
		return validity;
	}
	
	/**
	 * checks if the password is weak if between 6-9 characters
	 * @param passwordString
	 * @return true or false
	 */
	public static boolean isWeakPassword(String passwordString) {
		if(passwordString.length() >= 6 && passwordString.length() < 10) {
			return true;
		}
		else return false;
	}
	
	
	
	/**
	 * checks for invalid passwords
	 * @param passwords
	 * @return returns the invalid passwords
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		ArrayList<String> invalidPasswordStrings = new ArrayList<String>(); 
		for (String password: passwords) {
			try {
				isValidPassword(password);
			}
			catch (Exception e) {
					invalidPasswordStrings.add(password + " " + e.getMessage());
			}
		}
		return invalidPasswordStrings;
	}

}
