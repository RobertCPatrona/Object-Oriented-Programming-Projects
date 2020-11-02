
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/**
 * Tests the main class, which should
 *  - print its command line arguments on standard output
 *  - read any line after and
 *    o if it's known print "Pffft, everyone knows that!"
 *    o if not, print "You're so smart and intelligent!" and remember it
 * @author Stijn Mijland
 */
public class MainTest {
    
    private PrintStream standardOut;
    private InputStream standardIn;
    private ByteArrayOutputStream outputBuffer;
    private ByteArrayInputStream inputBuffer;
    
    /**
     * test set for arguments used.
     */
    private static Map<String, String> stringsWithReverse;
    private final String testInput
        = "The Fact Sphere is the most intelligent sphere" 
        + System.lineSeparator()
        + "The Space Sphere will never go to space."
        + System.lineSeparator()
        + "This is a bad plan. You will fail."
        + System.lineSeparator()
        + "This situation is dangerous."
        + System.lineSeparator()
        + "Twelve. Twelve. Twelve. Twelve. Twelve. Twelve. Twelve. Twelve."
        + System.lineSeparator()
        + "Humans can survive under water, but not for very long."
        + System.lineSeparator()
        + "Honey does not spoil"
        + System.lineSeparator()
        + "At some point in their lives 1 in 6 children will be abducted by the Dutch."
        + System.lineSeparator();
    private static final int noInputLines = 8;

    /**
     *
     */
    @BeforeClass
    public static void setUpResources() {
        stringsWithReverse = new HashMap<>();
        stringsWithReverse.put("Apple", "elppA");
        stringsWithReverse.put("racecar", "racecar");
        stringsWithReverse.put("Sample text", "txet elpmaS");
        stringsWithReverse.put("Java", "avaJ");
    }
    
    /**
     * Before each test, set the output to write to buffer
     */    
    public void setUp(String completeInput) {
        standardIn = System.in;
        standardOut = System.out;
        outputBuffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputBuffer));
        inputBuffer = new ByteArrayInputStream(completeInput.getBytes());
        System.setIn(inputBuffer);
    }
    
    /**
     * Make sure no data echoes between tests
     */
    @After
    public void tearDown() {
        try {
            outputBuffer.close();
        } catch (IOException ioe) {
            System.err.println("outputBuffer not initialised");
        } finally {
            outputBuffer = null;
        }
        try {
            inputBuffer.close();
        } catch (IOException ioe) {
            System.err.println("inputBuffer not initialised");
        } finally {
            inputBuffer = null;
        }
        System.setOut(standardOut);
        System.setIn(standardIn);
    }
    
    /**
     * Tests if the arguments passed to the input of main are indeed
     * printed in reverse
     */
    @Test
    public void argumentTest() throws IOException {
        setUp("");
        String[] commandLineArguments = stringsWithReverse.keySet().toArray(
            new String[1]);
        Main.main(commandLineArguments);
        Scanner sc = new Scanner(outputBuffer.toString());
        for(String str : commandLineArguments) {
            assertTrue(sc.hasNextLine());
            assertEquals(sc.nextLine(), stringsWithReverse.get(str));
        }
        sc.close();
    }
    
    /**
     * Tests if the input passed on the command line is correctly replied to
     */
    @Test
    public void inputTest() throws IOException {
        setUp(testInput + testInput);
        Main.main(new String[0]); //no command line arguments
        Scanner sc = new Scanner(outputBuffer.toString());
        for(int line = 0; line < noInputLines; ++line) {
            assertTrue(sc.hasNextLine());
            assertEquals(sc.nextLine(), "You're so smart and intelligent!");
        }
        for(int line = 0; line < noInputLines; ++line) {
            assertTrue(sc.hasNextLine());
            assertEquals(sc.nextLine(), "Pffft, everyone knows that!");
        }
        sc.close();
    }
    
    /**
     * Tests both functionalities of the problem to see if they don't affect
     * each other
     */
    @Test
    public void compoundTest() throws IOException {
        setUp(testInput + testInput);
        String[] commandLineArguments = stringsWithReverse.keySet().toArray(
            new String[1]);
        Main.main(commandLineArguments);
        Scanner sc = new Scanner(outputBuffer.toString());
        for(String str : commandLineArguments) {
            assertTrue(sc.hasNextLine());
            assertEquals(sc.nextLine(), stringsWithReverse.get(str));
        }
        for(int line = 0; line < noInputLines; ++line) {
            assertTrue(sc.hasNextLine());
            assertEquals(sc.nextLine(), "You're so smart and intelligent!");
        }
        for(int line = 0; line < noInputLines; ++line) {
            assertTrue(sc.hasNextLine());
            assertEquals(sc.nextLine(), "Pffft, everyone knows that!");
        }
        sc.close();
    }
        
}
