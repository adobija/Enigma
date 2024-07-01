#include <cctype>
#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
#include"DataOfLetter.cpp"
#include"Rotor.cpp"
#include <unordered_map>
using namespace std;

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

	DataOfLetter reflectLetter(DataOfLetter letter, Rotor rotorBeforeReflector) {
		unordered_map<string, string> dictionaryOfReflections;
		string alphabet[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		int i = 0;
		for (string x : lettersToReflect) {
			dictionaryOfReflections.emplace(alphabet[i], x);
			i++;
		}
		DataOfLetter output = DataOfLetter(dictionaryOfReflections[letter.getLetter()], false);
		string** table = rotorBeforeReflector.getScrambledLetters();
		int indexOutput = 0;
		for (int row = 0; row < 26; ++row) {
			string* x = table[row];
			if (output.getLetter() == x[0]) {
				indexOutput = row;
			}
			else {
				indexOutput++;
			}
		}
		return output;
	}

	string getHash() {
		return this->hash;
	}
};