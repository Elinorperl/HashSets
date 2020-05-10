/**
 * Simple Hashset is the "father" class of our Hashsets, acting as the connecting element of the two sets.
 */
public abstract class SimpleHashSet implements SimpleSet {
    double upperLoaderFactor;
    double lowerLoadFactor;
    int hashCapacity;
    String[] data;
    protected int elementCounter;
    protected float loadFactor;
    final double defaultUpperLoaderFactor = 0.75f;
    final double defaultLowerLoadFactor = 0.25f;
    public int defaultCapacity = 16;
    public final int CapacityMinusOne = hashCapacity -1;
    public final int RehashFactor = 2;
    public final int NonExistingElement = -1;
    public final int HALF = 2;

    public SimpleHashSet(float upperLoaderFactor, float lowerLoadFactor) {
        this.upperLoaderFactor = upperLoaderFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        this.hashCapacity = defaultCapacity;
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public SimpleHashSet() {
        this.upperLoaderFactor = defaultUpperLoaderFactor;
        this.lowerLoadFactor = defaultLowerLoadFactor;
        this.hashCapacity = defaultCapacity;
    }

    /**
     * Adds specified value in the set.
     * @param newValue Value to add to the hashtable
     * @return False if newValue is found in the set and can not be added to the set, true if it isn't and can be added
//     */

     public abstract boolean add(String newValue);

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */

    public abstract boolean contains(String searchVal);

    /**
     * Look for a specified value in the set.
     * @param toDelete Value to delete
     * @return True if isDelete is found in the set and can be deleted, false otherwise.
     */

    public abstract boolean delete(String toDelete);

    /**
     * The amount of elements our set can currently hold.
     * @return The capacity of the set.
     */
    public abstract int capacity();

}
