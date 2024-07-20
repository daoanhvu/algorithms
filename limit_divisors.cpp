#include <iostream>
#include <vector>

int divisibility(const std::vector<int> &list, int limit, int divisor) {
    int n = list.size();
    int result = 0;

    std::cout << std::endl;
    for (int i = 0; i < n; i++) {
        if (list[i] % divisor == 0) {
            result++;
            limit = limit < (n - i - 1)? limit : n - i - 1;
            std::cout << "Limit " << limit << std::endl;
            for (int j = 1; j < limit; j++) {
                int idx = i + j;
                if (list[idx] % divisor == 0) {
                    for (int k=i; k <= idx; k++) {
                        std::cout << list[k] << " ";
                    }
                    result++;
                    std::cout << "   idx = "<< idx << ", j="<< j << " --> " << result << std::endl;
                } else 
                    break;
            }
        }
    }
    return result;
}

int main(int argc, char** args) {
    // std::vector<int> l = {5, 10, 15, 20};
    // int divisor = 5;
    // int limit = 4;

    std::vector<int> l = {6, 9, 9, 6, 6};
    int divisor = 3;
    int limit = 3;

    int result = divisibility(l, limit, divisor);

    std::cout << "Result " << result << std::endl;

    return 0;
}