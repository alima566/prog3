import java.util.Comparator;
/**
 * Represents a singly linked list
 * @author Aaron Li-Mai
 * @version 2.0
 * Created by Aaron Li-Mai on Jan 22, 2016
 * Last Modified: Feb  2, 2016 (As Assignment 2)
 *                Feb 11, 2016 (As Assignment 3)
 */
public class SLL <T extends Comparable <T>>
{
    /**
     * Represents a single node object
     * @author Aaron Li-Mai
     * @version 1.0
     * Created by Aaron Li- Mai on Jan 22, 2016
     * Last Modified: Jan 22, 2016 (As Assignment 2)
     */
    public class Node <T extends Comparable <T>>
    {
        private T data;
        private Node <T> next;
        public Node (T data)
        {
            this.setData(data);
            this.setNext(next);
        }

        public T getData () { return data; }

        public void setData (T data) { this.data = data; }

        public Node <T> getNext () { return next; }

        public void setNext (Node <T> next) { this.next = next; }

        public String toString () { return getData().toString(); }
    } //End class Node
    public Node <T> head, tail;
    private int listSize; //The size of the list
    public SLL ()
    {
        head = null;
        listSize = 0;
    }

    /**
     * Gets the size of the linked list
     * @return The size of the linked list
     */
    public int size ()
    {
        return listSize;
    }

    /**
     * Adds an object to the head
     * @param data The data to add
     */
    public void addHead (T data)
    {
        Node <T> newNode = new Node <T> (data);
        addHead(newNode);
    }
    
    /**
     * Adds an object to the tail
     * @param data The data to add
     */
    public void addTail (T data)
    {
        Node <T> newNode = new Node <T> (data);
        addTail(newNode);
    }
    
    /**
     * Deletes the head from the list
     * @return The reference of the deleted object
     */
    public T deleteHead ()
    {
        Node <T> n = delHead();
        return (n == null) ? null : n.getData(); 
    }
    
    /**
     * Adds a head to the linked list
     * @param newNode The new node to add
     */
    private void addHead (Node <T> newNode)
    {
        if (head == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.setNext(head);
            head = newNode;
        }
        listSize++;
    }

    /**
     * Adds a tail to the linked list
     * @param newNode The new node to add
     */
    private void addTail (Node <T> newNode)
    {
        if (head == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.setNext(newNode);
            tail = newNode;
        }
        listSize++;
    }
    
    /**
     * Checks to see if the linked list is empty or not
     * @return True if the list is empty; false otherwise
     */
    public boolean isEmpty ()
    {
        boolean empty = false;
        if (head == null)
        {
            empty = true;
        }
        return empty;
    }

    /**
     * Adds a new object to the linked list alphabetically
     * @param obj The new object to add
     */
    private void add (T obj)
    {
        Node <T> curr = head;
        Node <T> newNode = new Node <T> (obj);
        if (head == null)
        {
            addHead(newNode);
        }
        else
        {
            if (newNode.getData().compareTo(curr.getData()) < 0)
            {
                addHead(newNode);
            }
            else
            {
                while (curr.getNext() != null && newNode.getData().compareTo(curr.getNext().getData()) > 0)
                {
                    curr = curr.getNext();
                }
                if (curr.getNext() == null)
                {
                    addTail(newNode);
                }
                else
                {
                    newNode.setNext(curr.getNext());
                    curr.setNext(newNode);
                }
            }
        }
        listSize++;
    }

    /**
     * Adds a object to the linked list sorting it by word count frequency
     * @param obj The new object to add
     * @param comp The Comparator object
     */
    public void add (T obj, Comparator <T> comp)
    {
        Node <T> curr = head;
        Node <T> newNode = new Node <T> (obj);
        if (head == null)
        {
            addHead(newNode);
        }
        else
        {
            if (comp.compare(newNode.getData(), curr.getData()) < 0)
            {
                addHead(newNode);
            }
            else
            {
                while (curr.getNext() != null && comp.compare(newNode.getData(), curr.getNext().getData()) > 0)
                {
                    curr = curr.getNext();
                }
                if (curr.getNext() == null)
                {
                    addTail(newNode);
                }
                else
                {
                    newNode.setNext(curr.getNext());
                    curr.setNext(newNode);
                }
            }
        }
    }

    /**
     * Gets a specific element in the linked list at a certain spot
     * @param index The spot to check
     * @return The object at the spot
     */
    public T get (int index)
    {
        Node <T> curr = head;
        int i = 0;
        while (curr != null && i != index)
        {
            curr = curr.getNext();
            i++;
        }
        if (i == index) //If i is equal to the index passed in
        {
            return curr.getData(); //Returns the object at that index
        }
        else
        {
            return null;
        }
    }

    /**
     * Gets the location of the first occurence of the data in the linked list
     * @param data The data to check
     * @return Returns the index of the first occurence of the object; -1 if it doesn't exist
     */
    public int indexOf (T data)
    {
        Node <T> curr = head;
        int i = 0;
        while (curr != null && !curr.getData().equals(data))
        {
            curr = curr.getNext();
            i++;
        }
        if (curr == null)
        {
            return -1;
        }
        else
        {
            return i;
        }
    }

    /**
     * Checks to see if the linked list already contains the object
     * @param data The object to check
     * @return True if the linked list already contains the object; false otherwise
     */
    public boolean contains (T data)
    {
        boolean exists = false;
        Node <T> curr = head;
        while (curr != null)
        {
            if (curr.getData().equals(data))
            {
                exists = true;
            }
            curr = curr.getNext();
        }
        return exists;
    }

    private Node <T> delHead () 
    {
        Node <T> n = null;
        if (head != null) 
        {
            n = head;
            if (head == tail)
            {
                tail = head.getNext();
            }
            head = head.getNext();
            listSize--;
        }
        return n;
    }
} //End class SLL