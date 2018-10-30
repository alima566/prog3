import java.util.Comparator;
/**
 * Represents a single word and keeps track of how many times it appears
 * @author Aaron Li-Mai
 * @version 3.0
 * Created by Aaron Li-Mai on Jan 11, 2016
 * Last Modified: Jan 15, 2016 (As Assignment 1)
 *                Jan 26, 2016 (As Assignment 2)
 *                Mar 14, 2016 (As Assignment 5)
 */
public class Word implements Comparable <Word>
{
    private String word;
    private int wordCount;
    private int length;

    public static Comparator <Word> ascComp = new Comparator <Word> () //Sorts word count in ascending order
        {
            @Override
            /**
             * Compares the two word counts to see if they are the same
             * @param word1 The first word to compare
             * @param word2 The second word to compare
             * @return The difference between the two word counts. If they're the same, returns it in alphabetical order
             */
            public int compare (Word word1, Word word2) 
            {
                int diff = word2.getWordCount() - word1.getWordCount();
                return (diff == 0) ? word1.compareTo(word2) : diff;
            }
        };

    public static Comparator <Word> descComp = new Comparator <Word> () //Sorts word count in descending order
        {
            @Override
            /**
             * Compares the two word counts to see if they are the same
             * @param word1 The first word to compare
             * @param word2 The second word to compare
             * @return The difference between the two word counts. If they're the same, returns it in alphabetical order
             */
            public int compare (Word word1, Word word2) 
            {
                int diff = word1.getWordCount() - word2.getWordCount();
                return (diff == 0) ? word1.compareTo(word2) : diff;
            }
        };

    public static Comparator <Word> wordLength = new Comparator <Word> () //Sorts using word length
        {
            @Override
            /**
             * Compares the two word lengths to see if they are the same
             * @param word1 The first word to compare
             * @param word2 The second word to compare
             * @return The difference between the two word lengths. If they're the same, returns it in alphabetical order
             */
            public int compare (Word word1, Word word2)
            {
                int diff = word2.getWord().length() - word1.getWord().length();
                return (diff == 0) ? word1.compareTo(word2) : diff;
            }
        };

    public Word (String word)
    {
        this.setWord(word);
        wordCount = 1;
    }

    /**
     * @return The word
     */
    public String getWord () 
    {
        return word;
    }

    /**
     * @param word The word to set
     */
    public void setWord (String word) 
    {
        this.word = word;
    }

    /**
     * @return The word count
     */
    public int getWordCount ()
    {
        return wordCount;
    }

    public int getLength ()
    {
        return word.length();
    }

    /**
     * Increments the word count
     */
    public void incrementWordCount ()
    {
        wordCount++;
    }

    @Override
    /**
     * @param other The other Word to compare to
     * @return -1 if this is less than other; 0 if both are the same; 1 if other is greater than this
     */
    public int compareTo (Word other) 
    {
        return this.getWord().compareTo(other.getWord());
    }

    @Override
    /**
     * @param other The other object to compare
     * @return True if the two objects are the same; false otherwise
     */
    public boolean equals (Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null)
        {
            return false;
        }
        if (this.getClass() != other.getClass())
        {
            return false;
        }
        Word w = (Word)other;
        return this.getWord().equals(w.getWord());
    }

    public String toString ()
    {
        return getWord() + " : " + getLength() + " : " + getWordCount();
    }
}