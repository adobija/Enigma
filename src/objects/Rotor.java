package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Rotor {
   private int position;

   String scrambledLetters;

   private int notchToRotateNext;

    public Rotor(int position, String scrambledLetters, int notchToRotateNext) {
        this.position = position;
        this.scrambledLetters = scrambledLetters;
        this.notchToRotateNext = notchToRotateNext;
    }
}
