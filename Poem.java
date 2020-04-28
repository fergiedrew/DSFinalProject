import java.util.ArrayList;
import java.util.Random;

public class Poem {
    ArrayList<String> poem; // the poem in a list, one line for each item
    String rhymepattern = "ABABCDCDEFEFGG"; // The rhyme pattern
    String stresspattern = "1010101010"; // The stress pattern
    ArrayList<Integer> usablelines; // The lines which can be used to check for rhymes

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
        String[] stringsOnLineShakespeare = this.poem.get(linenumber).replaceAll("[)(\\[\\]!,.?{}:;\"\\'\\-]", "").split(" ");
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

        for (int j = 0; j < wordsOnInputLine.size(); j++) { // find the Stresspattern of the inputline
            for (int k = 0; k < wordsOnInputLine.get(j).stresses.size(); k++) {
                inputstresspattern += wordsOnInputLine.get(j).stresses.get(k).toString();
            }

        }

        Word lastWordShakespeare = Game.cmupron.get(stringsOnLineShakespeare[stringsOnLineShakespeare.length - 1]);
        Word lastWordInputLine = wordsOnInputLine.get(wordsOnInputLine.size() - 1);

        if (lastWordShakespeare == null) { // Check if the target word is in cmupron
            System.out.println("Couldn't find shakespeare's word! "
                    + stringsOnLineShakespeare[stringsOnLineShakespeare.length - 1]);
            return false;
        } else { // Check if the line has the correct stress pattern and rhymes with the target
                 // word
            if (areCloseEnough(this.stresspattern, inputstresspattern) && lastWordInputLine.endsWith(lastWordShakespeare.getRhymeNeeds())) {
                return true;

            } else {
                System.out.println("The stress pattern you gave was " + inputstresspattern);
                System.out.println(lastWordInputLine.word + " for " + lastWordShakespeare.word);
                return false;
            }
        }
    }
    public static String getLastWord(String line) {
        String [] arrayofstrings = line.trim().toLowerCase().replaceAll("[)(\\[\\]!,.?{}:;\"\\'\\-]", "").split(" ");
        return arrayofstrings[arrayofstrings.length - 1];

    }
    public String toString() {
        return this.poem.toString() + "\n" + this.rhymepattern + " " + this.stresspattern;

    }
    public Boolean areCloseEnough(String stresspattern1, String stresspattern2) { // The stress pattern is close enough if it is the same length and it has at least 4 matching stresses
        int sumofmatchingsyllables = 0;
        if (stresspattern1.length() != stresspattern2.length()) {
            return false;
        }
        for (int i = 0; i < stresspattern1.length(); i++) {
            if (stresspattern1.charAt(i) == stresspattern2.charAt(i)) sumofmatchingsyllables += 1;
        }
        if (sumofmatchingsyllables > 3) {
            return true;
        } else {
            return false;
        }
        


    }

}