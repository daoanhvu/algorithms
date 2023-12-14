#ifndef _ALGORITHM_HPP_
#define _ALGORITHM_HPP_

#include <stack>

namespace algo {

    struct LeftRightIndex {
        int l;
        int r;
    };

    template<typename T>
    T partition(T *arr, int left, int right) {
        int pivot = arr[left];
        int l = left;
        int r = right;
        T tmp;

        while(l < r) {
            while(arr[l] < pivot && (l <= right)) {
                l++;
            }

            while(arr[r] > pivot && (r >= left)) {
                r--;
            }

            if(l < r) {
                if(arr[l] != arr[r]) {
                    tmp = arr[r];
                    arr[r] = arr[l];
                    arr[l] = tmp;
                } else {
                    l++;
                }
            }
        }
        
        return l;
    }

    int partition1(int arr[], int start, int end) {
 
        int pivot = arr[start];
        int count = 0;
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] <= pivot)
                count++;
        }
    
        // Giving pivot element its correct position
        int pivotIndex = start + count;
        //swap(arr[pivotIndex], arr[start]);
    
        // Sorting left and right parts of the pivot element
        int i = start, j = end;
    
        while (i < pivotIndex && j > pivotIndex) {
    
            while (arr[i] <= pivot) {
                i++;
            }
    
            while (arr[j] > pivot) {
                j--;
            }
    
            if (i < pivotIndex && j > pivotIndex) {
                // swap(arr[i++], arr[j--]);
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    
        return pivotIndex;
    }

    template<typename T>
    void quickSort(T *arr, int left, int right) {
        std::stack<LeftRightIndex> aStack;
        LeftRightIndex idx;
        LeftRightIndex lr1, lr2;

        idx.l = left;
        idx.r = right;
        aStack.push(idx);

        int l;
        int r;

        while(!aStack.empty()) {
            LeftRightIndex lr = aStack.top();
            aStack.pop();

            l = lr.l;
            r = lr.r;

            int m = algo::partition(arr, l, r);

            if(l < m) {
                lr1.l = l;
                lr1.r = m - 1;
                if(lr1.l < lr.r) {
                    aStack.push(lr1);
                }
            }

            if(m < r) {
                lr2.l = m + 1;
                lr2.r = r;
                if(lr2.l < lr2.r) {
                    aStack.push(lr2);
                }
            }
        }
    }
}

#endif