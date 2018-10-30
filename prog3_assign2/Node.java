/**
 * Represents a single node object
 * @author Aaron Li-Mai
 * @version 1.0
 * Created by Aaron Li-Mai on Jan 22, 2016
 * Last Modified: Jan 22, 2016
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
    
    public T getData ()
    {
        return data;
    }
    
    public void setData (T data)
    {
        this.data = data;
    }
    
    public Node <T> getNext ()
    {
        return next;
    }
    
    public void setNext (Node <T> next)
    {
        this.next = next;
    }
    
    public String toString ()
    {
        return getData().toString();
    }
}