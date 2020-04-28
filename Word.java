import java.util.ArrayList;

public class Word {
  public String word;
  int numberOfSyllables;
  ArrayList<String> phonemes;
  ArrayList<Integer> stresses;

  public Word(String cmuline) {
    String[] parts = cmuline.split(" ");
    this.word = parts[0].toLowerCase().replaceAll("[)(\\[\\]!,.?{} :;\"\\'\\-]", "");
    this.phonemes = new ArrayList<String>();
    this.stresses = new ArrayList<Integer>();
    for (int i = 1; i < parts.length; i++) {
      if (parts[i].length() < 1)
        continue;
      if (Character.isDigit(parts[i].charAt(parts[i].length() - 1))) {
        this.stresses.add(Integer.valueOf("" + parts[i].charAt(parts[i].length() - 1)));
        this.phonemes.add(parts[i].substring(0, parts[i].length() - 1));
      } else {
        this.phonemes.add(parts[i]);
      }
    }
  }

  public boolean endsWith(ArrayList<String> phonemes) {
    if (this.phonemes.size() < phonemes.size())
      return false;

    int mylen = this.phonemes.size();
    int otherlen = phonemes.size();
    for (int i = 0; i < otherlen; i++) {
      if (!this.phonemes.get(mylen - 1 - i).equals(phonemes.get(otherlen - 1 - i)))
        return false;
    }
    return true;
  }

  public ArrayList<String> getRhymeNeeds() {
    int idx = this.stresses.size() - 1;
    int unstressCount = 0; // # of 0-stresses at the end of the word

    while (idx >= 0 && this.stresses.get(idx) == 0) {
      unstressCount++;
      idx--;
    }
    // System.out.println(this.stresses + "unstressCount = " + unstressCount);
    // Work backwards in phonemes ArrayList, pass unstressCount-many vowel phonemes,
    // and then the last stressed vowel. We prepend each phoneme we encounter to the
    // rv ("return value") ArrayList of phonemes, which we will return when the loop
    // ends.
    idx = this.phonemes.size() - 1;
    ArrayList<String> rv = new ArrayList<String>();
    while (idx >= 0 && unstressCount >= 0) {
      if ("AEIOUaeiou".indexOf(this.phonemes.get(idx).charAt(0)) != -1) {
        rv.add(0, this.phonemes.get(idx));
        unstressCount--;
        idx--;
      } else {
        rv.add(0, this.phonemes.get(idx));
        idx--;
      }
    }
    return rv;
  }
}