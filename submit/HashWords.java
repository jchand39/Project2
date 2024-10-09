import itsc2214.*; // not needed now, but you might in your projects

/**
 * Description
 *  @author Jared Chandler
 */
public class HashWords {

    private WordFrequency[] table;  
    private int size;
    
    //
    private int numUniqueWords = 0;
    private int totalWordsCount = 0;

    /**
     * Constructor for the class which creates the array.
     */
    public HashWords(int initialSize) {
        this.table = new WordFrequency[initialSize];  
        this.size = initialSize;                              
    }

    /**
     * Returns the size of the table
     */
    public int size() {
        return size;
    }

    /**
     * HashKey method is responsible for converting a word into an index by using a hash function. this is used to place the word in the hash table.
     */
    public int hashKey(String w) {
        w = w.toLowerCase();
        int sum = 0;
        for (char c : w.toCharArray()) {
            sum += (int) c; // Summing the ASCII values
        }
        return sum % size;
    }

    /**
     * Description
     */
    private WordFrequency getUsingWord(String w) {
        for (WordFrequency wf : table)
            if (wf != null && wf.getWord().equal(w))
                return wf;
        return null;
    }

    /**
     * If wf does not equal null then count word frequency, if wf does equal null return 0.
     */
    public int frequency(String w) {
        WordFrequency wf = getUsingWord(w);
        return (wf != null) ? wf.getCount() : 0;
    }

    /**
     * The add word function will add a word to the array and if that word already exists in said array it will increment that word.
     * @param w
     */
    public void addWord(String w) {
        w = w.toLowerCase();

        int index = hashKey(w);
        WordFrequency wf = getUsingWord(w);

        totalWordsCount++;

        if (wf != null) {
            wf.increment();
            return;
        }

        while (table[index] != null) {
            index = (index + 1) % size;
        }

        table[index] = new WordFrequency(w);
        uniqueWordCount++;

        if (isFull()) {
            resizeTable();
        }
    }

    /**
     * Checks if the table's #elements equals it's max size.
     * 
     * @return true if the table is full of unique words; otherwise, false
     */
    private boolean isFull() {
        for (WordFrequency wf : table)
            if (wf == null)
                return false;
        return true;
    }

    /**
     * Does this array contain word "w", if so return true?
     * 
     * @param w
     * @return true if the word IS in the table False if NOT.
     */
    public boolean contains(String w) {
        return getUsingword(w) != null;
    }

    /**
     * Total number of unique words.
     * 
     * @return the total number of all the unique words in the array
     */
    public int numUniqueWordsInTable() {
        return uniqueWordCount;
    }

    /**
     * Returns the total number of all words in the array.
     * 
     * @return the sum off all words in the array
     */
    public int totalNumOfWords() {
        return totalWordsCount;
    }

    /**
     * Looks for the most incremented word in the array and returns it.
     * 
     * @return the word with the highest count in the array.
     */
    public String mostCommonWord() {
        String mostFreqWord = null;
        int count = 0;

        for (WordFrequency wf : table) {
            if (wf != null && wf.getCount() > count) {
                count = wf.getCount();
                mostFreqWord = wf.getWord()
            }
        }
        return mostFreqWord;
    }

    /**
     * termFrequency() determines the uniqueness of a term within the entirety of the array and returns a ratio.
     * 
     * @param w
     * @return wordRatio - the ratio of the words frequency and the total number of words
     */
    public double termFrequency(String w) {
        wordRatio = frequency(w) / totaNumWords();
        
        if (totalNumWords() == 0) {
            return 0.0;
        }
        return wordRatio;
    }

    private void resizeTable() {
        WordFrequency[] oldTable = table;

        size *= 3;
        table = new WordFrequency[size]; // table overrides the old table and JVM dumps the old table.

        for (WordFrequency wf : oldTable) {
            if (wf != null) {
            int key = hashKey(wf.getWord());

            while (table[key] != null) {
                key = (key + 1) % size;
                table[key] = wf
                }
            }
        } 
    }
}
