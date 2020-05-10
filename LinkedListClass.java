import java.util.LinkedList;

/**
 * The WrapperClass inherits all its characteristics from LinkedList.
 */
public class LinkedListClass extends LinkedList<String> {
    LinkedList<String> linkedList;

    /**
     * Creates a new instance of LinkedList.
     */
    public LinkedListClass() {
        this.linkedList = new LinkedList<String>();
    }

    /**
     * A getter function -
     * @return linkedlist - our linkedlist instance.
     */
    public LinkedList<String> getLinkedList() { return linkedList;}

}
