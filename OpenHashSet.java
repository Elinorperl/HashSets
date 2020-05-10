import java.util.Iterator;

public class OpenHashSet extends SimpleHashSet {
    int elementCounter;
    float loadFactor;
    private LinkedListClass[] linkedList;

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoaderFactor - a manual upperloadfactor to measure against the load factor.
     * @param lowerLoadFactor - a manual lowerloadfactor to measure against the load factor.
     */
    public OpenHashSet(float upperLoaderFactor, float lowerLoadFactor) {
        super(upperLoaderFactor, lowerLoadFactor);
        linkedList = new LinkedListClass[hashCapacity];
        for (int i = 0; i < hashCapacity; i++)
            linkedList[i] = new LinkedListClass();

    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        super();
        linkedList = new LinkedListClass[hashCapacity];
        for (int i = 0; i < hashCapacity; i++)
            linkedList[i] = new LinkedListClass();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data - A list of strings I wish to add to my Hashset.
     */
    public OpenHashSet(String[] data) {
        this();
        this.data = data;
        linkedList = new LinkedListClass[defaultCapacity];
        for (int i = 0; i < hashCapacity; i++)
            linkedList[i] = new LinkedListClass();
        for (int i = 0; i < data.length; i++) {
            add(data[i]);
        }
    }

    /**
     * A function that resizes our HashTable once we've reached maximum
     */

    private void reHashing(LinkedListClass[] oldHash, int newHashCapacity) {
        LinkedListClass[] newLinkedList = new LinkedListClass[newHashCapacity];
        int oldCapacity = hashCapacity;
        hashCapacity = newHashCapacity;
        for (int i = 0; i < newHashCapacity; i++) {
            newLinkedList[i] = new LinkedListClass();
        }
        for (int j = 0; j < oldCapacity; j++) {
            Iterator<String> currentString = oldHash[j].getLinkedList().iterator();
            while (currentString.hasNext()) {
                String current = currentString.next();
                int index = getIndex(current);
                newLinkedList[index].getLinkedList().add(current);
            }
        }
        linkedList = newLinkedList;
        updateLoadfactor();

    }

    /**
     * Returns the index for a value in our Hashtable.
     *
     * @param value - a value from the HashTable.
     * @return The index for the inserted value.
     */
    private int getIndex(java.lang.String value) {
        return (value.hashCode() & hashCapacity-1);
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue - The new value that one is interested in adding to the HashTable.
     * @return false if element is already in the openHashSet, true otherwise.
     */
    public boolean add(java.lang.String newValue) {
        if (linkedList[getIndex(newValue)].getLinkedList().contains(newValue))
            return false;
        if (upperLoaderFactor < ((float) (elementCounter + 1) / capacity())) {
            reHashing(linkedList, (hashCapacity * 2));
        }
        elementCounter++;
        updateLoadfactor();
        linkedList[getIndex(newValue)].getLinkedList().add(newValue);
        return true;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param searchVal - The value that one is interested in checking if it is found in the HashTable.
     * @return true if element is in the openHashSet, false otherwise.
     */
    public boolean contains(java.lang.String searchVal) {
        int index = getIndex(searchVal);
        return linkedList[index] != null && linkedList[index].getLinkedList().contains(searchVal);
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param toDelete - The value that one is interested in deleteing from the HashTable.
     * @return true if the parameter of "toDelete" was found and deleted, false otherwise.
     */
    public boolean delete(java.lang.String toDelete) {
        if (contains(toDelete)) {
            linkedList[getIndex(toDelete)].getLinkedList().remove(toDelete);
            elementCounter--;
            updateLoadfactor();
            if (((float) (elementCounter - 1) / capacity() < lowerLoadFactor)) {
                reHashing(linkedList, hashCapacity / 2);
            }
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


