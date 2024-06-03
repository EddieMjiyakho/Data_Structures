
public class QPAutoTest extends AutoTest {
    protected HashTable makeTable(final int size) {
        return new QPHashTable(size);
    }

    public static void main(final String[] args) {
        (new QPAutoTest()).run();
    }
}
