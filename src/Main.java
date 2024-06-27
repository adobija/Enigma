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

        String afterRotor3 = R3.getOutputIndex("C");
        String afterRotor2 = R2.getOutputIndex(afterRotor3);
        String afterRotor1 = R1.getOutputIndex(afterRotor2);
        System.out.println(afterRotor1);
//
//-> rotor 3 ->w prawej kolumnie szukam literki z indexem # -> sprawdzam jej index w tabeli lewej -> @
//-> rotor 2 ->w prawej kolumnie szukam literki z indexem @ -> sprawdzam jej index w tabeli lewej -> &
//-> rotor 1 ->w prawej kolumnie suzkam literki z indexem & -> sprawdzam jej index w tabeli lewej ->
//
//
//
//        C -> 2 -> get.tablicaZnaków[2][1] ->
//        F -> # -> i = 0; for(String[] tablica: tablicaZnaków){
//                  if(tablica[0] == #){
//                  return i;
//                  }else{
//                  i++
//                  }
//          } -> 5
//
//        5 -> get.tablicaZnaków[5][1] ->
//        I -> # -> i = 0; for(String[] tablica: tablicaZnaków){
//                  if(tablica[0] == #){
//                  return i;
//                  }else{
//                  i++
//                  }
//          } -> 0
//
//        0 -> get.tablicaZnaków[0][1] ->
//        A -> # -> i = 0; for(String[] tablica: tablicaZnaków){
//                  if(tablica[0] == #){
//                  return i;
//                  }else{
//                  i++
//                  }
//          } -> 0
    }
}