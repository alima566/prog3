import java.util.*;
/**
 * This class reads in two long integers and adds them using a stack
 * @author Aaron Li-Mai
 * @version 1.0
 * Created by Aaron Li-Mai on Feb 8, 2016
 * Last Modified: Feb 22, 2016
 */
public class A3b
{
    private Stack <Integer> firstNum = new Stack <Integer> (); //Holds the first set of numbers to add
    private Stack <Integer> secondNum = new Stack <Integer> (); //Holds the second set of numbers to add
    private Stack <Integer> total = new Stack <Integer> (); //Holds the total after adding the two stacks together

    private Scanner inp = new Scanner (System.in);

    private String num1; //First number to add
    private String num2; //Second number to add
    public static void main (String[] args)
    {
        A3b a3 = new A3b ();
        a3.run();
    }

    private void run ()
    {
        while (inp.hasNext())
        {
            num1 = inp.next();
            num2 = inp.next();
            checkIfValid();
        }
    }

    /**
     * Checks if the number entered is valid or not
     */
    private void checkIfValid ()
    {
        if (isDigit(num1) && isDigit(num2))
        {
            addToStack();
            printResults();
        }
        else 
        {
            if (!isDigit(num1) && !isDigit(num2))
            {
                System.err.println (num1 + " and " + num2 + " are not numbers!\n");
            }
            else if (!isDigit(num1))
            {
                System.err.println (num1 + " is not a number!\n");
            }
            else if (!isDigit(num2))
            {
                System.err.println (num2 + " is not a number!\n");
            }
        }
    }

    /**
     * Adds the numbers into the stacks
     */
    private void addToStack ()
    {
        makeEqualSize();
        for (int i = 0; i < num1.length(); i++)
        {
            firstNum.push(Character.getNumericValue(num1.charAt(i)));
        }
        for (int j = 0; j < num2.length(); j++)
        {
            secondNum.push(Character.getNumericValue(num2.charAt(j)));
        }
    }

    /**
     * Checks to see if the number entered is valid number or not
     * @param number The number to check
     * @return True if it's a valid number; false otherwise
     */
    private boolean isDigit (String number)
    {
        return number.matches("[0-9]+");
    }

    /**
     * Gets the sum of the two stacks
     */
    private void getSum ()
    {
        int carry = 0, num1, num2, sum;
        while (!firstNum.isEmpty() && !secondNum.isEmpty())
        {
            num1 = firstNum.pop();
            num2 = secondNum.pop();
            if (num1 < 0)
            {
                num1 = 0;
            } 
            else if (num2 < 0)
            {
                num2 = 0;
            }
            sum = (num1 + num2 + carry);
            carry = sum/10; //Calculates the carry, if any, and stores it for the next two numbers to be added to
            if (sum > 9)
            {
                if (!firstNum.isEmpty() || !secondNum.isEmpty())
                {
                    sum -= 10;
                }
            }
            total.push(sum);
        }
    }

    /**
     * Formats the output
     * @param result The total
     * @return The number to format the result
     */
    private String formatOutput (String result)
    {
        return "%" + result.length() + "s%n";
    }

    /**
     * Prints out the total after adding the two stacks
     * @return The total
     */
    private String printTotal ()
    {
        getSum();
        String totalNum = "";
        while (!total.isEmpty())
        {
            totalNum += total.pop();
        }
        return totalNum;
    }

    /**
     * Makes the two strings equal size if they're different
     */
    private void makeEqualSize ()
    {
        int diff = Math.abs(num1.length() - num2.length()); //Takes the absolute difference of the two lengths
        String space = "";
        for (int i = 0; i < diff; i++)
        {
            space += " ";
        }
        if (num1.length() < num2.length())
        {
            num1 = space + num1;
        }
        else
        {
            num2 = space + num2;
        }
    }

    /**
     * Prints the results
     */
    private void printResults ()
    {
        String result = printTotal();

        System.out.printf ("   " + formatOutput(result), num1);
        System.out.printf (" + " + formatOutput(result), num2);
        System.out.printf (" = " + formatOutput(result), result);
        System.out.println ();
    }
}