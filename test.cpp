#include <iostream>
#include "header/algorithms.hpp"

void printArray(const int *arr, int n) {

    std::cout << std::endl;
    for(int i=0; i<n; i++) {
        std::cout << arr[i] << " ";
    }
    std::cout << std::endl;
}

int main(int argc, char** args) {
    int arr[] = {3, 5, 6, 7, 3, 2, 40, 1, 101, 85, 61, 82, 8, 71, 39, 28};
    algo::quickSort<int>(arr, 0, 8);
    printArray(arr, 9);
    return 0;
}