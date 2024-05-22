import Objects.Reflector;
import Objects.Rotor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Rotor R1 = new Rotor(4,  "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        Rotor R2 = new Rotor(15, "AJDKSIRUXBLHWTMCQGZNPYFVOE");
        Rotor R3 = new Rotor(8,  "BDFHJLCPRTXVZNYEIWGAKMUSQO");
        Rotor R4 = new Rotor(1,  "ESOVPZJAYQUIRHXLNFTGKDCMWB");
        Rotor R5 = new Rotor(1,  "VZBRGITYUPSDNHLXAWMJQOFECK");

        Reflector RA = new Reflector("EJMZALYXVBWFCRQUONTSPIKHGD");
        Reflector RB = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        Reflector RC = new Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");

        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));


        String letterToCipher = "R"; // 5 + 7
        System.out.println(alphabet.indexOf(letterToCipher));

        int indexOfLetterAfterFirstCrypt = R3.getLetterFromIndex(alphabet.indexOf(letterToCipher)); // 14
        System.out.println(indexOfLetterAfterFirstCrypt + " index zwroconej literki");
//        System.out.println(indexOfLetterAfterFirstCrypt);
        int indexOfLetterAfterSecondCrypt = R2.getLetterFromIndex(indexOfLetterAfterFirstCrypt); // V -> D
        System.out.println(indexOfLetterAfterSecondCrypt + " index zwroconej literki");

        int indexOfLetterAfterThirdCrypt = R1.getLetterFromIndex(indexOfLetterAfterSecondCrypt); // D -> S
        System.out.println(indexOfLetterAfterThirdCrypt + " index zwroconej literki");



    }
}