/**
 * Simple hash table implementation using quadratic probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class QPHashTable  extends HashTable {


    /**
     * Create an QPHashTable with DEFAULT_SIZE table.
     */
    public QPHashTable() { super(); }

    /**
     * Create an QPHashTable with the given default size table.
     */
    public QPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
    @Override
    protected int findIndex(String key) {
		// Implement using quadratic probing.
    int hash = hashFunction(key);
        int index = hash;
        int i = 1;
        while (table[index] != null && !table[index].equals(key)) {
            index = (hash + i*i) % tableSize();
            incProbeCount();
            if (index == hash) {
                // Table is full
                return -1;
            }
            i++;
        }
        incProbeCount();
        return index;
    }
}
