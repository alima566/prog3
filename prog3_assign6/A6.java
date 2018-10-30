import java.util.*;
/**
 * This program counts the number of times a certain word appears
 * @author Aaron Li-Mai
 * @version 4.0
 * Created by Aaron Li-Mai on Jan 11, 2016
 * Last Modified: Jan 16, 2016 (As Assignment 1)
 *                Feb  2, 2016 (As Assignment 2)
 *                Mar 18, 2016 (As Assignment 5)
 *                Apr 12, 2016 (As Assignment 6)
 */
public class A6
{    
    private final static int NUM_WORDS = 20; //The number of words to print

    private HashMap <String, Word> hm = new HashMap <> ();

    private TreeMap <Word, Word> alpha = new TreeMap <> (); //Alphabetic tree
    private TreeMap <Word, Word> mostFreq = new TreeMap <> (Word.ascComp); //Word frequency tree
    private TreeMap <Word, Word> length = new TreeMap <> (Word.wordLength); //Word length tree

    private Scanner input;

    private Word w;

    private int totalWords = 0;
    private int stopWordCount = 0;

    private int wordCountTotal = 0;
    private int totalLength = 0;

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
        A6 a = new A6 ();
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
                if (!hm.containsValue(w))
                {   
                    hm.put(word, w);
                }
                else
                {
                    hm.get(word).incrementWordCount(); //Gets where the word is located in the HashMap and increments the count
                }
                totalWords++;
            }
        }
        deleteStopWords();
        createTrees();
        printResults();
    }

    /*
     * Iterates through the HashMap and deletes all stop words from it
     */
    private void deleteStopWords ()
    {
        Iterator <Map.Entry <String, Word>> it = hm.entrySet().iterator();
        Arrays.sort(stopWords); //Sorts the array of stop words alphabetically so we can do a binary search 
        while (it.hasNext())
        {
            Map.Entry <String, Word> words = it.next();
            if (isStopWord(words.getKey(), 0, stopWords.length - 1))
            {
                it.remove();
                hm.remove(words.getKey());
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
     * Gets the average word length of the TreeMap
     */
    private int averageWordLength ()
    {
        return (length.size() > 0) ? totalLength/length.size() : 0;
    }

    /*
     * Gets the average word frequency of the TreeMap
     */
    private int averageWordFreq ()
    {
        return (mostFreq.size() > 0) ? wordCountTotal/mostFreq.size() : 0;
    }

    /*
     * Gets the longest word in the TreeMap
     */
    private Word longestWord ()
    {
        return (!length.isEmpty()) ? length.firstKey() : null;
    }

    /*
     * Gets the shortest word in the TreeMap
     */
    private Word shortestWord ()
    {
        return (!length.isEmpty()) ? length.lastKey() : null;
    }

    /*
     * Gets the most frequent word in the TreeMap
     */
    private Word mostFreqWord ()
    {
        return (!mostFreq.isEmpty()) ? mostFreq.firstKey() : null;
    }

    /*
     * Gets the least frequent word in the TreeMap
     */
    private Word leastFreqWord ()
    {
        return (!mostFreq.isEmpty()) ? mostFreq.lastKey() : null;
    }

    /*
     * Creates the three TreeMaps using the existing HashMap
     */
    private void createTrees ()
    {
        if (!hm.isEmpty())
        {
            for (Word wrd : hm.values())
            {
                alpha.put(wrd, wrd); //Adds the Word objects into the alpha tree, sorted alphabetically
                mostFreq.put(wrd, wrd); //Adds the Word objects into the mostFreq tree, sorted from most to least frequent
                length.put(wrd, wrd); //Adds the Word objects into the length tree, sorted from longest to shortest

                wordCountTotal += mostFreq.get(wrd).getWordCount(); //Sums up all the word counts
                totalLength += length.get(wrd).getLength(); //Sums up all the lengths
            }
        }
    }

    /*
     * Prints out the first N elements of the TreeMap
     */
    private void printFirstN (int n, TreeMap <Word, Word> tm)
    {
        Iterator <Map.Entry <Word, Word>> it = tm.entrySet().iterator();
        for (int i = 0; i < n && it.hasNext(); i++)
        {
            Map.Entry <Word, Word> words = it.next();
            System.out.println (words.getValue());
        }
    }

    /*
     * Prints out all the elements of the TreeMap
     */
    private void printAll (TreeMap <Word, Word> tm)
    {
        printFirstN(tm.size(), tm);
    }

    /*
     * Prints out the results of the program
     */
    private void printResults ()
    {
        System.out.println ("\n------\n");
        System.out.println ("Total Words: " + totalWords);
        System.out.println ("Stop Words: " + stopWordCount);
        System.out.println ("Unique Words: " + hm.size());
        System.out.println ("\n------\n");
        System.out.println (NUM_WORDS + " Most Frequent");
        printFirstN(NUM_WORDS, mostFreq);
        System.out.println ("\n------\n");
        printFreqStats();
        System.out.println ("\n------\n");
        printLengthStats();
        System.out.println ("\n------\n");
        System.out.println ("All Words");
        printAll(alpha);
        System.out.println ("\n------\n");
    }

    /*
     * Prints out the statistics for word frequencies
     */
    private void printFreqStats ()
    {
        Word most = mostFreqWord();
        Word least = leastFreqWord();
        int avgFreq = averageWordFreq();

        System.out.println ("Statistics for Word Frequencies");
        System.out.println ("The most frequent word is " + ((most == null) ? "None" : most));
        System.out.println ("The least frequent word is " + ((least == null) ? "None" : least));
        System.out.println ("The average word frequency is " + ((avgFreq == 0) ? "None" : avgFreq));
    }

    /*
     * Prints out the statistics for word lengths
     */
    private void printLengthStats ()
    {
        Word longest = longestWord();
        Word shortest = shortestWord();
        int avgLength = averageWordLength();

        System.out.println ("Statistics for Word Length");
        System.out.println ("The longest word is " + ((longest == null) ? "None" : longest));
        System.out.println ("The shortest word is " + ((shortest == null) ? "None" : shortest));
        System.out.println ("The average word length is " + ((avgLength == 0) ? "None" : avgLength));
    }
}