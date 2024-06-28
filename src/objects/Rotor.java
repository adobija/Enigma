package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class Rotor {
   private int position;

   private String[][] scrambledLetters;

   private int notchToRotateNext;

   private String lettersToCypher;

    public Rotor(int position, String lettersToCypher, int notchToRotateNext) {
        if(position > 25 || position < 0){
            throw new ArithmeticException("Please input valid position!");
        }
        if(notchToRotateNext > 25 || notchToRotateNext < 0){
            throw new ArithmeticException("Please input valid notch!");
        }
        this.position = position;
        this.notchToRotateNext = notchToRotateNext;
        this.scrambledLetters = new String[26][2];
        this.lettersToCypher = lettersToCypher;
//        for (int i = position; i<= 25; i++){
//            this.scrambledLetters[i] = new String[]{alphabet[i], arrayOfScrambledLetters[i]};
//        }
        generateScrablerToPosition(position);
    }
    private String getLettersToCypher(){
        return this.lettersToCypher;
    }

    public void generateScrablerToPosition(int position){
        String[] arrayOfScrambledLetters = getLettersToCypher().toUpperCase().split("");
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int counter = 0;
        while(counter <= 25){
//            System.out.println("adding to row " + counter +  " elements " + alphabet[position] + ", " + arrayOfScrambledLetters[position]);
            this.scrambledLetters[position] = new String[]{alphabet[counter], arrayOfScrambledLetters[counter]};
            if(position < 25){
                position++;
            }else{
                position = 0;
            }
            counter++;
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
//        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int indexOfInputLetter = letter.getIndexOfNextLetter();
//        int indexOfInputLetter = 0;
//        for(String x: alphabet){
//            if(x.equalsIgnoreCase(letter.getLetter())){
//                break;
//            }else{
//                indexOfInputLetter++;
//            }
//        }
        System.out.println("rzad brany pod uwage to  " + indexOfInputLetter);
        String foundLetter = this.scrambledLetters[indexOfInputLetter][1];
        System.out.println("odpowiadająca jej literka w sramblerze to " + foundLetter);
        int indexOfLetterInAlphabet = 0;
        for(String[] pairs: this.scrambledLetters){
            if(pairs[0].equalsIgnoreCase(String.valueOf(foundLetter))){
                break;
            }else{
                indexOfLetterInAlphabet++;
            }
        }
        System.out.println("ta sama literka co wyżej w lewej kolumnie ma index " + indexOfLetterInAlphabet);
        boolean shouldRotateNext = false;
        int positionAtEnd = getPosition();
        if(positionOnCall == positionAtEnd-1 && getNotchToRotateNext() == positionOnCall){
            shouldRotateNext = true;

        }
        DataOfLetter outputLetter = new DataOfLetter(foundLetter, shouldRotateNext);
        outputLetter.setIndexOfNextLetter(indexOfLetterInAlphabet);
        return outputLetter;
    }

    public DataOfLetter getOutputIndexOut(DataOfLetter letter){
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int indexOfInputLetter = letter.getIndexOfNextLetter();
//        int indexOfInputLetter = 0;
//        for(String x: alphabet){
//            if(x.equalsIgnoreCase(letter.getLetter())){
//                break;
//            }else{
//                indexOfInputLetter++;
//            }
//        }
        System.out.println("rzad brany pod uwage to  " + indexOfInputLetter);
        String foundLetter = this.scrambledLetters[indexOfInputLetter][0];
        System.out.println("odpowiadająca jej literka w sramblerze to lewa kolumna to " + foundLetter);
        int indexOfLetterInAlphabet = 0;
        for(String[] pairs: this.scrambledLetters){
            if(pairs[1].equalsIgnoreCase(String.valueOf(foundLetter))){
                break;
            }else{
                indexOfLetterInAlphabet++;
            }
        }
        System.out.println("ta sama literka co wyżej w prawej kolumnie ma index " + indexOfLetterInAlphabet);
        DataOfLetter outputLetter = new DataOfLetter(foundLetter, false);
        outputLetter.setIndexOfNextLetter(indexOfLetterInAlphabet);
        return outputLetter;
    }

    public void rotate(){
//        this.position++;
    }
    public int getPosition(){
        return this.position;
    }

    public int getNotchToRotateNext() {
        return notchToRotateNext;
    }

    public String[][] getScrambledLetters() {
        return scrambledLetters;
    }

    public void setScrambledLetters(String[][] scrambledLetters) {
        this.scrambledLetters = scrambledLetters;
    }

}
