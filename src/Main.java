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


        String[] lettersToCypher = {"O", "L", "E", "K"};
        for (String x : lettersToCypher){
            int index = getIndexInAlphabet(x);

            DataOfLetter dataOfLetter = new DataOfLetter(x, true);
            dataOfLetter.setIndexOfNextLetter(index);
            DataOfLetter afterRotor3 = R3.getOutputIndexIn(dataOfLetter);
            DataOfLetter afterRotor2 = R2.getOutputIndexIn(afterRotor3);
            DataOfLetter afterRotor1 = R1.getOutputIndexIn(afterRotor2);
            DataOfLetter reflected = RA.reflectLetter(afterRotor1, R1);
            DataOfLetter letterAfterRotor1 = R1.getOutputIndexOut(reflected);
            DataOfLetter letterAfterRotor2 = R2.getOutputIndexOut(letterAfterRotor1);
            DataOfLetter letterAfterRotor3 = R3.getOutputIndexOut(letterAfterRotor2);

            System.out.println("Zaszyfrowana literka to " + getLetterFromAlphabetByIndex(letterAfterRotor3.getIndexOfNextLetter()));
        }
    }
    public static int getIndexInAlphabet(String letter){
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int index = 0;
        for (String x: alphabet){
            if(x.equalsIgnoreCase(letter)){
                return index;
            }else{
                index++;
            }
        }
        return -1;
    }
    public static String getLetterFromAlphabetByIndex(int index){
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        return alphabet[index];
    }
}