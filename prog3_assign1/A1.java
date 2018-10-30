import java.util.*;
/**
 * This program counts the number of times a certain word appears
 * @author Aaron Li-Mai
 * @version 1.0
 * Created by Aaron Li-Mai on Jan 11, 2016
 * Last Modified: Jan 15, 2016
 */
public class A1
{
    private ArrayList <Word> words = new ArrayList <Word> ();
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
        A1 a = new A1 ();
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
                        w.incrementWordCount();
                    }
                    else
                    {
                        words.get(words.indexOf(w)).incrementWordCount();
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
            if (word.matches(stopWords[i]))
            {
                isStopWord = true;
                stopWordCount++;
            }
        }
        return isStopWord;
    }

    /**
     * Prints out the 10 most frequent words from most to least
     */
    private void printMostFrequent ()
    {
        Collections.sort(words, Word.comp);
        Collections.reverse(words);
        for (int i = 0; i < words.size(); i++)
        {
            if (i < 10)
            {
                System.out.println (words.get(i));
            }
        }
    }

    /**
     * Prints out the 10 least frequent words from least to most
     */
    private void printLeastFrequent ()
    {
        Collections.sort(words, Word.descComp);
        for (int i = 0; i < words.size(); i++)
        {
            if (i < 10)
            {
                System.out.println (words.get(i));
            }
        }
    }

    /**
     * Prints out all the words sorted alphabetically
     */
    private void printAllWords ()
    {
        Collections.sort(words);
        for (int i = 0; i < words.size(); i++)
        {
            System.out.println (words.get(i));
        }
    }

    /**
     * Prints out the results of the program
     */
    private void printResults ()
    {
        System.out.println ("Total Words: " + totalWords);
        System.out.println ("Unique Words: " + words.size());
        System.out.println ("Stop Words: " + stopWordCount);
        System.out.println ("\n10 Most Frequent");
        printMostFrequent();
        System.out.println ("\n10 Least Frequent");
        printLeastFrequent();
        System.out.println ("\nAll");
        printAllWords();
    }
}