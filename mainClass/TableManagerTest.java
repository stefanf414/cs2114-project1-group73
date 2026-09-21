package mainClass;

/**
 * Runs tests for every public method in {@link TableManager}.
 * The tests cover normal behavior, invalid input, unavailable tables,
 * duplicate table numbers, and missing table numbers.
 *
 * @author Stefan Fernandez
 */
public class TableManagerTest {

    /**
     * Creates sample tables, tests the TableManager methods, and prints a
     * success message when every test passes.
     *
     * @param args command-line arguments; not used by this test program
     */
    public static void main(String[] args) {
        TableManager manager = new TableManager();
        Table twoSeat = new Table(1, 2);
        Table fourSeat = new BoothTable(2, 4);
        Table sixSeat = new WindowTable(3, 6);

        // addTable: normal case, null case, and duplicate-number case
        manager.addTable(twoSeat);
        manager.addTable(fourSeat);
        manager.addTable(sixSeat);
        manager.addTable(null);
        manager.addTable(new Table(1, 10));
        check(manager.getAvailableTableCount() == 3,
            "addTable should add only valid, unique tables.");

        // findAvailableTable: normal, closest-fit, bad size, and no-fit cases
        check(manager.findAvailableTable(4) == fourSeat,
            "A party of 4 should receive the 4-seat table.");
        check(manager.findAvailableTable(1) == twoSeat,
            "A party of 1 should receive the smallest fitting table.");
        check(manager.findAvailableTable(0) == null,
            "A party size of 0 should return null.");
        check(manager.findAvailableTable(7) == null,
            "A party too large for every table should return null.");

        // assignTable: normal case and no-fitting-table case
        check(manager.assignTable(4),
            "A party of 4 should be assigned a table.");
        check(!fourSeat.isAvailable(),
            "The assigned table should be occupied.");
        check(manager.getAvailableTableCount() == 2,
            "Two tables should remain available after assignment.");
        check(!manager.assignTable(20),
            "A party that cannot fit should not be assigned a table.");
        check(manager.getAvailableTableCount() == 2,
            "A failed assignment must not change table availability.");

        // releaseTable: normal, already-available, and missing-table cases
        check(manager.releaseTable(2), "Releasing table 2 should succeed.");
        check(fourSeat.isAvailable(),
            "Released table should become available.");
        check(manager.releaseTable(2),
            "Releasing an already available existing table should be safe.");
        check(!manager.releaseTable(99),
            "Releasing a table number that does not exist should return false.");

        // getAvailableTableCount: normal and empty-availability case
        check(manager.getAvailableTableCount() == 3,
            "All three tables should now be available.");
        check(manager.assignTable(2), "First 2-person party should be seated.");
        check(manager.assignTable(4), "4-person party should be seated.");
        check(manager.assignTable(6), "6-person party should be seated.");
        check(manager.getAvailableTableCount() == 0,
            "No tables should be available after all are occupied.");

        System.out.println("All TableManager tests passed.");
    }


    /**
     * Throws an AssertionError if a test condition is false.
     *
     * @param condition
     *            the result that must be true for the test to pass
     * @param message
     *            the description shown if the test fails
     * @throws AssertionError
     *             if {@code condition} is {@code false}
     */
    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("Test failed: " + message);
        }
    }
}
