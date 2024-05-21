package Objects;

import java.util.HashMap;

public class Rotor {
    private HashMap<Integer, String> Scrambler;
    private int Position;

    public Rotor(int position, String letters) {
        Scrambler = scramblerGenerator(letters);
        Position = position;
    }
    private HashMap<Integer, String> scramblerGenerator(String letters){
        String[] tableOfLetters = letters.split("");
        HashMap<Integer, String> tableToReturn = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            tableToReturn.put(i,tableOfLetters[i]);
        }
        return tableToReturn;
    }

    public String getScrambledLetter(int indexOfLetter) {
//        RotateRotor();
        int indexOfLetterToOutput = indexOfLetter+(Position-1);
        if(indexOfLetterToOutput >= 26){
            indexOfLetterToOutput = indexOfLetterToOutput - 26;
        }
            return Scrambler.get(indexOfLetterToOutput);
    }

    public void RotateRotor(){
        this.Position = Position+1;
    }

}
