
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Test the small class 'Memory' used as an illustration to students how a
 * class works.
 *
 * @author Stijn Mijland
 */
public class MemoryTest {

    /**
     * The test set used to verify if words can be remembered accurately.
     * tests are designed around case sensitive uniqueness.
     */
    private static String[] testSet = { "Arbitrary"
                                      , "Words"
                                      , "Collected"
                                      , "In"
                                      , "An"
                                      , "Array"
                                      , "note"
                                      , "memory"
                                      , "is"
                                      , "case"
                                      , "sensitive"
                                      , "SeNsItIvE"
                                      , "sEnSiTiVe"
                                      };
    /**
     * The Memory-class being tested;
     */
    private Memory memory;
    
    /**
     * Initialize a new Memory
     */
    @Before
    public void buildUp() {
        memory = new Memory();
    }
    
    /**
     * Eliminate echo-effects by making the memory null again
     */
    @After
    public void tearDown() {
        memory = null;
    }

    /**
     * Attempts to verify that the used constructor does not fail, although
     * this is probably rather pointless.
     */
    @Test
    public void testConstructorEmpty() {
        assertNotNull(memory);
    }
    
    /**
     * Test the remember-method by repeatedly inserting words. It should
     * not fail when the same word is inserted again.
     */
    @Test
    public void testRemember() {
        for(String str : testSet)
            memory.remember(str);
        for(String str : testSet)
            memory.remember(str);
        //no assertions possible - only canRemember produces output
    }
    
    /**
     * Test the canRemember method. Any string remembered should return true,
     * any string not remembered should return false.
     */
    @Test
    public void testCanRemember() {
        for(int remembered = 0; remembered < testSet.length; ++remembered) {
            for(int checked = 0; checked < testSet.length; ++checked)
                if(checked < remembered)
                    assertTrue(memory.canRemember(testSet[checked]));
                else
                    assertFalse(memory.canRemember(testSet[checked]));
            memory.remember(testSet[remembered]);
        }
        for(int checked = 0; checked < testSet.length; ++checked)
            assertTrue(memory.canRemember(testSet[checked]));
    }
}
