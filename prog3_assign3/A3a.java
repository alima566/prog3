import java.io.*;
/**
 * This class checks to see if a word is a palindrome or not using a stack
 * @author Aaron Li-Mai
 * @version 1.0
 * Created by Aaron Li-Mai on Feb  8, 2016
 * Last Modified: Feb 18, 2016
 */
public class A3a
{
    private Stack <Character> original = new Stack <Character> (); //Holds a reference to the original stack
    private Stack <Character> forward = new Stack <Character> (); //Reads the word character by character normally
    private Stack <Character> reverse = new Stack <Character> (); //Reads the word character by character in reverse

    private BufferedReader inp = new BufferedReader (new InputStreamReader (System.in));

    private int value;
    public static void main (String[] args)
    {
        A3a a3 = new A3a ();
        a3.run();
    }

    private void run ()
    {
        try
        {
            while ((value = inp.read()) != -1)
            {
                if (!isWhiteSpace())
                {
                    while (!isWhiteSpace())
                    {
                        char letter = (char)value;
                        original.push(letter);
                        reverse.push(original.peek());
                        System.out.print (letter);
                        value = inp.read();
                    }
                    printResult();
                }
            }
        }
        catch (IOException error)
        {
            System.out.println ("There was an error with reading the file");
        }
    }

    /**
     * Checks to see if the value read in is a whitespace character
     * @return True if the character is a whitespace; false otherwise
     */
    private boolean isWhiteSpace ()
    {
        return (value >= 9 && value <= 13 || value == 32);
    }

    /**
     * Checks to see if the word is a palindrome
     * @return True if the word is a palindrome; false otherwise
     */
    private boolean isPalindrome ()
    {
        boolean isPal = true;
        while (!original.isEmpty())
        {
            forward.push((char)original.pop());
        }
        while (!forward.isEmpty() || !reverse.isEmpty())
        {
            if (!(forward.pop().compareTo(reverse.pop()) == 0))
            {
                isPal = false;
            }
        }
        return isPal;
    }

    /**
     * Prints out yes or no depending on if it's a palindrome or not
     */
    private void printResult ()
    {
        System.out.println ((isPalindrome() ? ": Yes" : ": No"));
    }
} //End class A3a