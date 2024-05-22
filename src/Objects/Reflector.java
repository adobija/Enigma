package Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Reflector {
    private HashMap<String, String> Scrambler;

    public Reflector(String letters) {
        Scrambler = generateScrambler(letters);
    }

    private HashMap<String, String> generateScrambler(String letters){
        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
        String[] letterToScrambler = letters.split("");
        HashMap<String, String> hashMapToReturn = new HashMap<>();
        int i = 0;
        for (String x: letterToScrambler){
            hashMapToReturn.put(alphabet.get(i), x);
            i++;
        }
        return hashMapToReturn;
    }
    public DataOfLetter reflectLetter(String letter){
        int i = 1;
        for (String x: Scrambler.keySet()) {
            if(letter.equalsIgnoreCase(x)){
                break;
            }
            i++;
        }
        DataOfLetter letterToReturn = new DataOfLetter(Scrambler.get(letter), i-1);
        return letterToReturn;
    }
}
