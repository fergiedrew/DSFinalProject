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
       readPoems("Shakepeare.txt");
       System.out.println(poems.get(13).toString());
        
       //System.out.println(cmupron.get(13).word);
    }
    static void readCMUPron(String cmu){ // Fills the cmupron field with the dictionary of cmupron info
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

        }   catch (IOException e) {
                System.out.format("IOException %s\n", e);
        }
    }
    static void readPoems(String allPoems) { //fills the poems list with all of the shakespeare sonnets
        try {
            poems = new ArrayList<Poem>();
            FileReader f = new FileReader(allPoems);
            BufferedReader reader = new BufferedReader(f);
            String poemLine = reader.readLine();
            while (poemLine != null) { 
                if (poemLine.length() < 10) {
                    poemLine = reader.readLine();
                    continue;
                }
                String wholePoem = "";
                for(int i = 0; i < 14; i++) { // Loops 14 times for each line of a sonnet
                    wholePoem += poemLine + "\n";
                    poemLine = reader.readLine();
                }
                Poem newPoem = new Poem(wholePoem);
                poems.add(newPoem);
                poemLine = reader.readLine();

            }
            
        } catch (IOException f) {
            System.out.format("IOException %s\n", f);
        }
    }
}