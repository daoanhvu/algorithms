#include <iostream>
#include <vector>

int divisibility(const std::vector<int> &list, int limit, int divisor) {
    int n = list.size();
    int result = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < limit; j++) {
            if (list[i + j] % divisor == 0) {
                result++;
            } else 
                break;
        }
    }
    return result;
}

int main(int argc, char** args) {
    std::vector<int> l = {5, 10, 15, 20};
    int divisor = 5;
    int limit = 4;

    int result = divisibility(l, limit, divisor);

    std::cout << "Result " << result << std::endl;

    return 0;
}