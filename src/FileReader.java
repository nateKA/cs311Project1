import java.io.File;
import java.util.Scanner;

public class FileReader {
    public static String getStringFromFile(String path){
        try {
            String str = "";
            File f = new File(path);
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                String line = s.nextLine();
                str += line
                        .replaceAll("\\s+","")
                        .replaceAll("\\.|,|;|:","");
            }
            return str;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
