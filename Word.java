import java.util.ArrayList;
public class Word {
  public String word;
  int numberOfSyllables;
  ArrayList<String> phonemes; 
  ArrayList<Integer> stresses; 

  public Word(String cmuline){
      String[] parts = cmuline.split(" ");
      this.word = parts[0].toLowerCase().replaceAll("[)(\\[\\]!,.?{} :;\"\\'\\-]", "");
      this.phonemes = new ArrayList<String>();
      this.stresses = new ArrayList<Integer>();
      for (int i = 1; i < parts.length; i++) {
          if(parts[i].length() < 1) continue;
          if(Character.isDigit(parts[i].charAt(parts[i].length() - 1))) {
              this.stresses.add(Integer.valueOf("" + parts[i].charAt(parts[i].length()-1)));
              this.phonemes.add(parts[i].substring(0, parts[i].length() - 1));
          } else {
              this.phonemes.add(parts[i]);
          }
        }
  }
}