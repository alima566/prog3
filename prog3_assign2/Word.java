import java.util.Comparator;
/**
 * Represents a single word and keeps track of how many times it appears
 * @author Aaron Li-Mai
 * @version 2.0
 * Created by Aaron Li-Mai on Jan 11, 2016
 * Last Modified: Jan 15, 2016 (As Assignment 1)
 *                Jan 26, 2016 (As Assignment 2)
 */
public class Word implements Comparable <Word>
{
    private String word;
    private int wordCount;

    public static Comparator <Word> ascComp = new Comparator <Word> () //Sorts from most to least
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
                if (diff == 0)
                {
                    return word1.compareTo(word2);
                }
                else
                {
                    return diff;
                }
            }
        };

    public static Comparator <Word> descComp = new Comparator <Word> () //Sorts from least to most
        {
            @Override
            /**
             * Compares the two word counts to see if they are the same
             * @param word1 The first word to compare
             * @param word2 The second word to compare
             * @return -1 The difference between the two word counts. If they're the same, returns it in alphabetical order
             */
            public int compare (Word word1, Word word2) 
            {
                int diff = word1.getWordCount() - word2.getWordCount();
                if (diff == 0)
                {
                    return word1.compareTo(word2);
                }
                else
                {
                    return diff;
                }
            }
        };

    public Word (String word)
    {
        this.setWord(word);
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
        return getWord() + " : " + getWordCount();
    }
}