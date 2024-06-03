public class LPAutoTest extends AutoTest {
    protected HashTable makeTable(final int size) {
        return new LPHashTable(size);
    }

    public static void main(final String[] args) {
        (new LPAutoTest()).run();
    }

}
