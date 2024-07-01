import objects.DataOfLetter;
import objects.Enigma;
import objects.Reflector;
import objects.Rotor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Enigma enigma = new Enigma();
        enigma.start();
    }
}