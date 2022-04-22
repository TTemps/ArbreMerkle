import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OpenFile {

    public ArrayList<Integer> readFile(String fileName) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            File fil = new File("texte.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(fil))) {
                int r = 0;

                while ((r = br.read()) != -1) {
                    list.add(r);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}