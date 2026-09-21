package mainClass;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test class for validating specialized behaviors of the
 * {@link WindowTable} subclass.
 *
 * @author Stefan Fernandez
 * @version 2026.09.21
 */
public class WindowTableTest {
    // ~ Fields ................................................................

    /** Test instance of WindowTable used across unit test cases. */
    private WindowTable windowTable;

    // ~ Setup Method ..........................................................

    /**
     * Sets up the test fixture before each test execution.
     * Initializes a WindowTable object with ID 3 and capacity 6.
     */
    @Before
    public void setUp() {
        windowTable = new WindowTable(3, 6);
    }

    // ~ Test Methods ..........................................................


    /**
     * Verifies that the table ID inherited from superclass Table is correctly
     * assigned.
     */
    @Test
    public void testGetTableId() {
        assertEquals(3, windowTable.getTableId());
    }


    /**
     * Verifies that capacity initialization correctly sets max seating
     * capacity.
     */
    @Test
    public void testGetCapacity() {
        assertEquals(6, windowTable.getCapacity());
    }


    /**
     * Tests that a newly created WindowTable defaults to unoccupied (false).
     */
    @Test
    public void testInitialOccupiedState() {
        assertFalse(windowTable.isOccupied());
    }


    /**
     * Tests setting window table occupation state from false to true and back
     * to false.
     */
    @Test
    public void testSetOccupied() {
        windowTable.setOccupied(true);
        assertTrue(windowTable.isOccupied());

        windowTable.setOccupied(false);
        assertFalse(windowTable.isOccupied());
    }


    /**
     * Verifies that overridden method returns "window" instead of base
     * "regular".
     */
    @Test
    public void testGetTableType() {
        assertEquals("window", windowTable.getTableType());
    }
}
