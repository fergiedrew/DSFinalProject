import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

// To wipe screen: 
// System.out.print("\033[H\033[2J");
// System.out.flush();

public class Game {
    static ArrayList<Word> cmupron;
    static ArrayList<Poem> poems;
    public static void main(String[] args) {
       // playGame();
       readCMUPron("cmupron.txt");
       readPoems("Shakepeare.txt");
       playGame();
       
        
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
    static void playGame(){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int index = random.nextInt(poems.size() - 1);
        System.out.print("To Begin Game, Type Play:");
        String beginword = scanner.nextLine().toLowerCase().trim();
        if (beginword.equals("play")) {
            System.out.println("Here's how the game works: ");
            System.out.print("  You will be given a random shakepeare sonnet \n  To test out your poet skills you will be asked to come up with a line\n");
            System.out.println("Here we go!");
            System.out.print("Type ready when ready: ")
            String readyword = scanner.nextLine().toLowerCase().trim();
            if (readyword.equals("ready")) {
                poems.get(index).takeOutRandomLine().printPoem();
            }

            
            
            
            
        } else {
            System.out.println("Aight, I see how it is");
        }
        


    }
    
}