import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Test {
    String s1, s2;
    public Test(String file1, String file2, int shingleLength)
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

    static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static void main(String[] args){
        Test test = new Test("test/shak1", "test/shak2", 8);
        System.out.println(test.s1);
        System.out.println(test.s2);
//        HashStringSimilarity string = new HashStringSimilarity(test.s1, test.s2, 8);
//        System.out.println(string.similarity());
//        BruteForceSimilarity brute = new BruteForceSimilarity(test.s1, test.s2, 8);
//        System.out.println(brute.similarity());
        HashCodeSimilarity hash = new HashCodeSimilarity(test.s1, test.s2, 8);
        System.out.println("HashCodeSimilarity: " + hash.lengthOfS1());
        System.out.println("VectorLength2: " + hash.lengthOfS2());
        System.out.println(hash.lengthOfS1() * hash.lengthOfS2());
        System.out.println(hash.similarity());
    }
}
