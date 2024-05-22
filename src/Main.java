import Objects.DataOfLetter;
import Objects.Reflector;
import Objects.Rotor;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        Rotor R1 = new Rotor(4,  "EKMFLGDQVZNTOWYHXUSPAIBRCJ", 17);
        Rotor R2 = new Rotor(15, "AJDKSIRUXBLHWTMCQGZNPYFVOE", 5);
        Rotor R3 = new Rotor(7,  "BDFHJLCPRTXVZNYEIWGAKMUSQO", 22);
        Rotor R4 = new Rotor(0,  "ESOVPZJAYQUIRHXLNFTGKDCMWB", 10);
        Rotor R5 = new Rotor(0,  "VZBRGITYUPSDNHLXAWMJQOFECK", 26);

        Reflector RA = new Reflector("EJMZALYXVBWFCRQUONTSPIKHGD");
        Reflector RB = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        Reflector RC = new Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");

        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));

        R3.setFastestRotor(true);



        DataOfLetter letterToCipher = new DataOfLetter("E",5);
        letterToCipher.setShouldRotate(true);



        DataOfLetter indexOfLetterAfterFirstCrypt = R3.getLetterFromIndex(alphabet.indexOf(letterToCipher.getLetter()), true); // E -> V
//        System.out.println(indexOfLetterAfterFirstCrypt + " index zwroconej literki");
        DataOfLetter indexOfLetterAfterSecondCrypt = R2.getLetterFromIndex(indexOfLetterAfterFirstCrypt.getIndexOfNextToCode(), indexOfLetterAfterFirstCrypt.getShouldRotate()); // V -> D
//        System.out.println(indexOfLetterAfterSecondCrypt + " index zwroconej literki");

        DataOfLetter indexOfLetterAfterThirdCrypt = R1.getLetterFromIndex(indexOfLetterAfterSecondCrypt.getIndexOfNextToCode(), indexOfLetterAfterSecondCrypt.getShouldRotate()); // D -> S
//        System.out.println(indexOfLetterAfterThirdCrypt + " index zwroconej literki");

        String letterAfterRotors = indexOfLetterAfterThirdCrypt.getLetter();
        DataOfLetter returningLetter = RB.reflectLetter(letterAfterRotors);


        DataOfLetter letterAfterFirstInReturn = R1.getLetterFromIndex(returningLetter.getIndexOfNextToCode());
         DataOfLetter letterAfterSecondInReturn = R2.getLetterFromIndex(letterAfterFirstInReturn.getIndexOfNextToCode());
        DataOfLetter letterAfterThirdInReturn = R3.getLetterFromIndex(letterAfterSecondInReturn.getIndexOfNextToCode());
        System.out.println(letterAfterThirdInReturn.getLetter());

    }
}