#include <cctype>
#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
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