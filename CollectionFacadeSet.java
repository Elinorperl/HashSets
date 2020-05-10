public class CollectionFacadeSet implements SimpleSet {

    protected java.util.Collection<java.lang.String> hashCollection;

    CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {
        hashCollection = collection;
    }
    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue - New value to add to the set
     * @return False if newValue already exists in the set, true otherwise,
     */
    public boolean add(java.lang.String newValue) {
        if (contains(newValue)) {
            return false;
        } else {
            hashCollection.add(newValue);
        }return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal - Value to search for
     * @return True if searchVal is found in the set, false otherwise
     */
    public boolean contains(java.lang.String searchVal) {
        if (hashCollection.contains(searchVal))
            return true;
        return false;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete - Value to delete
     * @return True if toDelete is found and deleted, false otherwise
     */
    public boolean delete (java.lang.String toDelete) {
        if (contains(toDelete)) {
            hashCollection.remove(toDelete);
            return true;
        }return false;
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size () { return hashCollection.size();}
}
