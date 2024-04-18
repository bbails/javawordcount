import java.io.FileNotFoundException;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileWordCounter
{
    private Map<String, Integer> wordMap; 

    public FileWordCounter()
    {
        wordMap = new Hashtable<>();
    }

    // Public accessors
    public Map<String, Integer> GetWordMap()
    {
        return wordMap;
    }

    public void ClearCounter()
    {
        wordMap.clear();
    }

    public void CountWords(String filePath)
    {
        Scanner scan = null;

        try
        {
            scan = new Scanner(new File(filePath));
        }
        catch(FileNotFoundException e)
        {
            throw new RuntimeException("File not Found", e);
        }
        while (scan.hasNextLine())
        {
            String str = scan.nextLine();
            // Split the string into words
            CountWordsFromString(str);
        }
        if (scan != null)
        {
            try {
                scan.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        PrintMap();
    }

    private void CountWordsFromString(String str)
    {  
        // Split the string into words, remove any non-letters
        String[] words = str.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");

        // Loop through the words
        for (String word: words)
        {
            wordMap.merge(word, 1, (a, b) -> (a+b));
        }
    }

    // Returns a LinkedHasMap Sorted by the Integer Values desc
    private LinkedHashMap<String, Integer> SortWordMap()
    {
        LinkedHashMap<String, Integer> sortedMap = wordMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue((a, b) -> (b.compareTo(a))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap;
    }

    // Prints out the Map in desc order of Integer Values
    private void PrintMap()
    {
        LinkedHashMap<String, Integer> sortedMap = SortWordMap();

        Iterator<Map.Entry<String,Integer>> iterator = sortedMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> me = (Map.Entry<String, Integer>) iterator.next();
            System.out.println(me.getKey() + ": " + me.getValue());
        }
    }
}