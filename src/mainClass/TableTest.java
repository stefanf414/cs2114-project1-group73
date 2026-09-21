package mainClass;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test class for validating the behavior and state management of the
 * base {@link Table} class.
 *
 * @author Noah Bierman
 * @version 2026.09.21
 */
public class TableTest {
    // ~ Fields ................................................................

    /** Test instance of Table used across unit test cases. */
    private Table table;

    // ~ Setup Method ..........................................................

    /**
     * Sets up the test fixture before each test execution.
     * Initializes a standard Table object with ID 1 and capacity 4.
     */
    @Before
    public void setUp() {
        table = new Table(1, 4);
    }

    // ~ Test Methods ..........................................................


    /**
     * Verifies that the table ID is correctly initialized and retrieved.
     */
    @Test
    public void testGetTableId() {
        assertEquals(1, table.getTableId());
    }


    /**
     * Verifies that the seating capacity is correctly initialized and
     * retrieved.
     */
    @Test
    public void testGetCapacity() {
        assertEquals(4, table.getCapacity());
    }


    /**
     * Tests that new table instances default to an unoccupied state (false).
     */
    @Test
    public void testInitialOccupiedState() {
        assertFalse(table.isOccupied());
    }


    /**
     * Tests changing table occupation status using
     * {@link Table#setOccupied(boolean)}.
     */
    @Test
    public void testSetOccupied() {
        table.setOccupied(true);
        assertTrue(table.isOccupied());

        table.setOccupied(false);
        assertFalse(table.isOccupied());
    }


    /**
     * Tests that base Table returns the default table type "regular".
     */
    @Test
    public void testGetTableType() {
        assertEquals("regular", table.getTableType());
    }
}
