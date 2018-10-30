/**
 * Represents a single queue object
 * @author Aaron Li-Mai
 * @version 1.0
 * Created by Aaron Li-Mai on Feb 8, 2016
 * Last Modified: Feb 10, 2016
 */
public class Queue <T extends Comparable <T>> extends SLL <T>
{
    /**
     * Removes an element from the front (head) of the queue
     * @return The removed element
     */
    public T dequeue ()
    {
        return deleteHead();
    }
    
    /**
     * Adds an element to the end (tail) of the queue
     * @param data The data to add
     */
    public void enqueue (T data)
    {
        addTail(data);
    }
    
    /**
     * Checks to see what the element is at the front of the queue
     * @return The element at the front
     */
    public T peek ()
    {
        return head.getData();
    }
    
    /**
     * Checks to see if the queue is empty or not
     * @return True if the queue is empty; false otherwise
     */
    public boolean isEmpty ()
    {
        return super.isEmpty();
    }
}