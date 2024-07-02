#include <cctype>
#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <sstream>
#include <conio.h> 
using namespace std;

class DataOfLetter {
private:
    string letter;
    bool shouldRotate;
    int indexOfNextLetter;

public:
    DataOfLetter(string letter, bool shouldRotate) {
        this->letter = letter;
        this->shouldRotate = shouldRotate;
    }

    int getIndexOfNextLetter() {
        return this->indexOfNextLetter;
    }
    void setIndexOfNextLetter(int indexOfNextLetter) {
        this->indexOfNextLetter = indexOfNextLetter;
    }
    string getLetter() {
        string upperCaseLetter = this->letter;
        transform(upperCaseLetter.begin(), upperCaseLetter.end(), upperCaseLetter.begin(), ::toupper);
        return upperCaseLetter;
    }
    bool ShouldRotate() {
        return this->shouldRotate;
    }
};

class Rotor {
private:
    int position;
    string scrambledLetters[26][2];
    int notchToRotateNext;
    string lettersToCypher;
    int positionOnStart;
    string hash;

    string getLettersToCypher() {
        return this->lettersToCypher;
    }

    vector<string> splitIntoCharacters(const string& str) {
        vector<string> result;
        for (char c : str) {
            result.push_back(string(1, c));
        }
        return result;
    }

    string toUpperCase(const string& str) {
        string result = str;
        transform(result.begin(), result.end(), result.begin(), ::toupper);
        return result;
    }

public:
    int getPositionOnStart() {
        return this->positionOnStart;
    }

    void setPositionOnStart(int positionOnStart) {
        this->positionOnStart = positionOnStart;
    }

    int getPosition() {
        return this->position;
    }

    int getNotchToRotateNext() {
        return notchToRotateNext;
    }

    string getHash() {
        return this->hash;
    }

    void rotate() {
        int prevPosition = getPosition();
        this->position++;
        if (getPosition() >= 26) {
            this->position = 0;
        }
        generateScramblerToPosition(getPosition());
    }

    string** getScrambledLetters() {
        string** result = new string * [26];

        for (int i = 0; i < 26; ++i) {
            result[i] = new string[2];
            result[i][0] = scrambledLetters[i][0];
            result[i][1] = scrambledLetters[i][1];
        }

        return result;
    }

    Rotor(int position, string lettersToCypher, int notchToRotateNext) {
        if (position > 25 || position < 0) {
            throw invalid_argument("Please input valid position!");
        }
        if (notchToRotateNext > 25 || notchToRotateNext < 0) {
            throw invalid_argument("Please input valid notch!");
        }
        this->position = position;
        this->notchToRotateNext = notchToRotateNext;
        this->lettersToCypher = lettersToCypher;
        this->hash = lettersToCypher;
        generateScramblerToPosition(position);
    }

