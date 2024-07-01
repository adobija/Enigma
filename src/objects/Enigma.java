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
    private String codedMessage;

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

    public String getCodedMessage() {
        return codedMessage;
    }

    public void addCodedMessage(String x) {
        String[] testSize = getCodedMessage().split("");
        if(testSize.length%5 == 0){
            this.codedMessage += " ";
        }
        this.codedMessage += x;
    }

    public void start() throws IOException {
        this.codedMessage = " ";
        System.out.println("Welcome to Enigma coder made by Aleksander Dobija");
        Scanner skaner = new Scanner(System.in);

        setupRotorLeft(skaner);
        setupRotorMid(skaner);
        setupRotorRight(skaner);
        setupReflector(skaner);
        System.console().flush();
        System.out.println("Insert one letter or type whole message!");
        System.out.println("If you want stop coding type '***'");
        System.out.println("To check settings type '$settings$'");
        System.out.println("To print your whole coded message type '$output$'");
                boolean shouldBeWorking = true;
                while (shouldBeWorking){

                    String usersLetter = skaner.nextLine();
                    if(usersLetter.equalsIgnoreCase("***")){
                        System.console().flush();
                        shouldBeWorking = false;
                    }else if (usersLetter.equalsIgnoreCase("$settings$")){
                        System.console().flush();
                        System.out.println("*".repeat(100));
                        System.out.println("Settings of: ");
                        System.out.println();
                        System.out.println("Rotor left");
                        System.out.println("Starting position: " + getRotorLeft().getPositionOnStart());
                        System.out.println("Notch to rotate next rotor " + getRotorLeft().getNotchToRotateNext());
                        System.out.println("Hash of rotor: " + getRotorLeft().getHash());
                        System.out.println();
                        System.out.println("Rotor middle");
                        System.out.println("Starting position: " + getRotorMid().getPositionOnStart());
                        System.out.println("Notch to rotate next rotor " + getRotorMid().getNotchToRotateNext());
                        System.out.println("Hash of rotor: " + getRotorMid().getHash());
                        System.out.println();
                        System.out.println("Rotor right");
                        System.out.println("Starting position: " + getRotorRight().getPositionOnStart());
                        System.out.println("Notch to rotate next rotor " + getRotorRight().getNotchToRotateNext());
                        System.out.println("Hash of rotor: " + getRotorRight().getHash());
                        System.out.println();
                        System.out.println("Reflector: ");
                        System.out.println(getReflector().getHash());
                        System.out.println("*".repeat(100));
                    } else if (usersLetter.equalsIgnoreCase("$output$")) {
                        System.out.println(getCodedMessage());
                    } else{
                        String[] arrayOfLetters = usersLetter.split("");
                        for (String x: arrayOfLetters){
                            if(!x.matches("[A-Za-z]")){
                                continue;
                            }
                            int index = getIndexInAlphabet(x);

                            DataOfLetter dataOfLetter = new DataOfLetter(x, true);
                            dataOfLetter.setIndexOfNextLetter(index);
                            DataOfLetter rotorRight = getRotorRight().getOutputIndexIn(dataOfLetter);
                            DataOfLetter rotorMid = getRotorMid().getOutputIndexIn(rotorRight);
                            DataOfLetter rotorLeft = getRotorLeft().getOutputIndexIn(rotorMid);
                            DataOfLetter reflector = getReflector().reflectLetter(rotorLeft, getRotorLeft());
                            DataOfLetter returningRotorLeft = getRotorLeft().getOutputIndexOut(reflector);
                            DataOfLetter returningRotorMid = getRotorMid().getOutputIndexOut(returningRotorLeft);
                            DataOfLetter returningRotorRight = getRotorRight().getOutputIndexOut(returningRotorMid);

                            System.out.println("Cyphered letter -> " + getLetterFromAlphabetByIndex(returningRotorRight.getIndexOfNextLetter()));
                            addCodedMessage(getLetterFromAlphabetByIndex(returningRotorRight.getIndexOfNextLetter()));
                        }
                    }
                }
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
        switch (reflectorResponse.toUpperCase()){
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

    public void setupRotorRight(Scanner skaner) throws IOException {
        System.out.println("Setting right rotor:");
        System.out.println("Please choose Rotor1 [1], Rotor2 [2], Rotor3 [3], Rotor4 [4], Rotor5 [5]");
        System.out.println("or write your hash e.g. 'VZBRGITYUPSDNHLXAWMJQOFECK' - letters in your hash shuold be uniqe, write only letters");
        String response = skaner.nextLine();
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
            default:
                setRotorRight(configRotor(response, skaner));
                break;
        }
    }

    public void setupRotorMid(Scanner skaner) throws IOException {
        System.out.println("Setting middle rotor:");
        System.out.println("Please choose Rotor1 [1], Rotor2 [2], Rotor3 [3], Rotor4 [4], Rotor5 [5]");
        System.out.println("or write your hash e.g. 'VZBRGITYUPSDNHLXAWMJQOFECK' - letters in your hash shuold be uniqe, write only letters");
        String response = skaner.nextLine();
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
            default:
                setRotorMid(configRotor(response, skaner));
                break;
        }
        System.out.println("Middle rotor has been successfully set!");
    }

    public void setupRotorLeft(Scanner skaner) throws IOException {
        System.out.println("Setting left rotor:");
        System.out.println("Please choose Rotor1 [1], Rotor2 [2], Rotor3 [3], Rotor4 [4], Rotor5 [5]");
        System.out.println("or write your hash e.g. 'VZBRGITYUPSDNHLXAWMJQOFECK' - letters in your hash shuold be uniqe, write only letters");
        String response = skaner.nextLine();
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
           default:
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
        Rotor rotor = new Rotor(position, checkHash(hash), notchOfTurningNext);
        rotor.setPositionOnStart(position);
        return rotor;
    }
    public Rotor configRotor(String hash, int notchOfTurningNext, Scanner skaner) throws IOException{
        System.out.println("Insert position (0-25):");
        int position = skaner.nextInt();
        skaner.nextLine();
        Rotor rotor = new Rotor(position, checkHash(hash), notchOfTurningNext);
        rotor.setPositionOnStart(position);
        return rotor;
    }
}
