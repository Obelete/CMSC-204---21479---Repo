import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Orion Belete
 *
 */
public class PasswordCheckerSTUDENT_Test {

	ArrayList<String> myPasswords;
	
	@Before
	public void setUp() throws Exception {
		String[] pStrings = {"1OaB%", "1OB%password", "123lowercase%", "123Lowercase%", "123UPPERCASE%", "123uppercase#",
							"JustnoDigit%", "123withDigit&", "123noSpecial", "123withSpecial%", "123RRRepetative$", "123Normal$", 
							"1weakP@", "123LongPass$"};
		myPasswords = new ArrayList<>();
		myPasswords.addAll(Arrays.asList(pStrings)); 
		}

	@After
	public void tearDown() throws Exception {
		myPasswords = null;
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("123DEF15#"));
			PasswordCheckerUtility.isValidPassword("123a#");
			assertTrue("Did not throw lengthException",false);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("1234567aA@"));
			PasswordCheckerUtility.isValidPassword("1234567a@");
			assertTrue("Did not throw NoUpperAlphaException",false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("1234567Aa#"));
			PasswordCheckerUtility.isValidPassword("1234567A#");
			assertTrue("Did not throw NoLowerAlphaException",false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("1234aaAA910$"));
			boolean weakPwd = PasswordCheckerUtility.isWeakPassword("1234aaAA#");
			assertTrue(weakPwd);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception",false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("1234aaAA@"));
			PasswordCheckerUtility.isValidPassword("1234aAAA!");
			assertTrue("Did not throw an InvalidSequenceException",false);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("123Orion@@"));
			PasswordCheckerUtility.isValidPassword("Orion@@");
			assertTrue("Did not throw NoDigitException", false);
		}
		catch (NoDigitException e) {
			assertTrue("Successfully threw NoDigitException", true);
		}
		catch(Exception e) {
			assertTrue("threw some other exception besides NoDigitException", false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
		assertEquals(true, PasswordCheckerUtility.isValidPassword("123Orion@@"));
		boolean strongPassword = PasswordCheckerUtility.isValidPassword("123Orion@@");
		assertTrue(strongPassword);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("threw incorrect exception", false);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(myPasswords);
		
		System.out.println(results.get(0));
		System.out.println(results.get(2));
		System.out.println(results.get(4));
		System.out.println(results.get(5));
		System.out.println(results.get(6));
		
		assertEquals(results.get(0), "1OaB% The password must be at least 6 characters long");
		assertEquals(results.get(2), "123UPPERCASE% The password must contain at least one lowercase alphabetic character");
		String fourthResult = results.get(4);
		assertEquals(fourthResult, "JustnoDigit% The password must contain at least one digit");
		String fifthResult = results.get(5);
		assertEquals(fifthResult, "123noSpecial The password must contain at least one special character");
		String sixthResult = results.get(6);
		assertEquals(sixthResult, "123RRRepetative$ The password cannot contain more than two of the same character in sequence.");
		
		
	}
	
	
}