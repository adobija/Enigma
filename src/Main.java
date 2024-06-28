import objects.DataOfLetter;
import objects.Enigma;
import objects.Reflector;
import objects.Rotor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        //position from 0 - 25
//        Rotor R1 = new Rotor(8,  "EKMFLGDQVZNTOWYHXUSPAIBRCJ", 17);
//        Rotor R2 = new Rotor(3, "AJDKSIRUXBLHWTMCQGZNPYFVOE", 5);
//        Rotor R3 = new Rotor(22,  "BDFHJLCPRTXVZNYEIWGAKMUSQO", 22);
//        Rotor R4 = new Rotor(0,  "ESOVPZJAYQUIRHXLNFTGKDCMWB", 10);
//        Rotor R5 = new Rotor(0,  "VZBRGITYUPSDNHLXAWMJQOFECK", 25);
//
//        Reflector RA = new Reflector("EJMZALYXVBWFCRQUONTSPIKHGD");
//        Reflector RB = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
//        Reflector RC = new Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");
//
//
//        String[] lettersToCypher = {"L", "Q", "S", "Y"};
//        for (String x : lettersToCypher){
//            int index = getIndexInAlphabet(x);
//
//            DataOfLetter dataOfLetter = new DataOfLetter(x, true);
//            dataOfLetter.setIndexOfNextLetter(index);
//            DataOfLetter afterRotor3 = R3.getOutputIndexIn(dataOfLetter);
//            DataOfLetter afterRotor2 = R2.getOutputIndexIn(afterRotor3);
//            DataOfLetter afterRotor1 = R1.getOutputIndexIn(afterRotor2);
//            DataOfLetter reflected = RA.reflectLetter(afterRotor1, R1);
//            DataOfLetter letterAfterRotor1 = R1.getOutputIndexOut(reflected);
//            DataOfLetter letterAfterRotor2 = R2.getOutputIndexOut(letterAfterRotor1);
//            DataOfLetter letterAfterRotor3 = R3.getOutputIndexOut(letterAfterRotor2);
//
//            System.out.println("Zaszyfrowana literka to " + getLetterFromAlphabetByIndex(letterAfterRotor3.getIndexOfNextLetter()));
//        }

        Enigma enigma = new Enigma();
        enigma.start();
    }
}