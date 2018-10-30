import java.util.*;
/**
 * This program counts the number of times a certain word appears
 * @author Aaron Li-Mai
 * @version 2.0
 * Created by Aaron Li-Mai on Jan 11, 2016
 * Last Modified: Jan 16, 2016 (As Assignment 1)
 *                Feb  2, 2016 (As Assignment 2)
 */
public class A2
{    
    private SLL <Word> words = new SLL <Word> ();
    private SLL <Word> mostFreq = new SLL <Word> ();
    private SLL <Word> leastFreq = new SLL <Word> ();

    private Scanner input;

    private Word w;

    private int totalWords;
    private int stopWordCount;

    private String[] stopWords = {
                                    "a", "about", "all", "am", "an", "and", "any", "are", "as", "at", "be",
                                    "been", "but", "by", "can", "cannot", "could", "did", "do", "does",
                                    "else", "for", "from", "get", "got", "had", "has", "have", "he", "her",
                                    "hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", "its", "like",
                                    "more", "me", "my", "no", "now", "not", "of", "on", "one", "or", "our", "out",
                                    "said", "say", "says", "she", "so", "some", "than", "that", "the", "their",
                                    "them", "then", "there", "these", "they", "this", "to", "too", "us", "upon",
                                    "was", "we", "were", "what", "with", "when", "where", "which", "while", "who",
                                    "whom", "why", "will", "you", "your"
                                 };
    public static void main (String[] args)
    {
        A2 a = new A2 ();
        a.run();
    }

    /**
     * Executes the main flow of the word frequency program
     */
    private void run ()
    {
        input = new Scanner (System.in);
        while (input.hasNext())
        {
            String word = input.next().toLowerCase().trim().replaceAll("[\\W\\d]|_", "");
            w = new Word(word);
            if (word.length() > 0)
            {
                if (!isStopWord(word))
                {
                    if (!words.contains(w))
                    {   
                        words.add(w);
                        w.incrementWordCount(); //Increments the word count for that word
                    }
                    else
                    {
                        words.get(words.indexOf(w)).incrementWordCount(); //Gets where the word is located in the list and increments the count
                    }
                }
                totalWords++;
            }
        }
        printResults();
    }

    /**
     * Checks to see if a word is a stop word
     * @param word The word to check
     * @return True if the word is a stop word; false otherwise
     */
    private boolean isStopWord (String word)
    {
        boolean isStopWord = false;
        for (int i = 0; i < stopWords.length; i++)
        {
            if (word.equals(stopWords[i]))
            {
                isStopWord = true;
                stopWordCount++;
            }
        }
        return isStopWord;
    }

    /**
     * Orders the existing word list by word frequency
     */
    private void orderByWordFreq ()
    {
        for (int i = 0; i < words.size(); i++)
        {
            mostFreq.add(words.get(i), Word.ascComp); //Adds the words into the list from most to least
            leastFreq.add(words.get(i), Word.descComp); //Adds the words into the list from least to most
        }
    }

    /**
     * Prints out the results of the program
     */
    private void printResults ()
    {
        orderByWordFreq();
        System.out.println ("Total Words: " + totalWords);
        System.out.println ("Unique Words: " + words.size());
        System.out.println ("Stop Words: " + stopWordCount);
        System.out.println ("\n10 Most Frequent");
        mostFreq.printList(10);
        System.out.println ("\n10 Least Frequent");
        leastFreq.printList(10);
        System.out.println ("\nAll");
        words.printList(words.size());
    }
}