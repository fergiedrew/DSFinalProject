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
    public Poem takeOutRandomLine() { // Takes a random line out of the poem arraylist and replaces it with " "
        Random random = new Random();
        int linenumber = random.nextInt(13) + 1;
        Poem newpoem = new Poem();
        for (int i = 0; i < this.poem.size(); i++) {
            if (i == linenumber) {
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


    public String toString(){
        return this.poem.toString() + "\n" + this.rhymepattern + " " + this.stresspattern;

    }

    

}