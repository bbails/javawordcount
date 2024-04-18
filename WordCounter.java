public class WordCounter {
    public static void main(String[] args) {
        if (args.length < 1)
        {
            System.out.println("Error, must include File Path as command line argument");
        }
        FileWordCounter counter = new FileWordCounter();
        counter.CountWords(args[0]);
    }
}
