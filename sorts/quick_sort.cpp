#include "..\header\algorithms.hpp"
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

            int m = partitionA(a, l, r, track);

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