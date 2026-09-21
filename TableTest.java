/**
 * Tests the Table class.
 *
 * @author Noah Bierman
 * @version 2026.09.20
 */

package mainClass;
import student.TestCase;

public class TableTest extends TestCase
{
    private Table table;


    /**
     * Creates a table before each test.
     */
    public void setUp()
    {
        table = new Table(5, 4);
    }


    /**
     * Tests a valid table constructor.
     */
    public void testConstructor()
    {
        assertEquals(5, table.getTableNumber());
        assertEquals(4, table.getSeats());
        assertTrue(table.isAvailable());
    }


    /**
     * Tests an invalid table number.
     */
    public void testInvalidTableNumber()
    {
        try
        {
            new Table(0, 4);
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
            new Table(5, 0);
            fail("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertNotNull(e);
        }
    }


    /**
     * Tests getTableNumber.
     */
    public void testGetTableNumber()
    {
        assertEquals(5, table.getTableNumber());
    }


    /**
     * Tests getSeats.
     */
    public void testGetSeats()
    {
        assertEquals(4, table.getSeats());
    }


    /**
     * Tests availability for an open and occupied table.
     */
    public void testIsAvailable()
    {
        assertTrue(table.isAvailable());

        table.occupy();

        assertFalse(table.isAvailable());
    }


    /**
     * Tests occupying a table.
     */
    public void testOccupy()
    {
        assertTrue(table.isAvailable());

        table.occupy();

        assertFalse(table.isAvailable());

        // Occupying an already occupied table should cause no error.
        table.occupy();

        assertFalse(table.isAvailable());
    }


    /**
     * Tests making a table available.
     */
    public void testMakeAvailable()
    {
        table.occupy();

        assertFalse(table.isAvailable());

        table.makeAvailable();

        assertTrue(table.isAvailable());

        // Making an already available table available should cause no error.
        table.makeAvailable();

        assertTrue(table.isAvailable());
    }


    /**
     * Tests the standard table type.
     */
    public void testGetTableType()
    {
        assertEquals("Standard", table.getTableType());
    }


    /**
     * Tests the readable string representation.
     */
    public void testToString()
    {
        assertEquals(
            "Table 5 (Standard, 4 seats) - Available",
            table.toString());

        table.occupy();

        assertEquals(
            "Table 5 (Standard, 4 seats) - Occupied",
            table.toString());
    }
}