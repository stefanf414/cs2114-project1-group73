package mainClass;

/**
 * Represents a window-side table in the restaurant layout.
 * Inherits core table functionality from {@link Table} and overrides table type
 * identification.
 *
 * @author Noah Bierman
 * @version 2026.09.21
 */
public class WindowTable extends Table {
    // ~ Constructors ..........................................................

    /**
     * Constructs a WindowTable instance with a unique ID and capacity limit.
     * Initializes as unoccupied by invoking the superclass constructor.
     *
     * @param tableId
     *            unique numeric identifier for this table
     * @param capacity
     *            maximum seating capacity of the window table
     */
    public WindowTable(int tableId, int capacity) {
        super(tableId, capacity);
    }

    // ~ Overridden Public Methods .............................................


    /**
     * Identifies the category type of this table subclass.
     *
     * @return the string literal "window"
     */
    @Override
    public String getTableType() {
        return "window";
    }
}
