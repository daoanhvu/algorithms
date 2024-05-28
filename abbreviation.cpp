#include <string>
#include <cstring>
#include <vector>
#include <iostream>

bool isLowerCase(char ch) {
    return ch >= 'a' && ch <= 'z';
}

char toUpper(char ch) {
    return ch - 32;
}

std::string abbreviation(std::string a, std::string b) {

    int al = a.length();
    int bl = b.length();

    if(bl > al) {
        return std::string("NO");
    }

    int **ok = new int*[al];
    for(auto i=0; i<al; i++) {
        ok[i] = new int[bl];

        for(auto k=0; k<bl; k++) ok[i][k] = 0;
    }
    
    for(auto j=0; j<bl; j++) {
        for(auto i=0; i<al; i++) {
            if(a[i] == b[j]) {
                ok[i][j] = 1;
                std::cout << "ok[" << i << "][" << j << "] " << 1 << std::endl;
            } else {
                if(isLowerCase(a[i])) {
                    ok[i][j] = 1;
                }
            }
        }
    }

    int result = ok[al-1][bl-1];
    for(auto i=0; i<al; i++) {
        delete[] ok[i];
    }
    delete[] ok;

    return result == 0 ? std::string("NO") : std::string("YES");
}

int main(int argc, char** args) {
    std::string a = "sYOCa";
    std::string b = "YOCN";
    //std::string a = "WQmyyYHjBv";
    //std::string b = "WQYHB";
    std::string result = abbreviation(a, b);
    std::cout << "Result: " << result << std::endl;
    return 0;
}