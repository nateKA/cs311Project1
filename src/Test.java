import java.io.File;
import java.util.Scanner;

public class Test {
    String s1, s2;
    public Test(String file1, String file2)
    {
        try {
            Scanner scanner = new Scanner(new File(file1));
            s1 = scanner.useDelimiter("\\A").next();
            s1 = s1.replaceAll("[^a-zA-Z]", "");
            s1 = s1.toLowerCase();            scanner = new Scanner(new File(file2));
            s2 = scanner.useDelimiter("\\A").next();
            s2 = s2.replaceAll("[^a-zA-Z]", "");
            s2 = s2.toLowerCase();            scanner = new Scanner(new File(file2));

            scanner.close();
        }
        catch(Exception e){
            System.out.println(e.getCause());
        }
    }

    public static void main(String[] args){
        Test test = new Test("test/shak1", "test/shak2");

        long time = System.currentTimeMillis();
        HashStringSimilarity string = new HashStringSimilarity(test.s1, test.s2, 7);
        System.out.println(string.similarity());
        time = System.currentTimeMillis() - time;
        System.out.println("HashStringTime: " + time);

        time = System.currentTimeMillis();
        BruteForceSimilarity brute = new BruteForceSimilarity(test.s1, test.s2, 8);
        System.out.println(brute.similarity());
        time = System.currentTimeMillis() - time;
        System.out.println("BruteForceTime: " + time);

        time = System.currentTimeMillis();
        HashCodeSimilarity hash = new HashCodeSimilarity(test.s1, test.s2, 7);
        System.out.println(hash.similarity());
        time = System.currentTimeMillis() - time;
        System.out.println("HashCodeTime: " + time);
    }
}
