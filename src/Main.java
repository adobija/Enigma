import objects.DataOfLetter;
import objects.Reflector;
import objects.Rotor;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //position from 0 - 25
        Rotor R1 = new Rotor(8,  "EKMFLGDQVZNTOWYHXUSPAIBRCJ", 17);
        Rotor R2 = new Rotor(3, "AJDKSIRUXBLHWTMCQGZNPYFVOE", 5);
        Rotor R3 = new Rotor(5,  "BDFHJLCPRTXVZNYEIWGAKMUSQO", 22);
        Rotor R4 = new Rotor(0,  "ESOVPZJAYQUIRHXLNFTGKDCMWB", 10);
        Rotor R5 = new Rotor(0,  "VZBRGITYUPSDNHLXAWMJQOFECK", 25);

        Reflector RA = new Reflector("EJMZALYXVBWFCRQUONTSPIKHGD");
        Reflector RB = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        Reflector RC = new Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");
R3.printScrambler();
        System.out.println();
R2.printScrambler();
        System.out.println();
R1.printScrambler();
        System.out.println();
        String letterToInput = "C";
        DataOfLetter dataOfLetter = new DataOfLetter(letterToInput, true);
        System.out.println("wchodzi literka " + letterToInput);
        dataOfLetter.setIndexOfNextLetter(2);
        DataOfLetter afterRotor3 = R3.getOutputIndexIn(dataOfLetter);
        System.out.println(afterRotor3.getIndexOfNextLetter());
        DataOfLetter afterRotor2 = R2.getOutputIndexIn(afterRotor3);
        System.out.println(afterRotor2.getIndexOfNextLetter());
        DataOfLetter afterRotor1 = R1.getOutputIndexIn(afterRotor2);
        System.out.println(afterRotor1.getIndexOfNextLetter());
        System.out.println("literka wchodzaca do reflectora to " + afterRotor1.getLetter());
        DataOfLetter reflected = RA.reflectLetter(afterRotor1, R1);
        System.out.println("wychodzi z reflektora " + reflected.getLetter());
        System.out.println("rzad ktory bedzie rozpatrzany to " + reflected.getIndexOfNextLetter());

        DataOfLetter letterAfterRotor1 = R1.getOutputIndexOut(reflected);
        System.out.println(letterAfterRotor1.getLetter());
        DataOfLetter letterAfterRotor2 = R2.getOutputIndexOut(letterAfterRotor1);
        System.out.println(letterAfterRotor2.getLetter());
        DataOfLetter letterAfterRotor3 = R3.getOutputIndexOut(letterAfterRotor2);
        System.out.println(letterAfterRotor3.getLetter());
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        System.out.println("zaszyfrowana literka to " + alphabet[letterAfterRotor3.getIndexOfNextLetter()]);
    }
}