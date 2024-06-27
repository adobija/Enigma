package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class Rotor {
   private int position;

   String[][] scrambledLetters;

   private int notchToRotateNext;

    public Rotor(int position, String scrambledLetters, int notchToRotateNext) {
        this.position = position;
        this.notchToRotateNext = notchToRotateNext;

        String[] arrayOfScrambledLetters = scrambledLetters.toUpperCase().split("");
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        this.scrambledLetters = new String[26][2];
        for (int i = 0; i<= 25; i++){
            this.scrambledLetters[i] = new String[]{alphabet[i], arrayOfScrambledLetters[i]};
        }

    }

    public void printScrambler(){
        for (String[] x: scrambledLetters){
            System.out.println("{"+x[0] + ", " + x[1]+"}");
        }
    }
    public String getOutputIndex(String letter){
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int indexOfInputLetter = 0;
        for(String x: alphabet){
            if(x.equalsIgnoreCase(letter)){
                break;
            }else{
                indexOfInputLetter++;
            }
        }
        String foundLetter = this.scrambledLetters[indexOfInputLetter][1];
        int indexOfLetterInAlphabet = 0;
        for(String[] pairs: this.scrambledLetters){
            if(pairs[0].equalsIgnoreCase(String.valueOf(foundLetter))){
                break;
            }else{
                indexOfLetterInAlphabet++;
            }
        }
        return alphabet[indexOfLetterInAlphabet];
    }
}
