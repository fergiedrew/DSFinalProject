import java.util.ArrayList;

public class Poem {
    ArrayList<String> poem; // the poem in a list, one line for each item
    String rhymepattern = "ABABCDCDEFEFGG";
    String stresspattern = "1010101010";
    public static void main(String[] args) {
     
    }


    public Poem(String wholepoem) { // Constructs a Poem object from a poem string
        String[] poemlines = wholepoem.split("\n"); // Makes array seperated by line
        poem = new ArrayList<String>();
        for (int i = 0; i < poemlines.length; i++) {
            poem.add(poemlines[i]);           
        }       
    }
    public String toString(){
        return this.poem.toString() + "\n" + this.rhymepattern + " " + this.stresspattern;

    }


}