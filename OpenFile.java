import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class OpenFile {
    public static void main(String args[]) {
        try {
            File fil = new File("/Users/john/Desktop/demo2.txt");
            BufferedReader br = new BufferedReader(new FileReader(fil));
            System.out.println("file content: ");
            int r = 0;
            while ((r = br.read()) != -1) {
                System.out.print((char) r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}