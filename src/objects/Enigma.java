package objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Enigma {
    private Rotor rotorRight;
    private Rotor rotorMid;
    private Rotor rotorLeft;
    private Reflector reflector;

    public Enigma() {
    }

    public Rotor getRotorRight() {
        return rotorRight;
    }

    public Rotor getRotorMid() {
        return rotorMid;
    }

    public Rotor getRotorLeft() {
        return rotorLeft;
    }

    public Reflector getReflector() {
        return reflector;
    }

    public void setRotorRight(Rotor rotorRight) {
        this.rotorRight = rotorRight;
    }

    public void setRotorMid(Rotor rotorMid) {
        this.rotorMid = rotorMid;
    }

    public void setRotorLeft(Rotor rotorLeft) {
        this.rotorLeft = rotorLeft;
    }

    public void setReflector(Reflector reflector) {
        this.reflector = reflector;
    }

    public void start() throws IOException {
        Reflector RA = new Reflector("EJMZALYXVBWFCRQUONTSPIKHGD");
        Reflector RB = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        Reflector RC = new Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");

        boolean flag = true;
        Scanner skaner = new Scanner(System.in);
        do {
            System.out.println("Welcome to Enigma coder made by Aleksander Dobija");
            System.out.println("Menu:");
            System.out.println("To start machine type 'START'");
            System.out.println("To exit type 'QUIT'");
            String response = skaner.nextLine();
            if(response.equalsIgnoreCase("start")){
                System.out.println("Setting rotors:");
                System.out.println("Please choose Rotor1 [1], Rotor2 [2], Rotor3 [3], Rotor4 [4], Rotor5 [5]");
                System.out.println("or write your hash e.g. 'VZBRGITYUPSDNHLXAWMJQOFECK' - letters in your hash shuold be uniqe, write only letters");

                System.out.println("Which should be left rotor:");
                String responseRotorLeft = skaner.nextLine();
                setupRotorLeft(responseRotorLeft, skaner);

                System.out.println("Which should be middle rotor:");
                String responseRotorMid = skaner.nextLine();
                setupRotorMid(responseRotorMid, skaner);

                System.out.println("Which should be right rotor:");
                String responseRotorRight = skaner.nextLine();
                setupRotorRight(responseRotorRight, skaner);

                setupReflector(skaner);
                System.out.println("Insert one letter at once - as original enigma machine");
                System.out.println("If you want stop coding type '***'");
                boolean shouldBeWorking = true;
                while (shouldBeWorking){
                    String usersLetter = skaner.nextLine();
                    if(usersLetter.equalsIgnoreCase("***")){
                        flag = false;
                    }else{
                        int index = getIndexInAlphabet(usersLetter);

                        DataOfLetter dataOfLetter = new DataOfLetter(usersLetter, true);
                        dataOfLetter.setIndexOfNextLetter(index);
                        DataOfLetter rotorRight = getRotorRight().getOutputIndexIn(dataOfLetter);
                        DataOfLetter rotorMid = getRotorMid().getOutputIndexIn(rotorRight);
                        DataOfLetter rotorLeft = getRotorLeft().getOutputIndexIn(rotorMid);
                        DataOfLetter relfector = getReflector().reflectLetter(rotorLeft, getRotorRight());
                        DataOfLetter returningRotorLeft = getRotorLeft().getOutputIndexOut(relfector);
                        DataOfLetter returningRotorMid = getRotorMid().getOutputIndexOut(returningRotorLeft);
                        DataOfLetter returningRotorRight = getRotorRight().getOutputIndexOut(returningRotorMid);

                        System.out.println("Zaszyfrowana literka to " + getLetterFromAlphabetByIndex(returningRotorRight.getIndexOfNextLetter()));
                    }
                }

            } else if (response.equalsIgnoreCase("quit")) {
                flag = false;
                System.out.println("Shutting down...");
            }
        }while (flag);
        System.out.println("Shutting down...");
    }

    public int getIndexInAlphabet(String letter){
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

    public String getLetterFromAlphabetByIndex(int index){
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        return alphabet[index];
    }

    public void setupReflector(Scanner skaner) throws IOException {
        System.out.println("Setting reflector");
        System.out.println("Please choose Reflector A [A], Reflector B [B], Reflector C [C]");
        System.out.println("or write your custom reflector - type hash e.g. 'VZBRGITYUPSDNHLXAWMJQOFECK' \nletters in your hash should be unique,\nwrite only letters");
        System.out.println("in example A will be reflected to V, B to Z, C to B, D to R...");
        String reflectorResponse = skaner.nextLine();
        switch (reflectorResponse){
            case "A":
                setReflector(configReflector("EJMZALYXVBWFCRQUONTSPIKHGD"));
                break;
            case "B":
                setReflector(configReflector("YRUHQSLDPXNGOKMIEBFZCWVJAT"));
                break;
            case "C":
                setReflector(configReflector("FVPJIAOYEDRZXWGCTKUQSBNMHL"));
                break;
            default:
                setReflector(configReflector(reflectorResponse));
                break;
        }
        System.out.println("Reflector has been successfully set!");
    }

    public Reflector configReflector(String hash) throws IOException {
        checkHash(hash);
        return new Reflector(hash);
    }

    public String checkHash(String hash) throws IOException {
        ArrayList<String> hashTab = new ArrayList<>(Arrays.asList(hash.split("")));
        ArrayList<String> checkTab = new ArrayList<>();
        if(hashTab.size() != 26){
            throw new IOException("Error - your hash dont have exactly 26 letters");
        }else{
            for (String x: hashTab){
                for (String y : checkTab){
                    if(checkTab.contains(x)){
                        throw new IOException("Error - your hash dont have uniqe 26 letters");
                    }else{
                        checkTab.add(y);
                    }
                }
            }
        }
        return hash;
    }

    public void setupRotorRight(String response, Scanner skaner) throws IOException {
        switch (response){
            case "1":
                setRotorRight(configRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ",17, skaner));
                break;
            case "2":
                setRotorRight(configRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE",5, skaner));
                break;
            case "3":
                setRotorRight(configRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO",22, skaner));
                break;
            case "4":
                setRotorRight(configRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB",10, skaner));
                break;
            case "5":
                setRotorRight(configRotor("VZBRGITYUPSDNHLXAWMJQOFECK",25, skaner));
                break;
            case "default":
                setRotorRight(configRotor(response, skaner));
                break;
        }
        System.out.println("Right rotor has been successfully set!");
    }

    public void setupRotorMid(String response, Scanner skaner) throws IOException {
        switch (response){
            case "1":
                setRotorMid(configRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ",17, skaner));
                break;
            case "2":
                setRotorMid(configRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE",5, skaner));
                break;
            case "3":
                setRotorMid(configRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO",22, skaner));
                break;
            case "4":
                setRotorMid(configRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB",10, skaner));
                break;
            case "5":
                setRotorMid(configRotor("VZBRGITYUPSDNHLXAWMJQOFECK",25, skaner));
                break;
            case "default":
                setRotorMid(configRotor(response, skaner));
                break;
        }
        System.out.println("Middle rotor has been successfully set!");
    }

    public void setupRotorLeft(String response, Scanner skaner) throws IOException {
        switch (response){
            case "1":
                setRotorLeft(configRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ",17, skaner));
                break;
            case "2":
                setRotorLeft(configRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE",5, skaner));
                break;
            case "3":
                setRotorLeft(configRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO",22, skaner));
                break;
            case "4":
                setRotorLeft(configRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB",10, skaner));
                break;
            case "5":
                setRotorLeft(configRotor("VZBRGITYUPSDNHLXAWMJQOFECK",25, skaner));
                break;
            case "default":
                setRotorLeft(configRotor(response, skaner));
                break;
        }
        System.out.println("Left rotor has been successfully set!");
    }

    public Rotor configRotor(String hash, Scanner skaner) throws IOException{
        System.out.println("Insert position (0-25):");
        int position = skaner.nextInt();
        skaner.nextLine();
        System.out.println("Insert notch of turning next rotor (0-25):");
        int notchOfTurningNext = skaner.nextInt();
        skaner.nextLine();
        return new Rotor(position, checkHash(hash), notchOfTurningNext);
    }
    public Rotor configRotor(String hash, int notchOfTurningNext, Scanner skaner) throws IOException{
        System.out.println("Insert position (0-25):");
        int position = skaner.nextInt();
        skaner.nextLine();
        return new Rotor(position, checkHash(hash), notchOfTurningNext);
    }
}
