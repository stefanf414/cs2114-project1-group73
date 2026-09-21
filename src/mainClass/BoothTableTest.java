package mainClass;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test class for validating specialized behaviors of the
 * {@link BoothTable} subclass.
 *
 * @author Noah Bierman
 * @version 2026.09.21
 */
public class BoothTableTest {
    // ~ Fields ................................................................

    /** Test instance of BoothTable used across unit test cases. */
    private BoothTable boothTable;

    // ~ Setup Method ..........................................................

    /**
     * Sets up the test fixture before each test execution.
     * Initializes a BoothTable object with ID 2 and capacity 4.
     */
    @Before
    public void setUp() {
        boothTable = new BoothTable(2, 4);
    }

    // ~ Test Methods ..........................................................


    /**
     * Verifies that the table ID inherited from superclass Table is correctly
     * assigned.
     */
    @Test
    public void testGetTableId() {
        assertEquals(2, boothTable.getTableId());
    }


    /**
     * Verifies that capacity initialization correctly sets max seating
     * capacity.
     */
    @Test
    public void testGetCapacity() {
        assertEquals(4, boothTable.getCapacity());
    }


    /**
     * Tests that a newly created BoothTable defaults to unoccupied (false).
     */
    @Test
    public void testInitialOccupiedState() {
        assertFalse(boothTable.isOccupied());
    }


    /**
     * Tests setting booth occupation state from false to true and back to
     * false.
     */
    @Test
    public void testSetOccupied() {
        boothTable.setOccupied(true);
        assertTrue(boothTable.isOccupied());

        boothTable.setOccupied(false);
        assertFalse(boothTable.isOccupied());
    }


    /**
     * Verifies that overridden method returns "booth" instead of base
     * "regular".
     */
    @Test
    public void testGetTableType() {
        assertEquals("booth", boothTable.getTableType());
    }
}
