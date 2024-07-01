#include <iostream>
#include"Enigma.cpp"
#include"Rotor.cpp"
#include"Reflector.cpp"
#include"DataOfLetter.cpp"
#include"Plugboard.cpp"

using namespace std;

int main()
{
    //Enigma enigma;
    //enigma.start();
    Rotor R1 = Rotor(0,"EKMFLGDQVZNTOWYHXUSPAIBRCJ", 17);
    Rotor R2 = Rotor(3,"AJDKSIRUXBLHWTMCQGZNPYFVOE", 5);
    Rotor R3 = Rotor(4,"BDFHJLCPRTXVZNYEIWGAKMUSQO", 22);

    Reflector RA = Reflector("EJMZALYXVBWFCRQUONTSPIKHGD");


    DataOfLetter dataofletter = DataOfLetter("C", true);
    dataofletter.setIndexOfNextLetter(2);

    DataOfLetter rotorRight = R3.getOutputIndexIn(dataofletter);
    DataOfLetter rotorMid = R2.getOutputIndexIn(dataofletter);
    DataOfLetter rotorLeft = R1.getOutputIndexIn(dataofletter);

    DataOfLetter reflector = RA.reflectLetter(rotorLeft, R1);

    DataOfLetter returningRotorLeft = R1.getOutputIndexOut(dataofletter);
    DataOfLetter returningRotorMid = R1.getOutputIndexOut(dataofletter);
    DataOfLetter returningRotorRight = R1.getOutputIndexOut(dataofletter);

    cout << getLetterFromAlphabetByIndex(returningRotorRight.getIndexOfNextLetter());


    return 0;
}

string getLetterFromAlphabetByIndex(int index) {
    string alphabet[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
    return alphabet[index];
}
