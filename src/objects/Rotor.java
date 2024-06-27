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
        if(position > 25 || position < 0){
            throw new ArithmeticException("Please input valid position!");
        }
        if(notchToRotateNext > 25 || notchToRotateNext < 0){
            throw new ArithmeticException("Please input valid position!");
        }
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
    public DataOfLetter getOutputIndexIn(DataOfLetter letter){
        int positionOnCall = getPosition();
        if(letter.ShouldRotate()){
            rotate();
        }

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int indexOfInputLetter = 0;
        for(String x: alphabet){
            if(x.equalsIgnoreCase(letter.getLetter())){
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
        boolean shouldRotateNext = false;
        int positionAtEnd = getPosition();
        if(positionOnCall == positionAtEnd-1 && getNotchToRotateNext() == positionOnCall){
            shouldRotateNext = true;

        }
        return new DataOfLetter(alphabet[indexOfLetterInAlphabet], shouldRotateNext);
    }

    public DataOfLetter getOutputIndexOut(DataOfLetter letter){
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int indexOfInputLetter = 0;
        for(String x: alphabet){
            if(x.equalsIgnoreCase(letter.getLetter())){
                break;
            }else{
                indexOfInputLetter++;
            }
        }
        String foundLetter = this.scrambledLetters[indexOfInputLetter][0];
        int indexOfLetterInAlphabet = 0;
        for(String[] pairs: this.scrambledLetters){
            if(pairs[1].equalsIgnoreCase(String.valueOf(foundLetter))){
                break;
            }else{
                indexOfLetterInAlphabet++;
            }
        }
        return new DataOfLetter(alphabet[indexOfLetterInAlphabet], false);
    }

    public void rotate(){
        this.position++;
    }
    public int getPosition(){
        return this.position;
    }

    public int getNotchToRotateNext() {
        return notchToRotateNext;
    }

}
