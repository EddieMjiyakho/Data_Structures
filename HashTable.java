import java.util.Arrays;
/**
 * Abstract implementation of hash table storing strings.
 *
 * @author Stephan Jamieson
 * @version 24/4/2023
 */
public abstract class HashTable  extends Monitorable {
    public final static int DEFAULT_SIZE = 50;
    public final static int KEY_LENGTH = 9;
    private static final int[] DEFAULT_WEIGHTS = {1, 2, 3, 4, 1, 2, 3, 4, 1};
    private int[] weights = DEFAULT_WEIGHTS;

    protected String[] table;
    protected int entries;


    /**
     * Create a table with DEFAULT_SIZE. (For use by sub classes.)
     */
    protected HashTable() {
        this(DEFAULT_SIZE);
    }

    /**
     * Create a table with the given default size. (For use by sub classes.)
     */
    protected HashTable(final int size) {
        this.table = new String[size];
        this.entries = 0;
    }
    /**
     * Obtain the size of the table. Used by hashFunction to support
     * sub classing.
     */
    protected int tableSize() { return table.length; }


    /**
     * Set hash function weights
     */
    public void setWeights(final int[] weights) {
        assert weights.length == KEY_LENGTH;
        this.weights = weights;
    }

    /**
     * Get hash function wieghts
     */
    public int[] getWeights() {
        return Arrays.copyOf(this.weights, this.weights.length);
    }
    /**
     * Weighted hashfunction.
     */
   
    protected int hashFunction(final String key) {
        assert key.length() == KEY_LENGTH;
    
        int hashVal = 0;
    
        for(int i = 0; i < key.length(); i++) {
            hashVal += weights[i % weights.length] * key.charAt(i);
        }
        return hashVal % this.tableSize();
    }
    
    /**
     * Determine whether given key value is in the table.
     */
    public boolean contains(String key) {
        int index = findIndex(key);
        if (index==-1) {
            return false;
        }
        else {
            return table[index]==null ? false : true;
        }
    }

    public void insert(String key) {
        int index = findIndex(key);
        if (index==-1) {
            // Table is full, time to rehash.
            this.rebuild();
        }

        if (table[index]==null) {
            table[index]= key;
            this.entries++;
        }
    }


    public boolean isEmpty() { return entries == 0; }

    public void empty() { this.table = new String[this.tableSize()]; this.entries=0; }

    public int size() { return this.entries; }

    /* Hash Table Functions */

    public double loadFactor() { return entries/(double)table.length; }

    /**
     * Method called by <code>insert()</code> when the table needs enlarging.
     * <p>
     * Subclasses should override as required.
     */
    protected void rebuild() {
        throw new IllegalStateException("Hashtable:insert(): table is full.");
    }


    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
   // protected abstract int findIndex(String key);
    protected int findIndex(String key) {
        int hash = hashFunction(key);
        int i = 0;
        while (i < this.tableSize()) {
            int index = (hash + i) % this.tableSize();
            if (table[index] == null || table[index].equals(key)) {
                this.incProbeCount();
                return index;
            }
            i++;
            this.incProbeCount();
        }
        return -1;
    }
    



    /**
     * Prints contents of table to screen. (Method provided to facilitate testing and debugging.)
     */
    public void dump() {
        final String[] table = this.table;
        for(int i=0; i<tableSize(); i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
        System.out.printf("\n#Entries: %d.", this.entries);
    }

    /**
     * Obtain a list of the entries in the dictionary. (Method to facilitate testing and debugging.)
     */
    public java.util.ArrayList<String> getKeys() {
        java.util.ArrayList<String> entries = new java.util.ArrayList<String>();
        for (int i=0; i<this.tableSize(); i++) {
            if (this.table[i]!=null) {
                entries.add(table[i]);
            }
        }
        return entries;
    }

}
