import java.util.*;
/**
 * This program counts the number of times a certain word appears
 * @author Aaron Li-Mai
 * @version 3.0
 * Created by Aaron Li-Mai on Jan 11, 2016
 * Last Modified: Jan 16, 2016 (As Assignment 1)
 *                Feb  2, 2016 (As Assignment 2)
 *                Mar 18, 2016 (As Assignment 5)
 */
public class A5
{    
    private final static int NUM_WORDS = 20; //The number of words to print

    private BST <Word> words = new BST <Word> (); //Alphabetic tree
    private BST <Word> freq = new BST <Word> (); //Frequency tree
    private BST <Word> wordLength = new BST <Word> (); //Length tree

    private Scanner input;

    private Word w;

    private int totalWords;
    private int stopWordCount;

    private String[] stopWords = { 
                                    "a", "about",  "all", "am", "an", "and",  "any", "are", "as", "at", 
                                    "be", "been", "but", "by", "can", "cannot", "could", "did", "do", "does", 
                                    "else", "for", "from", "get", "got", "had", "has", "have", "he", "her", 
                                    "hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", 
                                    "its", "like", "more", "me", "my", "no", "now", "not", "of", "on", 
                                    "one", "or", "our", "out", "said", "say", "says", "she", "so", "some",
                                    "than", "that", "thats", "the", "their", "them", "then", "there", "these", "they", "this", 
                                    "to", "too", "us", "upon", "was", "we", "were", "what", "with", "when", 
                                    "where", "which", "while", "who", "whom", "why", "will", "you", "your", "up", "down", "left", "right",
                                    "man", "woman", "would", "should", "dont", "after", "before", "im", "men"
                                 };
    public static void main (String[] args)
    {
        A5 a = new A5 ();
        a.run();
    }

    /*
     * Executes the main flow of the word frequency program
     */
    private void run ()
    {
        input = new Scanner (System.in);
        while (input.hasNext())
        {
            String word = input.next().toLowerCase().trim().replaceAll("[\\W\\d]|_", "");
            w = new Word (word);
            if (word.length() > 0)
            {
                if (!words.contains(w))
                {   
                    words.add(w);
                }
                else
                {
                    words.get(w).incrementWordCount(); //Gets where the word is located in the tree and increments the count
                }
                totalWords++;
            }
        }
        deleteStopWords();
        makeMoreTrees();
        printResults();
    }

    /*
     * Iterates through the BST and deletes all stop words from the tree
     */
    private void deleteStopWords ()
    {
        Iterator <Word> it = words.iterator();
        Arrays.sort(stopWords);
        while (it.hasNext())
        {
            w = it.next();
            if (isStopWord(w.getWord(), 0, stopWords.length - 1))
            {
                words.delete(w);
            }       
        }
    }

    /*
     * Checks to see if a word is a stop word or not by doing a binary search
     */
    private boolean isStopWord (String word, int start, int end)
    {
        if (start <= end)
        {
            int mid = (start + end) / 2;
            if (word.compareTo(stopWords[mid]) > 0)
            {
                return isStopWord(word, mid + 1, end);
            }
            else if (word.compareTo(stopWords[mid]) < 0)
            {
                return isStopWord(word, start, mid - 1);
            }
            else
            {
                stopWordCount++;
                return true;
            }
        }
        return false;
    }

    /*
     * Makes a frequency tree with words that have a word count of more than two and a word length tree
     */
    private void makeMoreTrees ()
    {
        for (Word w : words)
        {
            if (w.getWordCount() > 2)
            {
                freq.add(w, Word.ascComp); //Adds the words into the freq tree based on their word count in ascending order
            }
            wordLength.add(w, Word.wordLength); //Adds the words into the wordLength tree based on their lengths in descending order
        }
    }

    /*
     * Gets the optimum height of the tree by taking the log of the size of the tree and dividing it with log2
     */
    private int getOptimumHeight (BST <Word> wrds)
    {
        int opt = (int)(Math.round(Math.log(wrds.size()) / Math.log(2)));
        return (opt < 0) ? 0 : opt;
    }

    /*
     * Gets the average word length of a tree by summing up all the word lengths and dividing it by the size of the tree
     */
    private int averageWordLength (BST <Word> wrds)
    {
        Iterator <Word> it = wrds.iterator();
        int sum = 0;
        while (it.hasNext())
        {
            sum += it.next().getLength();
        }
        int avg = (wrds.size() > 0) ? sum/wrds.size() : 0;
        return avg;
    }

    /*
     * Iterates through the tree and finds the longest word
     */
    private Word longestWord ()
    {
        Iterator <Word> it = wordLength.iterator();
        Word longest = null;
        int max = 0;
        while (it.hasNext())
        {
            w = it.next();
            if (w.getWord().length() > max)
            {
                max = w.getWord().length();
                longest = w;
            }
        }
        return longest;
    }

    /*
     * Prints out the first N elements of the tree
     */
    private void printFirstN (int n, BST <Word> wrds)
    {
        Iterator <Word> it = wrds.iterator();
        for (int i = 0; i < n && it.hasNext(); i++)
        {
            System.out.println (it.next());
        }
    }

    /*
     * Prints out all the elements of the tree
     */
    private void printAll (BST <Word> wrds)
    {
        printFirstN(wrds.size(), wrds);
    }

    /*
     * Prints out the results of the program
     */
    private void printResults ()
    {
        System.out.println ("\n------\n");
        System.out.println ("Total Words: " + totalWords);
        System.out.println ("Stop Words: " + stopWordCount);
        System.out.println ("Unique Words: " + words.size());
        System.out.println ("\n------\n");
        System.out.println (NUM_WORDS + " Most Frequent");
        printFirstN(NUM_WORDS, freq);
        System.out.println ("\n------\n");
        System.out.println (NUM_WORDS + " Least Frequent");
        freq.mirror();
        printFirstN(NUM_WORDS, freq);
        System.out.println ("\n------\n");
        System.out.println ("The longest word is " + longestWord());
        System.out.println ("The average word length is " + averageWordLength(words));
        System.out.println ("\n------\n");
        System.out.println ("All Words");
        printAll(words);
        printTreeHeights();
    }

    /*
     * Prints out the optimum & actual heights of all the trees
     */
    private void printTreeHeights ()
    {
        System.out.println ("\n------\n");
        System.out.println ("Alphabetic Tree: ( Optimum Height: " + getOptimumHeight(words) + ") ( Actual Height: " + words.height() + ")"); 
        System.out.println ("Frequency Tree: ( Optimum Height: " +  getOptimumHeight(freq) + ") ( Actual Height: " + freq.height() + ")"); 
        System.out.println ("Length Tree: ( Optimum Height: " + getOptimumHeight(wordLength) + ") ( Actual Height: " + wordLength.height() + ")"); 
        System.out.println ("\n------\n");
    }
}