import java.util.*;
/**
 * This program determines the number of white areas and the number of cells that make up each area in an n x n square grid
 * @author Aaron Li-Mai
 * @version 1.0
 * Created by: Aaron Li-Mai on Feb 24, 2016
 * Last Modified: Mar 1, 2016
 */
public class A4
{
    private int[][] grid;

    private int size;
    private int row;
    private int col;

    private int count = 0;

    private Scanner inp = new Scanner (System.in);
    public static void main (String[] args)
    {
        A4 a4 = new A4 ();
        a4.run();
    }

    private void run ()
    {
        size = inp.nextInt();
        grid = new int[size][size];
        while (inp.hasNext())
        {
            if (col == size)
            {
                row++; //Jumps to the next row
                col = 0; //And resets the col back to 0
            }
            if (row == size)
            {
                break;
            }
            grid[row][col++] = inp.nextInt();
        }
        print();
    }

    /**
     * Calculates recursively, the total white area in an n x n square grid
     * @param r The row location
     * @param c The col location
     * @return 0 if it matches the base cases; otherwise returns the area of the white area
     */
    private int calculateWhiteArea (int r, int c)
    {
        if (!inBounds(r, c) || grid[r][c] != 1) //Base case
        {
            return 0;
        }
        else
        {
            grid[r][c]++; //Increments the one to a two, so it doesn't double count
            return (calculateWhiteArea(r + 1, c) + 1) + (calculateWhiteArea(r - 1, c)) + 
                   (calculateWhiteArea(r, c + 1)) + (calculateWhiteArea(r, c - 1)); //Checks up, down, left and right for any white areas
        }
    }

    /**
     * Checks to see if the square is in bounds or not
     * @param row The row location
     * @param col The col location
     * @return True if the square is in bounds; false otherwise
     */
    private boolean inBounds (int row, int col)
    {
        return ((row >= 0 && row < size) && (col >= 0 && col < size));
    }

    /**
     * Prints the result out to the console
     */
    private void print ()
    {
        int num = 0;
        int[] numAreas = new int[(size * size) + 1];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                num = calculateWhiteArea(i, j);
                if (num != 0)
                {
                    count++;
                    numAreas[num]++;
                }
            }
        }
        System.out.println ("There are " + count + " white areas");
        System.out.print (getNumArea(numAreas));
    }

    /**
     * Prints out how many times a white area of the same size occured
     * @param numAreas The 1D array that contains the white areas
     * @return The result of how many times a white area of the same size occured
     */
    private String getNumArea (int[] numAreas)
    {
        String print = "";
        for (int i = numAreas.length- 1; i >= 0; i--)
        {
            if (numAreas[i] != 0)
            {
                print += numAreas[i] + " x " + i + "\n";
            }
        }
        return print;
    }
}