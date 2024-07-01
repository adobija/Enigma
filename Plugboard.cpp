#include <iostream>
#include <unordered_map>
#include <vector>
#include <sstream>
#include <algorithm>

using namespace std;

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