#include <iostream>
#include <vector>
#include <algorithm>
#include"DataOfLetter.cpp"
using namespace std;

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

	string* splitIntoCharacters(const string& str) {
		string* result = new string[str.size()];
		for (size_t i = 0; i < str.size(); ++i) {
			result[i] = str[i];
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
	Rotor() {}

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
		string* arrayOfScrambledLetters = splitIntoCharacters(toUpperCase(getLettersToCypher()));
		string* alphabet = splitIntoCharacters("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
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

		string foundLetter = this->scrambledLetters[indexOfInputLetter][0];

		int indexOfLetterInRotorsAlphabet = 0;

		for (const auto& pairs : scrambledLetters) {
			if (pairs[1] == foundLetter) {
				break;
			}
			else {
				indexOfLetterInRotorsAlphabet++;
			}
		}
		DataOfLetter outputLetter = DataOfLetter(foundLetter, false);
		outputLetter.setIndexOfNextLetter(indexOfLetterInRotorsAlphabet);
		return outputLetter;
	}

};