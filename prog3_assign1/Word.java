import java.util.Comparator;
/**
 * Represents a single word and keeps track of how many times it appears
 * @author Aaron Li-Mai
 * @version 1.0
 * Created by Aaron Li-Mai on Jan 11, 2016
 * Last Modified: Jan 15, 2016
 */
public class Word implements Comparable <Word>
{
    private String word;
    private int wordCount;

    public static Comparator <Word> descComp = new Comparator <Word> ()
        {
            @Override
            /**
             * Compares the two word counts to see if they are the same
             * @param word1 The first word to compare
             * @param word2 The second word to compare
             * @return -1 if word1 is greater than word2; 0 if both are the same; 1 if word2 is greater than word1
             */
            public int compare (Word word1, Word word2) 
            {
                if (word1.getWordCount() > word2.getWordCount())
                {
                    return 1;
                }
                else if (word1.getWordCount() < word2.getWordCount())
                {
                    return -1;
                }
                return word1.compareTo(word2);
            }
        };

    public static Comparator <Word> comp = new Comparator <Word> ()
        {
            @Override
            /**
             * Compares the two word counts to see if they are the same
             * @param word1 The first word to compare
             * @param word2 The second word to compare
             * @return -1 if word1 is greater than word2; 0 if both are the same; 1 if word2 is greater than word1
             */
            public int compare (Word word1, Word word2) 
            {
                if (word1.getWordCount() > word2.getWordCount())
                {
                    return 1;
                }
                else if (word1.getWordCount() < word2.getWordCount())
                {
                    return -1;
                }
                return word2.compareTo(word1);
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