
import org.junit.Assert;
import org.junit.Test;


public class WordCountTests {

    @Test
    public void testCountWordsFromString_simple(){
        String filePath = "TestDoc.txt";
        
        FileWordCounter counter = new FileWordCounter();
        counter.CountWords(filePath);

        Assert.assertEquals(counter.GetWordMap().get("et"), (Integer)6);
        Assert.assertEquals(counter.GetWordMap().get("quis"), (Integer)2);
        Assert.assertEquals(counter.GetWordMap().get("three"), null);
    }

    @Test
    public void testCountWordsFromString_punctuation(){
        String filePath = "TestDoc2.txt";
        
        FileWordCounter counter = new FileWordCounter();
        counter.CountWords(filePath);

        Assert.assertEquals(counter.GetWordMap().get("et"), (Integer)6);
        Assert.assertEquals(counter.GetWordMap().get("quis"), (Integer)2);
        Assert.assertEquals(counter.GetWordMap().get("lorem"), (Integer)1);
        Assert.assertEquals(counter.GetWordMap().get("venenatis"), (Integer)2);
    }
}
