import java.util.*;
/**
 * Represents a Binary Search Tree 
 * @author Aaron Li-Mai
 * @version 1.0
 * Created by Aaron Li-Mai on Mar 12, 2016
 * Last Modified: Mar 16, 2016
 */
public class BST <T extends Comparable <T>> implements Iterable <T>
{
    /**
     * Represents a single BST node object
     * @author Aaron Li-Mai
     * @version 1.0
     * Created by Aaron Li-Mai on Mar 12, 2016
     * Last Modified: Mar 12, 2016
     */
    private class BSTNode implements Comparable <BSTNode>
    {
        private BSTNode left, right;
        private T data;
        public BSTNode (T data)
        {
            this.setRight(right);
            this.setLeft(left);
            this.setData(data);
        }

        public BSTNode getRight () { return right; }

        public void setRight (BSTNode right) { this.right = right; }

        public BSTNode getLeft () { return left; }

        public void setLeft (BSTNode left) { this.left = left; }

        public T getData () { return data; }

        public void setData (T data) { this.data = data; }

        public boolean isLeaf () { return (getRight() == null) && (getLeft() == null); }

        public int compareTo (BSTNode o) { return this.getData().compareTo(o.getData()); }
    }

    /** Publicly accessible methods */
    private BSTNode root;
    public BST () { root = null; }

    /**
     * An iterator that iterates through a tree using in-order tree traversal allowing a sorted sequence
     * @return An iterator
     */
    public Iterator <T> iterator ()
    {
        Iterator <T> it = new Iterator <T> ()    
            {
                private Stack <BSTNode> stk = new Stack <BSTNode> ();
                private BSTNode curr = root;
                public boolean hasNext ()
                {
                    return !stk.isEmpty() || curr != null;
                }

                public T next ()
                {
                    while (curr != null)
                    {
                        stk.push(curr);
                        curr = curr.getLeft();
                    }
                    curr = stk.pop();
                    T t = curr.getData();
                    curr = curr.getRight();
                    return t;
                }
            };
        return it;
    }

    /**
     * Adds a new object to the tree
     * @param data The data to add
     */
    public void add (T data)
    {
        if (root == null)
        {
            root = new BSTNode (data);
        }
        else
        {
            add(root, new BSTNode (data), null);
        }
    }

    /**
     * Adds a new object to the tree
     * @param data The data to add
     * @param comp The comparator object
     */
    public void add (T data, Comparator <T> comp)
    {
        if (root == null)
        {
            root = new BSTNode (data);
        }
        else
        {
            add(root, new BSTNode (data), comp);
        }
    }

    /**
     * Checks to see if the tree already contains the object
     * @param data The data to check
     * @return True if the tree already contains the object; false otherwise
     */
    public boolean contains (T data) { return contains(root, data); }

    /**
     * Deletes an object from the tree
     */
    public void delete (T data) { root = delete(root, data); }

    /**
     * Gets the size (how many nodes there are) of the tree
     * @return The total size of the tree
     */
    public int size () { return size(root); }

    /**
     * Gets an object from a certain spot in the tree
     * @param data The data to get
     * @return The object 
     */
    public T get (T data) { return get(root, data); }

    /**
     * Gets the total height of the tree
     * @return The total height of the tree
     */
    public int height () { return height(root); }

    /**
     * Gets the mirror image of the existing tree
     */
    public void mirror () { mirror(root); }

    /** Private helper methods */
    /*
     * A private helper method for the add method that does much of the processing by determining
     * where in the tree a certain node goes
     */
    private void add (BSTNode r, BSTNode n, Comparator <T> comp)
    {
        if (compareNodes(n, r, comp) > 0)
        {
            if (r.getRight() != null)
            {
                add(r.getRight(), n, comp);
            }
            else
            {
                r.setRight(n);
            }
        }
        else if (compareNodes(n, r, comp) < 0)
        {
            if (r.getLeft() != null)
            {
                add(r.getLeft(), n, comp);
            }
            else
            {
                r.setLeft(n);
            }
        }
    }

    /*
     * A private helper method for the contains method that does much of the processing by determining
     * if the tree contains a certain object or not
     */
    private boolean contains (BSTNode r, T data)
    {
        if (r == null)
        {
            return false;
        }
        else if (data.compareTo(r.getData()) > 0)
        {
            return contains(r.getRight(), data);
        }
        else if (data.compareTo(r.getData()) < 0)
        {
            return contains(r.getLeft(), data);
        }
        else
        {
            return true;
        }
    }

    /*
     * A private helper method for the delete method that does much of the processing by searching
     * through the entire tree and deleting the specified object
     */
    private BSTNode delete (BSTNode r, T data)
    {
        if (r == null)
        {
            return r;
        }
        else if (data.compareTo(r.getData()) > 0)
        {
            r.setRight(delete(r.getRight(), data));
        }
        else if (data.compareTo(r.getData()) < 0)
        {
            r.setLeft(delete(r.getLeft(), data));
        }
        else //Found the node to delete
        {
            if (r.isLeaf()) //r has no child
            {
                r = null;
            }
            else if (r.getLeft() == null) //r has one child on the right
            {
                r = r.getRight(); //Sets its parent pointer to the right child
            }
            else if (r.getRight() == null) //r has one child on the left
            {
                r = r.getLeft(); //Sets its parent pointer to the left child
            }
            else //r has two children
            {
                r.setData(min(r.getLeft())); //Get data from the right least node in the left subtree
                r.setLeft(delete(r.getLeft(), r.getData())); //Deletes the right least node in the left subtree
            }
        }
        return r;
    }

    /*
     * Retrieves the data from the right least node
     */
    private T min (BSTNode r)
    {
        while (r.getRight() != null)
        {
            r = r.getRight();
        }
        return r.getData();
    }

    /*
     * A private helper method for the size method that does much of the processing by determining
     * the total size (how many nodes there are) of the tree
     */
    private int size (BSTNode r)
    {
        return (r == null) ? 0 : 1 + size(r.getLeft()) + size(r.getRight());
    }

    /*
     * A private helper method for the get method that does much of the processing by getting
     * a certain object from a certain spot in the tree
     */
    private T get (BSTNode r, T data)
    {
        if (r == null)
        {
            return null;
        }
        else if (data.compareTo(r.getData()) > 0)
        {
            return get(r.getRight(), data);
        }
        else if (data.compareTo(r.getData()) < 0)
        {
            return get(r.getLeft(), data);
        }
        else
        {
            return r.getData();
        }
    }

    /*
     * A private helper method for the height method that does much of the processing by determining
     * the height of the tree by getting the maximum height from the right and left side of the tree
     */
    private int height (BSTNode r)
    {
        return (r == null) ? 0 : 1 + Math.max(height(r.getLeft()), height(r.getRight()));
    }

    /*
     * A private helper method for the mirror method that does much of the processing by flipping
     * the tree into its mirror image
     */
    private void mirror (BSTNode r)
    {
        if (r != null)
        {
            mirror(r.getLeft());
            mirror(r.getRight());
            BSTNode temp = r.getLeft();
            r.setLeft(r.getRight());
            r.setRight(temp);
        }
    }

    /*
     * Compares the data in two nodes, either by using the Comparator comp or by the natural ordering
     */
    private int compareNodes (BSTNode n, BSTNode r, Comparator <T> comp) 
    {
        int diff = (comp == null) ? n.compareTo(r) : comp.compare(n.getData(), r.getData());
        return diff;
    }
}