package Objects;

import java.util.ArrayList;
import java.util.Arrays;
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

    //get scrambled letter from index from values eg. on rotor1  0 -> E, 1 -> K
    public int getLetterFromIndex(int indexOfLetter) {
//        RotateRotor();
        int indexOfLetterToOutput = indexOfLetter+(getPosition()-1); // 14+ (14)
        if(indexOfLetterToOutput >= 26){
            indexOfLetterToOutput = indexOfLetterToOutput - 26;//2
        }
            return getAlphabeticalIndexOfLetter(Scrambler.get(indexOfLetterToOutput));
    }

    public void RotateRotor(){
        this.Position = Position+1;
    }

    public int getPosition() {
        return Position;
    }

    //search the same letter but in Key
    private int getAlphabeticalIndexOfLetter(String letter){
        System.out.println("literka " + letter);
        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
        System.out.println("index literki " + alphabet.indexOf(letter));
        int indexOf = (alphabet.indexOf(letter)-getPosition()+1); // 3 - (15+1)
        if(indexOf < 0){
            indexOf = indexOf + 26;
        }
        System.out.println(indexOf + " index literki do zwrotu");
        return indexOf;
    }
}
