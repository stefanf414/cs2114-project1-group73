/**
 * Tests the BoothTable class.
 *
 * @author Noah Bierman
 * @version 2026.09.20
 */

package mainClass;
import student.TestCase;

public class BoothTableTest extends TestCase
{
    private BoothTable booth;


    /**
     * Creates a booth before each test.
     */
    public void setUp()
    {
        booth = new BoothTable(2, 6);
    }


    /**
     * Tests creation of a valid booth.
     */
    public void testConstructor()
    {
        assertEquals(2, booth.getTableNumber());
        assertEquals(6, booth.getSeats());
        assertTrue(booth.isAvailable());
        assertEquals("Booth", booth.getTableType());
    }


    /**
     * Tests invalid booth creation.
     */
    public void testInvalidSeats()
    {
        try
        {
            new BoothTable(2, 0);
            fail("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertNotNull(e);
        }
    }


    /**
     * Tests inherited availability behavior.
     */
    public void testAvailability()
    {
        assertTrue(booth.isAvailable());

        booth.occupy();

        assertFalse(booth.isAvailable());

        booth.makeAvailable();

        assertTrue(booth.isAvailable());
    }


    /**
     * Tests booth display output.
     */
    public void testToString()
    {
        assertEquals(
            "Table 2 (Booth, 6 seats) - Available",
            booth.toString());
    }
}