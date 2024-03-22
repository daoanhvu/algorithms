#include <iostream>
#include <string>
#include <unordered_map>

std::unordered_map<char, int> ROMAN = {
        {'I', 1},
        {'V', 5},
        {'X', 10},
        {'L', 50},
        {'C', 100},
        {'D', 500},
        {'M', 1000}
    };

int romanToInt(std::string str) {
    
    int result = 0;
    int n = str.length();
    char ch;

    int i = 0;
    int j;
    int temp;

    while (i<n) {
        ch = str[i];
        temp = ROMAN[ch];
        j = i + 1;
        while (j < n && str[j] == ch) {
            temp += ROMAN[ch];
            j++;
        }

        if (j < n) {
            if (ch == 'I') {
                if (str[j] == 'V' || str[j] == 'X') {
                    temp = ROMAN[str[j]] - temp;
                    j++;
                }
            }

            if (ch == 'X') {
                if (str[j] == 'L' || str[j] == 'C') {
                    temp = ROMAN[str[j]] - temp;
                    j++;
                }
            }

            if (ch == 'C') {
                if (str[j] == 'D' || str[j] == 'M') {
                    temp = ROMAN[str[j]] - temp;
                    j++;
                }
            }
        }

        result += temp;
        i = j;
    }

    return result;
}

int romanToInt2(std::string str) {
    int n = str.length();
    int result = 0;

    for (auto i=0; i<n-1; i++) {
        if(ROMAN[str[i]] < ROMAN[str[i+1]]) {
            result -= ROMAN[str[i]];
        } else result += ROMAN[str[i]];
    }
    result += ROMAN[str[n-1]];
    return result;
}
/**
 * 
 * MMCIX = 2109
*/
int main(int argc, char **args) {
    if (argc <= 1) {
        std::cout << "Not enough parameters." << std::endl;
        return -1;
    }
    std::string romanStr(args[1]);
    int number = romanToInt(romanStr);
    std::cout << "Roman " << romanStr << " -> " << number << std::endl;
    return 0;
}