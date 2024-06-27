package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Reflector {
   private List<String> lettersToReflect;

    public Reflector(String lettersToReflect) {
        this.lettersToReflect = List.of(lettersToReflect.toUpperCase().split(""));
    }

    public DataOfLetter reflectLetter(DataOfLetter letter){
        HashMap<String, String> dictionaryOfReflections = new HashMap<>();
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int i = 0;
        for (String x: lettersToReflect){
            dictionaryOfReflections.put(alphabet[i], x);
            i++;
        }
        DataOfLetter output = new DataOfLetter(dictionaryOfReflections.get(letter.getLetter()), false);
        return output;
    }
}
