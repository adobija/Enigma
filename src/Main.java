import Objects.Reflector;
import Objects.Rotor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Rotor R1 = new Rotor(14, "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        Rotor R2 = new Rotor(2, "AJDKSIRUXBLHWTMCQGZNPYFVOE");
        Rotor R3 = new Rotor(1, "BDFHJLCPRTXVZNYEIWGAKMUSQO");
        Rotor R4 = new Rotor(1, "ESOVPZJAYQUIRHXLNFTGKDCMWB");
        Rotor R5 = new Rotor(1, "VZBRGITYUPSDNHLXAWMJQOFECK");

        Reflector RA = new Reflector("EJMZALYXVBWFCRQUONTSPIKHGD");
        Reflector RB = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        Reflector RC = new Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");

        String letterToCipher = "B";


    ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
    System.out.println(R2.getScrambler(alphabet.indexOf(letterToCipher)));



    }
}