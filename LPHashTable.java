import java.util.List;
/**
 * Simple hash table implementation using linear probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class LPHashTable extends HashTable {

    /**
     * Create an LPHashTable with DEFAULT_SIZE table.
     */
    public LPHashTable() { super(); }

    /**
     * Create an LPHashTable with the given default size table.
     */
    public LPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
    @Override
    protected int findIndex(String key) {
		// Implement using linear probing.
    int hash = hashFunction(key);
    int index = hash;
    int i = 1;
    while (table[index] != null && !table[index].equals(key)) {
        index = (hash + i) % tableSize();
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
