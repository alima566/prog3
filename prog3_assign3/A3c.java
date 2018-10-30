import java.util.*;
/**
 * Calculates the capital gain/loss of shares
 * @author Aaron Li-Mai
 * @version 1.0
 * Created by: Aaron Li-Mai on Feb 8, 2016
 * Last Modified: Feb 24, 2016
 */
public class A3c
{
    private Queue <Integer> capital= new Queue <Integer> ();

    private Scanner inp = new Scanner (System.in);

    private String type;
    private int numShares;
    private int price;

    private int num;
    public static void main (String[] args)
    {
        A3c a3 = new A3c ();
        a3.run();
    }

    private void run ()
    {
        while (inp.hasNext())
        {
            type = inp.next();
            numShares = inp.nextInt();
            price = inp.nextInt();
            checkValidNum();
            getTransactionType();
        }
        print();
    }
    
    /**
     * Checks to see if the number of share or the price of the stocks are greater than 0
     */
    private void checkValidNum ()
    {
        if (numShares < 0)
        {
            System.err.println ("Number of Shares cannot be negative!");
        }
        else if (price < 0)
        {
            System.err.println ("Price cannot by negative!");
        }
    }

    /**
     * Gets the transaction type: BUY or SELL
     */
    private void getTransactionType ()
    {
        switch (type.toLowerCase())
        {
            case  "buy": addToQueue(); break;
            case "sell": num += sellStocks(); break;
            default: System.err.println (type + " is not a valid transaction type!");
        }
    }

    /**
     * Adds the stocks into the queue
     */
    private void addToQueue ()
    {
        for (int i = 0; i < numShares; i++)
        {
            capital.enqueue(price);
        }
    }

    /**
     * Calculates how many stocks were sold
     */
    private int sellStocks ()
    {
        int gain = 0;
        if (capital.size() < numShares)
        {
            System.err.println ("You don't have " + numShares + " shares to sell!");
        }
        else
        {
            int sum = 0;
            for (int i = 0; i < numShares; i++)
            {
                sum += capital.dequeue();
            }
            gain = (numShares * price) - sum;
        }
        return gain;
    }

    /**
     * Prints out if it's a capital gain or loss and how much
     */
    private void print ()
    {
        System.out.println ((num < 0) ? "Capital Loss: " + Math.abs(num) : "Capital Gain: " + num);
    }
}