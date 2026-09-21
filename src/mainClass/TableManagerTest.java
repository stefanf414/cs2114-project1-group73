package mainClass;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * Unit test class for validating table management operations in
 * {@link TableManager}.
 * Verifies table additions, capacity/type assignment logic, table clearing
 * methods,
 * and availability counter tracking.
 *
 * @author Stefan Fernandez
 * @version 2026.09.21
 */
public class TableManagerTest {
    // ~ Fields ................................................................

    /** Test instance of TableManager under test. */
    private TableManager tableManager;

    /** Sample Booth table instance (ID 1, Capacity 4). */
    private BoothTable booth1;

    /** Sample Window table instance (ID 2, Capacity 6). */
    private Table window1;

    /** Sample Regular table instance (ID 3, Capacity 8). */
    private Table regular1;

    // ~ Setup Method ..........................................................

    /**
     * Sets up the test fixture before each unit test execution.
     * Initializes a fresh TableManager and populates it with a booth, window,
     * and regular table.
     */
    @Before
    public void setUp() {
        tableManager = new TableManager();

        booth1 = new BoothTable(1, 4);
        window1 = new WindowTable(2, 6);
        regular1 = new Table(3, 8);

        tableManager.addTable(booth1);
        tableManager.addTable(window1);
        tableManager.addTable(regular1);
    }

    // ~ Test Methods ..........................................................


    /**
     * Tests adding tables and verifying total table counts.
     */
    @Test
    public void testAddTableAndCounts() {
        assertEquals(3, tableManager.getTotalTableCount());
        assertEquals(3, tableManager.getAvailableTableCount());

        List<Table> tables = tableManager.getTables();
        assertNotNull(tables);
        assertEquals(3, tables.size());
    }


    /**
     * Tests counting total and available tables filtered by specific table
     * type.
     */
    @Test
    public void testGetCountsByType() {
        assertEquals(1, tableManager.getTotalCountByType("booth"));
        assertEquals(1, tableManager.getAvailableCountByType("booth"));

        assertEquals(1, tableManager.getTotalCountByType("window"));
        assertEquals(1, tableManager.getAvailableCountByType("window"));

        assertEquals(1, tableManager.getTotalCountByType("regular"));
        assertEquals(1, tableManager.getAvailableCountByType("regular"));
    }


    /**
     * Tests successful seating assignment matching both table type preference
     * and size capacity.
     */
    @Test
    public void testAssignTableSuccess() {
        // Assign a party of 4 to a booth
        boolean assigned = tableManager.assignTable(4, "booth");

        assertTrue(assigned);
        assertTrue(booth1.isOccupied());
        assertEquals(0, tableManager.getAvailableCountByType("booth"));
        assertEquals(2, tableManager.getAvailableTableCount());
    }


    /**
     * Tests failed table assignment due to party size exceeding table capacity.
     */
    @Test
    public void testAssignTableExceedsCapacity() {
        // Try seating a party of 6 in a booth with capacity 4
        boolean assigned = tableManager.assignTable(6, "booth");

        assertFalse(assigned);
        assertFalse(booth1.isOccupied());
        assertEquals(1, tableManager.getAvailableCountByType("booth"));
    }


    /**
     * Tests clearing an occupied table by its specific table ID number.
     */
    @Test
    public void testClearTableById() {
        tableManager.assignTable(4, "booth");
        assertTrue(booth1.isOccupied());

        // Clear table #1 by ID
        boolean cleared = tableManager.clearTableById(1);

        assertTrue(cleared);
        assertFalse(booth1.isOccupied());
        assertEquals(1, tableManager.getAvailableCountByType("booth"));

        // Attempting to clear an already open table should return false
        assertFalse(tableManager.clearTableById(1));

        // Attempting to clear a non-existent table ID should return false
        assertFalse(tableManager.clearTableById(99));
    }


    /**
     * Tests clearing the first occupied table matching a specified table type.
     */
    @Test
    public void testClearFirstOccupiedByType() {
        tableManager.assignTable(4, "booth");
        assertTrue(booth1.isOccupied());

        // Clear occupied table by type preference
        boolean cleared = tableManager.clearFirstOccupiedByType("booth");

        assertTrue(cleared);
        assertFalse(booth1.isOccupied());

        // Attempting to clear when no occupied tables of that type exist should
        // return false
        assertFalse(tableManager.clearFirstOccupiedByType("booth"));
    }
}
