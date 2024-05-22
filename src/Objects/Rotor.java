package Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Rotor {
    private HashMap<Integer, String> Scrambler;
    private int Position;

    private Boolean isFastestRotor;

    private Boolean isFirstTurn;

    private int notchToTurnAnotherWheel;

    private Boolean isSlowestRotor;

    private int positionBeforeCall;

    public int getNotchToTurnAnotherWheel() {
        return notchToTurnAnotherWheel;
    }

    public void setNotchToTurnAnotherWheel(int notchToTurnAnotherWheel) {
        this.notchToTurnAnotherWheel = notchToTurnAnotherWheel;
    }

    public Boolean getSlowestRotor() {
        return isSlowestRotor;
    }

    public void setSlowestRotor(Boolean slowestRotor) {
        isSlowestRotor = slowestRotor;
    }

    public Boolean getFirstTurn() {
        return isFirstTurn;
    }

    public void setFirstTurn(Boolean firstTurn) {
        isFirstTurn = firstTurn;
    }

    public Boolean getFastestRotor() {
        return isFastestRotor;
    }

    public int getPositionBeforeCall() {
        return positionBeforeCall;
    }

    public void setPositionBeforeCall(int positionBeforeCall) {
        this.positionBeforeCall = positionBeforeCall;
    }

    public void setFastestRotor(Boolean fastestRotor) {
        isFastestRotor = fastestRotor;
    }

    public Rotor(int position, String letters, int notchToTurnAnotherWheel) {
        Scrambler = scramblerGenerator(letters);
        Position = position;
        this.isFastestRotor = false;
        isFirstTurn = true;
        this.isSlowestRotor = false;
        this.notchToTurnAnotherWheel = notchToTurnAnotherWheel;
        this.positionBeforeCall = position;
    }
    private HashMap<Integer, String> scramblerGenerator(String letters){
        String[] tableOfLetters = letters.split("");
        HashMap<Integer, String> tableToReturn = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            tableToReturn.put(i,tableOfLetters[i]);
        }
        return tableToReturn;
    }

    //get scrambled letter from index from values eg. on rotor1  0 -> E, 1 -> K
    public DataOfLetter getLetterFromIndex(int indexOfLetter, boolean shouldRotate) {
        if(getFirstTurn()){
            setFirstTurn(false);
        }else{
            setFirstTurn(true);
        }

        if(shouldRotate){
            RotateRotor();
        }

        int indexOfLetterToOutput = indexOfLetter+(getPosition()-1);
        if(indexOfLetterToOutput >= 26){
            indexOfLetterToOutput = indexOfLetterToOutput - 26;
        }
            return getAlphabeticalIndexOfLetter(Scrambler.get(indexOfLetterToOutput));
    }

    public void RotateRotor(){
        int newPosition = getPosition()+1;
        if(newPosition >= 27){
            this.Position = 1;
        }
        this.positionBeforeCall = getPosition();
        this.Position = newPosition;

    }

    public int getPosition() {
        return Position;
    }

    private DataOfLetter getAlphabeticalIndexOfLetter(String letter){
        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
        int indexOf = (alphabet.indexOf(letter)-getPosition()+1);
        if(indexOf < 0){
            indexOf = indexOf + 26;
        }
        DataOfLetter literka = new DataOfLetter(letter, indexOf);
        if(getPositionBeforeCall() == getNotchToTurnAnotherWheel()){
            literka.setShouldRotate(true);
        }
        return literka;
    }
    public DataOfLetter getLetterFromIndex(int index){
        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
        String letter = alphabet.get(index+getPosition()-1);
        int i = 0;
        int indexToCipherNext = 0;
        for (String x: Scrambler.values()) {
            if(letter.equalsIgnoreCase(x)){
               indexToCipherNext = i-getPosition()+1;
               if(indexToCipherNext > 25){
                   indexToCipherNext = indexToCipherNext - 26;
               }
                break;
            }
            i++;
        }
        return new DataOfLetter(letter, indexToCipherNext);
    }
}
