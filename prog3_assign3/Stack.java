/**
 * Represents a single stack object
 * @author Aaron Li-Mai
 * @version 1.0
 * Created by Aaron Li-Mai on Feb 8, 2016
 * Last Modified: Feb 10, 2016
 */
public class Stack <T extends Comparable <T>> extends SLL <T>
{
    /**
     * Adds an element to the top of the stack
     * @param The data to add
     */
    public void push (T data)
    {
        addHead(data);
    }
    
    /**
     * Removes an element from the top of the stack
     * @return The element removed
     */
    public T pop ()
    {
        return deleteHead();
    }
    
    /**
     * Checks to see what the element is at the top of the stack
     * @return The element at the top
     */
    public T peek ()
    {
        return head.getData();
    }
    
    /**
     * Checks to see if the stack is empty
     * @return True if the stack is empty; false otherwise
     */
    public boolean isEmpty ()
    {
        return super.isEmpty();
    }
    
    /**
     * The size of the stack
     * @return The size
     */
    public int size ()
    {
        return super.size();
    }
}