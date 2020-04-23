import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game {
    static ArrayList<Word> cmupron;
    static ArrayList<Poem> poems;
    public static void main(String[] args) {
       // playGame();
       readCMUPron("cmupron.txt");
       //System.out.println(cmupron.get(13).word);

    }
    static void readCMUPron(String cmu){
        try {
            cmupron = new ArrayList<Word>();
            FileReader f = new FileReader(cmu);
            BufferedReader reader = new BufferedReader(f);
            String cmuline = reader.readLine();
        
            while (cmuline != null) {
                cmuline.trim();
                Word x = new Word(cmuline);
                cmupron.add(x);
                cmuline = reader.readLine();
            }

        }   catch (IOException e){
                System.out.format("IOException %s\n", e);
        }
    }
}