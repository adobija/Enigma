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


        String letterToCipher = "E"; // 5 + 7
        String letterAfterFirstCrypt = R3.getScrambledLetter(alphabet.indexOf(letterToCipher)); // E -> V
//        String letterAfterSecondCrypt = R2.getScrambledLetter(alphabet.indexOf(letterAfterFirstCrypt)); // V -> D
//        String letterAfterThirdCrypt = R1.getScrambledLetter(alphabet.indexOf(letterAfterSecondCrypt)); // D -> S

        System.out.println(letterToCipher);
        System.out.println(alphabet.indexOf(letterToCipher)+1);
        System.out.println(letterAfterFirstCrypt);
        System.out.println(alphabet.indexOf(letterAfterFirstCrypt)+1);
//        System.out.println(letterAfterSecondCrypt);
//        System.out.println(alphabet.indexOf(letterAfterSecondCrypt)+1);
//        System.out.println(letterAfterThirdCrypt);
//        System.out.println(alphabet.indexOf(letterAfterThirdCrypt)+1);


    }
}