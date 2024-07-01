package objects;

import java.util.HashMap;
import java.util.List;

public class Reflector {
   private List<String> lettersToReflect;
   private String hash;
    public Reflector(String lettersToReflect) {
        this.lettersToReflect = List.of(lettersToReflect.toUpperCase().split(""));
        this.hash = lettersToReflect;
    }

    public DataOfLetter reflectLetter(DataOfLetter letter, Rotor rotorBeforeReflector){
        HashMap<String, String> dictionaryOfReflections = new HashMap<>();
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int i = 0;
        for (String x: lettersToReflect){
            dictionaryOfReflections.put(alphabet[i], x);
            i++;
        }
        DataOfLetter output = new DataOfLetter(dictionaryOfReflections.get(letter.getLetter()), false);
        String[][] table = rotorBeforeReflector.getScrambledLetters();
        int indexOutput = 0;
        for (String[] x : table){
            if(output.getLetter().equalsIgnoreCase(x[0])){
                output.setIndexOfNextLetter(indexOutput);
            }else{
                indexOutput++;
            }
        }
        return output;
    }

    public String getHash() {
        return hash;
    }
}