    void generateScramblerToPosition(int position) {
        vector<string> arrayOfScrambledLetters = splitIntoCharacters(toUpperCase(getLettersToCypher()));
        vector<string> alphabet = splitIntoCharacters("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        int counter = 0;
        while (counter <= 25) {
            this->scrambledLetters[position][0] = alphabet[counter];
            this->scrambledLetters[position][1] = arrayOfScrambledLetters[counter];
            if (position < 25) {
                position++;
            }
            else {
                position = 0;
            }
            counter++;
        }
    }
    void printScrambler() {
        for (auto& x : this->scrambledLetters) {
            cout << "{" << x[0] << ", " << x[1] << "}" << endl;
        }
    }
    DataOfLetter getOutputIndexIn(DataOfLetter letter) {
        int positionOnCall = getPosition();
        if (letter.ShouldRotate()) {
            rotate();
        }

        int indexOfInputLetter = letter.getIndexOfNextLetter();
        string foundLetter = this->scrambledLetters[indexOfInputLetter][1];
        int indexOfLetterInAlphabet = 0;
        for (const auto& pairs : scrambledLetters) {
            if (pairs[0] == foundLetter) {
                break;
            }
            else {
                indexOfLetterInAlphabet++;
            }
        }
        bool shouldRotateNext = false;
        int positionAtEnd = getPosition();
        if (positionOnCall == positionAtEnd - 1 && getNotchToRotateNext() == positionOnCall) {
            shouldRotateNext = true;
        }
        DataOfLetter outputLetter = DataOfLetter(foundLetter, shouldRotateNext);
        outputLetter.setIndexOfNextLetter(indexOfLetterInAlphabet);
        return outputLetter;
    }

    DataOfLetter getOutputIndexOut(DataOfLetter letter) {
        int indexOfInputLetter = letter.getIndexOfNextLetter();

        string foundLetter = scrambledLetters[indexOfInputLetter][0];

        int indexOfLetterInRotorsAlphabet = 0;

        for (auto pairs : scrambledLetters) {
            if (pairs[1] == foundLetter) {
                break;
            }
            else {
                indexOfLetterInRotorsAlphabet++;
            }
        }
        DataOfLetter out = DataOfLetter(foundLetter, false);
        out.setIndexOfNextLetter(indexOfLetterInRotorsAlphabet);
        return out;
    }

};

string getLetterFromAlphabetByIndex(int index) {
    string alphabet[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
    return alphabet[index];
};

class Plugboard {
private:
    unordered_map<string, string> plug;
    vector<string> swaps;
public:
    Plugboard() {
    }
    Plugboard(string mapOfKeys) {
        vector<string> duo;
        istringstream iss(mapOfKeys);
        string token;

        while (getline(iss, token, ' ')) {
            duo.push_back(token);
        }

        for (string x : duo) {
            string swap1 = x.substr(0, 1);
            string swap2 = x.substr(1, 1);
            transform(swap1.begin(), swap1.end(), swap1.begin(), ::toupper);
            transform(swap2.begin(), swap2.end(), swap2.begin(), ::toupper);

            bool conflict = false;
            for (const auto& pair : plug) {
                if (pair.first == swap1 || pair.first == swap2) {
                    conflict = true;
                    break;
                }
            }

            if (!conflict) {
                plug[swap1] = swap2;
                plug[swap2] = swap1;
                swaps.push_back(swap1 + swap2);
            }
            else {
                cout << "Set " << swap1 << swap2 << " has been refused!" << endl;
            }
        }
    }

    string plugboardLetter(string letter) {
        string swappedLetter = plug[letter];
        if (swappedLetter.empty()) {
            return letter;
        }
        else {
            return swappedLetter;
        }
    }

    void printSwaps() {
        for (string x : swaps) {
            cout << x << " ";
        }
        cout << endl;
    }
};

class Reflector {
private:
    vector<string> lettersToReflect;
    string hash;

public:
    Reflector() {}

    Reflector(string lettersToReflect) {
        this->hash = lettersToReflect;

        string uppercaseLetters = lettersToReflect;

        transform(uppercaseLetters.begin(), uppercaseLetters.end(), uppercaseLetters.begin(), ::toupper);

        for (char c : uppercaseLetters) {
            this->lettersToReflect.push_back(string(1, c));
        }
    }

    DataOfLetter reflectLetter(DataOfLetter letter, Rotor* rotorBeforeReflector) {
        unordered_map<string, string> dictionaryOfReflections;
        string alphabet[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        int i = 0;
        for (const string& x : lettersToReflect) {
            dictionaryOfReflections.emplace(alphabet[i], x);
            i++;
        }

        DataOfLetter output(dictionaryOfReflections[letter.getLetter()], false);

        string** table = rotorBeforeReflector->getScrambledLetters();
        int indexOutput = 0;
        for (int row = 0; row < 26; ++row) {
            string* x = table[row];
            if (output.getLetter() == x[0]) {
                indexOutput = row;
                break;
            }
        }
        output.setIndexOfNextLetter(indexOutput);
        return output;
    }

    string getHash() {
        return this->hash;
    }
};

class Enigma {
public:
    Enigma() : codedMessage(" ") {}

    Rotor* getRotorRight() {
        return rotorRight;
    }

    Rotor* getRotorMid() {
        return rotorMid;
    }

    Rotor* getRotorLeft() {
        return rotorLeft;
    }

    Reflector* getReflector() {
        return reflector;
    }

    void setRotorRight(Rotor* rotor) {
        rotorRight = rotor;
    }

    void setRotorMid(Rotor* rotor) {
        rotorMid = rotor;
    }

    void setRotorLeft(Rotor* rotor) {
        rotorLeft = rotor;
    }

    void setReflector(Reflector* reflector) {
        this->reflector = reflector;
    }

    Plugboard* getPlugboard() {
        return plugboard;
    }

    string getCodedMessage() const {
        return codedMessage;
    }

    void addCodedMessage(const string& x) {
        if (codedMessage.size() % 6 == 0) {
            codedMessage += " ";
        }
        codedMessage += x;
    }

    void clearAndCredit() {
        system("cls");
        cout << "Welcome to Enigma coder made by Aleksander Dobija" << endl << endl;
    }

    void clearAndHelp() {
        cout << "Insert one letter or type whole message!" << endl;
        cout << "If you want stop coding type '***'" << endl;
        cout << "To check settings type '$settings$'" << endl;
        cout << "To print your whole coded message type '$output$'" << endl;
        cout << "To get help type '$help$'" << endl;
    }

    void start() {
        codedMessage = " ";
        clearAndCredit();
        setupRotorLeft();
        clearAndCredit();
        setupRotorMid();
        clearAndCredit();
        setupRotorRight();
        clearAndCredit();
        setupReflector();
        clearAndCredit();
        cout << "Setup plugboard:" << endl;
        cout << "Type letters to swap e.g. 'AB GH IO'" << endl;
        cout << "A will be swapped with B and B with A, G -> H and H -> G..." << endl;
        cout << "If you don't want to use plugboard just hit enter key" << endl;
        string swaps;
        getline(cin, swaps);
        if (!swaps.empty()) {
            plugboard = new Plugboard(swaps);
        }
        else {
            plugboard = new Plugboard();
        }
        clearAndCredit();
        clearAndHelp();

        bool shouldBeWorking = true;
        while (shouldBeWorking) {
            string usersLetter;
            getline(cin, usersLetter);
            if (usersLetter == "***") {
                shouldBeWorking = false;
            }
            else if (usersLetter == "$settings$") {
                clearAndCredit();
                printSettings();
            }
            else if (usersLetter == "$output$") {
                clearAndCredit();
                cout << getCodedMessage() << endl;
            }
            else if (usersLetter == "$help$") {
                clearAndHelp();
            }
            else {
                for (const auto& x : usersLetter) {
                    if (!isalpha(x)) {
                        continue;
                    }
                    int index = getIndexInAlphabet(plugboard->plugboardLetter(string(1, x)));

                    DataOfLetter dataOfLetter(string(1, x), true);
                    dataOfLetter.setIndexOfNextLetter(index);

                    DataOfLetter rotorRight = getRotorRight()->getOutputIndexIn(dataOfLetter);
                    DataOfLetter rotorMid = getRotorMid()->getOutputIndexIn(rotorRight);
                    DataOfLetter rotorLeft = getRotorLeft()->getOutputIndexIn(rotorMid);

                    DataOfLetter reflector = getReflector()->reflectLetter(rotorLeft, getRotorLeft());

                    DataOfLetter returningRotorLeft = getRotorLeft()->getOutputIndexOut(reflector);
                    DataOfLetter returningRotorMid = getRotorMid()->getOutputIndexOut(returningRotorLeft);
                    DataOfLetter returningRotorRight = getRotorRight()->getOutputIndexOut(returningRotorMid);

                    string letterAfterPlugboard = plugboard->plugboardLetter(getLetterFromAlphabetByIndex(returningRotorRight.getIndexOfNextLetter()));
                    cout << "Cyphered letter -> " << letterAfterPlugboard << endl;
                    addCodedMessage(letterAfterPlugboard);
                }
            }
        }
        cout << "Shutting down..." << endl;
    }

private:
    Rotor* rotorRight = nullptr;
    Rotor* rotorMid = nullptr;
    Rotor* rotorLeft = nullptr;
    Reflector* reflector = nullptr;
    string codedMessage;
    Plugboard* plugboard = nullptr;

    int getIndexInAlphabet(const string& letter) {
        string alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        auto it = find(alphabet.begin(), alphabet.end(), toupper(letter[0]));
        if (it != alphabet.end()) {
            return distance(alphabet.begin(), it);
        }
        return -1;
    }

    string getLetterFromAlphabetByIndex(int index) {
        string alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return string(1, alphabet[index]);
    }

    void setupReflector() {
        cout << "Setting reflector" << endl;
        cout << "Please choose Reflector A [A], Reflector B [B], Reflector C [C]" << endl;
        cout << "or write your custom reflector - type hash e.g. 'VZBRGITYUPSDNHLXAWMJQOFECK' \nletters in your hash should be unique,\nwrite only letters" << endl;
        string reflectorResponse;
        getline(cin, reflectorResponse);
        if (reflectorResponse == "A") {
            setReflector(new Reflector("EJMZALYXVBWFCRQUONTSPIKHGD"));
        }
        else if (reflectorResponse == "B") {
            setReflector(new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT"));
        }
        else if (reflectorResponse == "C") {
            setReflector(new Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL"));
        }
        else {
            setReflector(new Reflector(reflectorResponse));
        }
        cout << "Reflector has been successfully set!" << endl;
    }

    void setupRotorRight() {
        cout << "Setting right rotor:" << endl;
        cout << "Please choose Rotor1 [1], Rotor2 [2], Rotor3 [3], Rotor4 [4], Rotor5 [5]" << endl;
        cout << "or write your hash e.g. 'VZBRGITYUPSDNHLXAWMJQOFECK' - letters in your hash should be unique, write only letters" << endl;
        string response;
        getline(cin, response);
        if (response == "1") {
            setRotorRight(configRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 17));
        }
        else if (response == "2") {
            setRotorRight(configRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", 5));
        }
        else if (response == "3") {
            setRotorRight(configRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 22));
        }
        else if (response == "4") {
            setRotorRight(configRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB", 10));
        }
        else if (response == "5") {
            setRotorRight(configRotor("VZBRGITYUPSDNHLXAWMJQOFECK", 25));
        }
        else {
            setRotorRight(configRotor(response));
        }
    }

    void setupRotorMid() {
        cout << "Setting middle rotor:" << endl;
        cout << "Please choose Rotor1 [1], Rotor2 [2], Rotor3 [3], Rotor4 [4], Rotor5 [5]" << endl;
        cout << "or write your hash e.g. 'VZBRGITYUPSDNHLXAWMJQOFECK' - letters in your hash should be unique, write only letters" << endl;
        string response;
        getline(cin, response);
        if (response == "1") {
            setRotorMid(configRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 17));
        }
        else if (response == "2") {
            setRotorMid(configRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", 5));
        }
        else if (response == "3") {
            setRotorMid(configRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 22));
        }
        else if (response == "4") {
            setRotorMid(configRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB", 10));
        }
        else if (response == "5") {
            setRotorMid(configRotor("VZBRGITYUPSDNHLXAWMJQOFECK", 25));
        }
        else {
            setRotorMid(configRotor(response));
        }
        cout << "Middle rotor has been successfully set!" << endl;
    }

    void setupRotorLeft() {
        cout << "Setting left rotor:" << endl;
        cout << "Please choose Rotor1 [1], Rotor2 [2], Rotor3 [3], Rotor4 [4], Rotor5 [5]" << endl;
        cout << "or write your hash e.g. 'VZBRGITYUPSDNHLXAWMJQOFECK' - letters in your hash should be unique, write only letters" << endl;
        string response;
        getline(cin, response);
        if (response == "1") {
            setRotorLeft(configRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 17));
        }
        else if (response == "2") {
            setRotorLeft(configRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", 5));
        }
        else if (response == "3") {
            setRotorLeft(configRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 22));
        }
        else if (response == "4") {
            setRotorLeft(configRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB", 10));
        }
        else if (response == "5") {
            setRotorLeft(configRotor("VZBRGITYUPSDNHLXAWMJQOFECK", 25));
        }
        else {
            setRotorLeft(configRotor(response));
        }
        cout << "Left rotor has been successfully set!" << endl;
    }

    Rotor* configRotor(const string& hash, int notchOfTurningNext) {
        cout << "Insert position (0-25):" << endl;
        int position;
        cin >> position;
        cin.ignore();
        Rotor* rotor = new Rotor(position, checkHash(hash), notchOfTurningNext);
        rotor->setPositionOnStart(position);
        return rotor;
    }

    Rotor* configRotor(const string& hash) {
        cout << "Insert position (0-25):" << endl;
        int position;
        cin >> position;
        cin.ignore();
        cout << "Insert notch of turning next rotor (0-25):" << endl;
        int notchOfTurningNext;
        cin >> notchOfTurningNext;
        cin.ignore();
        Rotor* rotor = new Rotor(position, checkHash(hash), notchOfTurningNext);
        rotor->setPositionOnStart(position);
        return rotor;
    }

    string checkHash(const string& hash) {
        if (hash.size() != 26) {
            throw invalid_argument("Error - your hash doesn't have exactly 26 letters");
        }
        vector<char> hashTab(hash.begin(), hash.end());
        vector<char> checkTab;
        for (const auto& x : hashTab) {
            if (find(checkTab.begin(), checkTab.end(), x) != checkTab.end()) {
                throw invalid_argument("Error - your hash doesn't have unique 26 letters");
            }
            checkTab.push_back(x);
        }
        return hash;
    }

    void printSettings() {
        cout << string(100, '*') << endl;
        cout << "Settings of: " << endl;
        cout << endl;
        cout << "Rotor left" << endl;
        cout << "Starting position: " << getRotorLeft()->getPositionOnStart() << endl;
        cout << "Notch to rotate next rotor " << getRotorLeft()->getNotchToRotateNext() << endl;
        cout << "Hash of rotor: " << getRotorLeft()->getHash() << endl;
        cout << endl;
        cout << "Rotor middle" << endl;
        cout << "Starting position: " << getRotorMid()->getPositionOnStart() << endl;
        cout << "Notch to rotate next rotor " << getRotorMid()->getNotchToRotateNext() << endl;
        cout << "Hash of rotor: " << getRotorMid()->getHash() << endl;
        cout << endl;
        cout << "Rotor right" << endl;
        cout << "Starting position: " << getRotorRight()->getPositionOnStart() << endl;
        cout << "Notch to rotate next rotor " << getRotorRight()->getNotchToRotateNext() << endl;
        cout << "Hash of rotor: " << getRotorRight()->getHash() << endl;
        cout << endl;
        cout << "Reflector: " << endl;
        cout << getReflector()->getHash() << endl;
        cout << endl;
        cout << "Plugboard swaps: " << endl;
        getPlugboard()->printSwaps();
        cout << string(100, '*') << endl;
    }
};

int main()
{
    Enigma enigma;
    enigma.start();

    return 0;
}
