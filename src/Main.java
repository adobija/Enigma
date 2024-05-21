import Objects.Rotor;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Rotor R1 = new Rotor(0, "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        Rotor R2 = new Rotor(0, "AJDKSIRUXBLHWTMCQGZNPYFVOE");
        Rotor R3 = new Rotor(0, "BDFHJLCPRTXVZNYEIWGAKMUSQO");
        Rotor R4 = new Rotor(0, "ESOVPZJAYQUIRHXLNFTGKDCMWB");
        Rotor R5 = new Rotor(0, "VZBRGITYUPSDNHLXAWMJQOFECK");
        for (int i = 1; i <= 26; i++){
            System.out.println(R1.getScrambler(i));
        }

    }
}