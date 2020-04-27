import java.util.ArrayList;
import java.util.Random;

public class Poem {
    ArrayList<String> poem; // the poem in a list, one line for each item
    String rhymepattern = "ABABCDCDEFEFGG";
    String stresspattern = "1010101010";
    public static void main(String[] args) {
     
    }
    public Poem() {
        poem = new ArrayList<String>();

    }

    public Poem(String wholepoem) { // Constructs a Poem object from a poem string
        String[] poemlines = wholepoem.split("\n"); // Makes array seperated by line
        poem = new ArrayList<String>();
        for (int i = 0; i < poemlines.length; i++) {
            poem.add(poemlines[i]);           
        }       
    }
    public Poem takeOutLine(int index) { // Takes a random line out of the poem arraylist and replaces it with " "
        Poem newpoem = new Poem();
        for (int i = 0; i < this.poem.size(); i++) {
            if (i == index) {
                newpoem.poem.add(" ");
            } else {
                newpoem.poem.add(this.poem.get(i));
            }
        }
        return newpoem;

    }
    public void printPoem() {
        for (int i = 0; i < this.poem.size(); i++) {
            System.out.println(this.poem.get(i));
        }
    }
    public Boolean lineFits(String inputline, int linenumber) {
        String inputstresspattern = "";
        String[] stringsOnLineShakespeare = this.poem.get(linenumber).split(" ");
        String[] stringsOnInputLine = inputline.toLowerCase().replaceAll("[)(\\[\\]!,.?{}:;\"\\'\\-]", "").split(" ");
        ArrayList<Word> wordsOnInputLine = new ArrayList<Word>();
        for (int i = 0; i < stringsOnInputLine.length; i++) { // Make the inputline a list of words with cmupron info
            Word currentword = Game.cmupron.get(stringsOnInputLine[i]);
            if (currentword == null) {
                System.out.println(stringsOnInputLine[i] + " Did not work as a real word!");
                return false;
            } else {
                wordsOnInputLine.add(currentword);
            }
        }
        for(int j = 0; j < wordsOnInputLine.size(); j++) { // find the Stresspattern of the inputline
            for (int k = 0; k < wordsOnInputLine.get(j).stresses.size(); k++) {
                inputstresspattern += wordsOnInputLine.get(j).stresses.get(k).toString();
            }
            
        }
        
        Word lastWordShakespeare = Game.cmupron.get(stringsOnLineShakespeare[stringsOnLineShakespeare.length - 1]);
        Word lastWordInputLine = wordsOnInputLine.get(wordsOnInputLine.size() - 1);
        if (lastWordShakespeare == null) {
            System.out.println("Couldn't find shakespeare's word! " + stringsOnLineShakespeare[stringsOnLineShakespeare.length - 1]);
            return false;
        } else {
            if (lastWordInputLine.endsWith(lastWordShakespeare.getRhymeNeeds())) {
                // inputstresspattern.equals(this.stresspattern) && 
                return true;

            } else {
                return false;
            }


        }

        

    }
    
    


    public String toString(){
        return this.poem.toString() + "\n" + this.rhymepattern + " " + this.stresspattern;

    }

    

}