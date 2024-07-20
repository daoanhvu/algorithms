#include <vector>
#include <iostream>

void swap(std::vector<int> &arr, int i1, int i2) {
    int tmp = arr[i1];
    arr[i1] = arr[i2];
    arr[i2] = tmp;
}

void heapify(std::vector<int> &arr, int n, int idx) {
    int l = idx * 2 + 1;
    int r = l + 1;

    int maxIdx = idx;
    if ((l < n) && arr[idx] < arr[l]) {
        maxIdx = l;
    }

    if ( (r < n) && arr[maxIdx] < arr[r]) {
        maxIdx = r;
    }

    if (maxIdx != idx) {
        swap(arr, idx, maxIdx);
        heapify(arr, n, maxIdx);
    }
}

void heapify(std::vector<int> &arr) {
    int n = arr.size();
    int startIdx = n / 2 - 1;

    for (int i = startIdx; i >= 0; i--) {
        heapify(arr, n, i);
    }
}

void heapSort(std::vector<int> &arr) {
    int n = arr.size();
    int startIdx = n / 2 - 1;

    for (int i = startIdx; i >= 0; i--) {
        heapify(arr, n, i);
    }

    for (int i = n-1; i > 0; i--) {
        swap(arr, 0, i);
        n = n - 1;
        heapify(arr, n, 0);
    }
}

int main(int argc, char **args) {
    std::vector<int> arr = {5, 10, 3, 7, 20, 18, 5, 13, 27, 11};
    heapSort(arr);
    std::cout << std::endl;
    for (int j = 0; j < arr.size(); j++) {
        std::cout << arr[j] <<  ", ";
    }
    std::cout << std::endl;
    return 0;
}