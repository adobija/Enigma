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

    public String getScrambler(int indexOfLetter) {
        return Scrambler.get(indexOfLetter+(Position-1));
    }

    public void setScrambler(HashMap<Integer, String> scrambler) {
        Scrambler = scrambler;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }
}
