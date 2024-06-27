import objects.DataOfLetter;
import objects.Reflector;
import objects.Rotor;

import java.util.ArrayList;
import java.util.Arrays;

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

        System.out.println(RA.reflectLetter("E").getLetter());

    }
}