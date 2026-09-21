/**
 * Tests the WindowTable class.
 *
 * @author Noah Bierman
 * @version 2026.09.20
 */

package mainClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class WindowTableTest 
{
    private WindowTable window;


    /**
     * Creates a window table before each test.
     */
    public void setUp()
    {
        window = new WindowTable(3, 4);
    }


    /**
     * Tests creation of a valid window table.
     */
    public void testConstructor()
    {
        assertEquals(3, window.getTableNumber());
        assertEquals(4, window.getSeats());
        assertTrue(window.isAvailable());
        assertEquals("Window", window.getTableType());
    }


    /**
     * Tests an invalid table number.
     */
    public void testInvalidTableNumber()
    {
        try
        {
            new WindowTable(0, 4);
            fail("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertNotNull(e);
        }
    }


    /**
     * Tests an invalid number of seats.
     */
    public void testInvalidSeats()
    {
        try
        {
            new WindowTable(3, 0);
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
        assertTrue(window.isAvailable());

        window.occupy();

        assertFalse(window.isAvailable());

        window.makeAvailable();

        assertTrue(window.isAvailable());
    }


    /**
     * Tests window table display output.
     */
    public void testToString()
    {
        assertEquals(
            "Table 3 (Window, 4 seats) - Available",
            window.toString());
    }
}
