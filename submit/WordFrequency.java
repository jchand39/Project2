/**
 * WordFrequency class to be used in a hash table.
 * 
 * @author Jared Chandler
 */
public class WordFrequency {
    
    private String word;
    private int count;

    /**
     * Constructor WordFrequency stores arg w into the instace variable word 
     * and initializes the instance variable count to 1.
     */
    public WordFrequency(String w) {
        this.word = w.toLowerCase();
        this.count = 1;
    }

    /**
     * returns the word
     */
    public String getWord() {
        return this.word;
    }

    /**
     * returns the count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * When called will increment the count object by 1
     */
    public void increment() {
        count++;
    }

    /**
     * equals() - compares two WordFrequency objects
     * checking to see if they are the same. Equality
     * is defined by string matching ignoring case.
     * 
     * @param other object to compare against
     * @return true if this and other are equals, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof String) {
            String w = (String) other;
            return getWord().equalsIgnoreCase(w);
        } else if (other instanceof WordFrequency) {
            WordFrequency wf = (WordFrequency) other;
            String w = wf.getWord();
            return getWord().equalsIgnoreCase(w);
        } else {
            return false;
        }
    }
}
