#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <algorithm>
#include <chrono>
#include <cstring> //for memcpy
#include "header/algorithms.hpp"

void printArray(const int *arr, int n) {

    std::cout << std::endl;
    for(int i=0; i<n; i++) {
        std::cout << arr[i] << " ";
    }
    std::cout << std::endl;
}

int* readFromFile(const char *filename, int &len) {
    std::ifstream dataFile(filename);
    if(!dataFile.is_open()) {
        return nullptr;
    }
    std::string first_line;
    std::string data_line;
    getline(dataFile, first_line);
    getline(dataFile, data_line);
    std::istringstream ss(first_line);
    std::istringstream ssData(data_line);
    int temp;
    ss >> len;

    int *arr = new int[len];

    for(int i=0; i<len; i++) {
        ssData >> temp;
        arr[i] = temp;
    }

    dataFile.close();

    return arr;
}

int isGreater(const void* a, const void* b) {
    return a > b;
}
long sortDurationBySTL(int *arr, int len) {
    auto beforeTime = std::chrono::system_clock::now().time_since_epoch();
    auto beforeNano = std::chrono::duration_cast<std::chrono::nanoseconds>(beforeTime);
    std::qsort(arr, len, sizeof(int), isGreater);
    auto afterTime = std::chrono::system_clock::now().time_since_epoch();
    auto afterNano = std::chrono::duration_cast<std::chrono::nanoseconds>(afterTime);
    auto executionTime = afterNano - beforeNano;
    return executionTime.count();
}

long customSort(int *arr, int len) {
    auto beforeTime = std::chrono::system_clock::now().time_since_epoch();
    auto beforeNano = std::chrono::duration_cast<std::chrono::nanoseconds>(beforeTime);
    // algo::quickSort<int>(arr, 0, len - 1);
    algo::interativeQuickSort<>(arr, 0, len - 1);
    auto afterTime = std::chrono::system_clock::now().time_since_epoch();
    auto afterNano = std::chrono::duration_cast<std::chrono::nanoseconds>(afterTime);
    auto executionTime = afterNano - beforeNano;
    return executionTime.count();
}

int main(int argc, char** args) {

    if(argc < 2) {
        std::cout << "Argument file missing." << std::endl;
        return -1;
    }
    int len;
    int *arr = readFromFile(args[1], len);
    int *arrCopy = new int[len];
    memcpy(arrCopy, arr, sizeof(int) * len);

    long executionTimeNano = sortDurationBySTL(arr, len);
    long executionTimeNano2 = customSort(arrCopy, len);
    
    std::cout << "Execution time (nanoseconds) by STL: " << executionTimeNano << std::endl;
    std::cout << "Execution time (nanoseconds) by my custom: " << executionTimeNano2 << std::endl;
    printArray(arrCopy, len);
    delete[] arr;
    delete[] arrCopy;
    return 0;
}

int main1( int argc, char** args) {

    if(argc < 2) {
        std::cout << "Argument file missing." << std::endl;
        return -1;
    }
    int arr[] = {2, 3, 4, 2, 3};
    int len = sizeof(arr) / sizeof(int);

    algo::quickSort<int>(arr, 0, len - 1);
    printArray(arr, len);
    return 0;
}