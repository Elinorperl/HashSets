public class ClosedHashSet extends SimpleHashSet {
    private final String DELETED = "";
    private String[] closedHash;
    private int elementCounter;
    private float loadFactor;

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoaderFactor - a manual upperloadfactor to measure against the load factor.
     * @param lowerLoadFactor - a manual lowerloadfactor to measure against the load factor.
     */
    public ClosedHashSet(float upperLoaderFactor, float lowerLoadFactor) {
        super(upperLoaderFactor,lowerLoadFactor);
        this.closedHash = new String[hashCapacity];
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        super();
        closedHash = new String[hashCapacity];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param  data - A list of strings to add to the hashset.
     */
    public ClosedHashSet(String[] data) {
        this();
        for (String aData : data) this.add(aData);
    }

    /**
     * A function that resizes our HashTable once we've reached maximum (or minimum)
     * @param oldHash - the hashset that will will rehash (making it the old).
     * @param newCapacity - the capacity to which we are changing our new hash.
     */

    public void reHashing(String[] oldHash, int newCapacity) {
        String[] newHash = new String[newCapacity];
        int oldHashCapacity = hashCapacity;
        hashCapacity = newCapacity;
        closedHash = newHash;
        for (int i = 0; i < oldHashCapacity; i++) {
            if (oldHash[i] != null && !oldHash[i].equals(DELETED))
                closedHash[getIndexofNewValue(oldHash[i])] = oldHash[i];
        }
    }

    /**
     * Returns the index for an existing value in our Hashtable.
     * @param value - a value from the HashTable.
     * @return The index for the inserted value.
     */
    private int getIndexofExsistingValue(String value) {
        int index = value.hashCode() & hashCapacity-1 ;
        int i = 1;
        while (closedHash[index] != null){
            if (closedHash[index].equals(value) && closedHash[index] != DELETED)
                return index;
            index = (value.hashCode() + (i + i * i) / 2) & hashCapacity-1;
            i++;
        }return NonExistingElement;
    }

    /**
         *Returns the index for a  new value in our Hashtable.
     * @param value - a value from the HashTable.
     * @return The index for the inserted value.
     */
    private int getIndexofNewValue(String value) {
        int index;
        for (int i = 0; i < hashCapacity; i++) {
            index = (value.hashCode() + (i + i * i) / 2) & (hashCapacity-1);

            if (closedHash[index] == null || closedHash[index].equals(DELETED))
                return index;
        } return NonExistingElement; // Index isn't supposed to get to this line.
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue - The new value that one is interested in adding to the HashTable.
     * @return false if element is already in the openHashSet, true otherwise.
     */
    public boolean add(String newValue) {
            if (contains(newValue))
                return false;
            if (upperLoaderFactor < (float)(elementCounter+1) /capacity()) {
                reHashing(closedHash, hashCapacity * 2);
            }
        elementCounter++;
        closedHash[getIndexofNewValue(newValue)] = newValue;
            return true;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param searchVal - The value that one is interested in checking if it is found in the HashTable.
     * @return true if element is in the openHashSet, false otherwise.
     */
    public boolean contains(String searchVal) {
        int index;
        index = getIndexofExsistingValue(searchVal);
        return index != NonExistingElement;
        }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param toDelete - The value that one is interested in deleting from the HashTable.
     * @return true if the parameter of "toDelete" was found and deleted, false otherwise.
     */
    public boolean delete(String toDelete) {
        if (contains(toDelete)) {
            int index = getIndexofExsistingValue(toDelete);
            if (closedHash[index] != null)
                if (closedHash[index].equals(toDelete))
                    closedHash[index] = DELETED;
            updateLoadfactor();
            elementCounter--;
            if ((float)(elementCounter-1) /capacity() < lowerLoadFactor)
                reHashing(closedHash, (hashCapacity/RehashFactor));
            return true;
            }

        return false;
    }
    /**
     * Returns the number of elements found in our set.
     * @return The size of the set.
     */
    public int size() {return elementCounter;}

    /**
     * The amount of elements our set can currently hold.
     * @return The capacity of the set.
     */
    public int capacity() {return hashCapacity;}

    /**
     * Constantly updating uploadfactor according to the current givens.
     */
    public void updateLoadfactor() {loadFactor = (float)(elementCounter/hashCapacity);}
}


