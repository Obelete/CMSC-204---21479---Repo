import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * class to test grade book
 * @author Orion Belete
 *
 */
public class TestGradeBook {
	public GradeBook johnsGrade;
	public GradeBook doesGrade;
	double johnsmathGrade = 94.67, johnscmscGrade = 89.4, johnsenglishGrade = 86.34, 
			johnspsychologyGrade = 93.21, johnsdesignGrade = 97.89;
	double doesmathGrade = 67.9, doescmscGrade = 93.34, doesenglishGrade = 78.75, 
			doespsychologyGrade = 98.74, doesdesignGrade = 84.36;
	
@Before
public void setUp() throws Exception{
	johnsGrade = new GradeBook(5);
	doesGrade = new GradeBook(5);
	johnsGrade.addScore(johnsmathGrade);
	johnsGrade.addScore(johnscmscGrade);
	johnsGrade.addScore(johnsenglishGrade);
	johnsGrade.addScore(johnspsychologyGrade);
	johnsGrade.addScore(johnsdesignGrade);
	doesGrade.addScore(doesmathGrade);
	doesGrade.addScore(doescmscGrade);
	doesGrade.addScore(doesenglishGrade);
	doesGrade.addScore(doespsychologyGrade);
	doesGrade.addScore(doesdesignGrade);
}

@After
public void tearDown() throws Exception{
	johnsGrade = null;
	doesGrade = null;
}

@Test
public void testAddScore() {
	assertTrue(johnsGrade.toString().equals("94.67 89.4 86.34 93.21 97.89 "));
	assertTrue(doesGrade.toString().equals("67.9 93.34 78.75 98.74 84.36 "));
	assertEquals(5, doesGrade.getScoreSize());
}

@Test
public void testSum() {
	assertEquals(423.09, doesGrade.sum(), 0.01);
}

@Test
public void testMinimum() {
	assertEquals(86.34, johnsGrade.minimum(), .01);
	assertEquals(67.9, doesGrade.minimum(), .1);
}

@Test
public void testFinalScore() {
	assertEquals(375.16, johnsGrade.finalScore(), .01);
	assertEquals(355.19, doesGrade.finalScore(), .01);
}

}
