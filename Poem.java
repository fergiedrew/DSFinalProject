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
        String[] stringsOnLineShakespeare = this.poem.get(linenumber).replaceAll("[)(\\[\\]!,.?{}:;\"\\'\\-]", "")
                .split(" ");
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

        if (areCloseEnough(this.stresspattern, inputstresspattern)) {
            System.out.println("The stress pattern was correct!");
            if (this.checkOtherRhyme(linenumber, inputline) || lastWordInputLine.endsWith(lastWordShakespeare.getRhymeNeeds())) {
                System.out.println("That line sure did fit in the rhyme scheme!");
                return true;
            } else {
                System.out.println("That line did not rhyme!");
                return false;
            } 
        } else {
            System.out.println(this.stresspattern + " did not work with " + inputstresspattern);
            return false;
        } 
    }

    

    public static String getLastWord(String line) {
        String[] arrayofstrings = line.trim().toLowerCase().replaceAll("[)(\\[\\]!,.?{}:;\"\\'\\-]", "").split(" ");
        return arrayofstrings[arrayofstrings.length - 1];

    }

    public String toString() {
        return this.poem.toString() + "\n" + this.rhymepattern + " " + this.stresspattern + this.usablelines;

    }

    public Boolean areCloseEnough(String stresspattern1, String stresspattern2) { // The stress pattern is close enough
                                                                                  // if it is the same length and it has
                                                                                  // at least 4 matching stresses
        int sumofmatchingsyllables = 0;
        if (stresspattern1.length() != stresspattern2.length()) {
            return false;
        }
        for (int i = 0; i < stresspattern1.length(); i++) {
            if (stresspattern1.charAt(i) == stresspattern2.charAt(i))
                sumofmatchingsyllables += 1;
        }
        if (sumofmatchingsyllables > 1) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean checkOtherRhyme(int linenumber, String line) { // Check if a certain line fits in with the other
                                                                  // lines in
                                                                  // the poem i.e. rhymes with another B or A, etc. rhyme
        String lastWord = getLastWord(line);
        Word theLastWord = Game.cmupron.get(lastWord);
        char rhymeType = this.rhymepattern.charAt(linenumber);
        int sameRhymeTypeIndex = 0;
        for (int i = 0; i < this.rhymepattern.length(); i++) { // Finds the line number of the other line the inputline
                                                               // needs to rhyme with
            if (i == linenumber) {
                continue;
            }
            if (rhymeType == rhymepattern.charAt(i)) {
                sameRhymeTypeIndex = i;
            }
        }
        if (Game.cmupron.containsKey(getLastWord(this.poem.get(sameRhymeTypeIndex)))) { // Checks if we can check that
                                                                                        // other line
            Word otherRhymeWord = Game.cmupron.get(getLastWord(this.poem.get(sameRhymeTypeIndex)));

            if (theLastWord.endsWith(otherRhymeWord.getRhymeNeeds())) { // Checks if the input line's last word rhymes
                                                                        // with the other line's last word
                return true;
            } else {
                return false;
            }
        } else {
            return false; // If the other line is unusable then it will return false
        }

    }
    public Poem takeOutLastWordOfLine(int linenumber) { // Takes out the last word of the line of a poem at the given linenumber and replaces it with ____ and returns this poem
        Poem gamepoem = new Poem(); // Make new poem
        String line = this.poem.get(linenumber); // Gets original line
        String[] linearray = line.split(" "); // makes an array
        String newline = ""; // Instantiate the newline string
        for (int i = 0; i < linearray.length - 1; i++) {  // Runs for every word except the last one
            newline += linearray[i] + " ";
        }
        newline += "____"; // adds blank instead of last word
        for (int i = 0; i < this.poem.size(); i++) {  // adds the orginal lines to the new poem, except for the modified line
            if (i == linenumber) {
                gamepoem.poem.add(newline);
            } else {
                gamepoem.poem.add(this.poem.get(i));
            }
        }
        gamepoem.usablelines = this.usablelines;
        return gamepoem;


    }

}