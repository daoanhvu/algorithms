#ifndef _ALGORITHM_HPP_
#define _ALGORITHM_HPP_

#include <stack>

#define MAX_THRESH 4

/**
 * A simple stackk
*/
#define STACK_SIZE (8 * sizeof(size_t))
#define PUSH(l,r) ((void) ((top->l = (l)), (top->r = (r)), ++top))
#define POP(l,r) ((void) (--top, (l = top->l), (r = top->r)))
#define STACK_NOT_EMPTY (stack < top)

namespace algo {

    struct StackNode {
        char *l;
        char *r;
    };

    struct LeftRightIndex {
        int l;
        int r;
    };

    template<typename T>
    T partition(T *arr, int left, int right) {
        T pivot = arr[left];
        if(right - left > 1)
            pivot = median(arr[left], arr[left+1], arr[left+2]);
        int l = left;
        int r = right;
        T tmp;

        while(l < r) {
            while(arr[l] < pivot) {
                l++;
            }

            while(arr[r] > pivot) {
                r--;
            }

            if(l < r) {
                if(arr[l] != arr[r]) {
                    tmp = arr[r];
                    arr[r] = arr[l];
                    arr[l] = tmp;
                } else {
                    r--;
                }
            }
        }
        
        return l;
    }

    template<typename T>
    void interativeQuickSort(T *arr, int left, int right) {

        if(right - left < MAX_THRESH) {
            int j;
            T val;
            for(int i=left; i<=right; i++) {
                val = arr[i];
                j = i - 1;
                while(j>=left && arr[j] > val) {
                    arr[j+1] = arr[j];
                    j--;
                }
                arr[j+1] = val;
            }
            return;
        }

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

    template <typename T>
    T median(T a, T b, T c) {
        if(a < b) {
            if (b < c)
                return b;
            if(a > c)
                return a;
            return c;
        }

        if(b > c) {
            return b;
        }
        if(a > c)
            return c;
        return a;
    }

    template<typename T>
    void quickSort(T *arr, int left, int right) {
        

        if(right - left < MAX_THRESH) {
            int j;
            T val;
            for(int i=left; i<=right; i++) {
                val = arr[i];
                j = i - 1;
                while(j>=left && arr[j] > val) {
                    arr[j+1] = arr[j];
                    j--;
                }
                arr[j+1] = val;
            }
            return;
        }
        
        StackNode stack[STACK_SIZE];
        StackNode *top = stack;
        
    }
}

#endif