#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <fstream>
#include <sstream>

int* readFromFile(const char *filename, int &len, int &d) {
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
    ss >> d;

    int *arr = new int[len];

    for(int i=0; i<len; i++) {
        ssData >> temp;
        arr[i] = temp;
    }

    dataFile.close();

    return arr;
}

/**
 * The key point of this problem is the RELATION between position of an element and it's frequency 
 * in the SORTED array 
*/
float findMedian(int d, std::map<int, int> freq, int m) {
    float median;
    int sum_freq = 0;
    if(d % 2 == 0) {
        // After sorting, median is the average of the d/2 - 1 th element and d/2 th element (ZERO BASED)
        int p1 = -1;
        int p2 = -1;
        for(int v=0; v<=200; v++) {
            sum_freq += freq[v];
            //v is the first median element if and only if v has been found at m or after that and v has not been counted
            if(m <= sum_freq && p1 < 0) {
                p1 = v;
            }

            //In case the frequency of v is more than 1 then
            if(m + 1 <= sum_freq) {
                p2 = v;
                median = (p1 + p2) / 2.0f;
                break;
            } 
        }
    } else {
        //
        for(int v=0; v<=200; v++) {
            sum_freq += freq[v];
            if(m+1 <= sum_freq) {
                median = v;
                break;
            }
        }
    }
    return median;
}

float findMedian_nguoita(int d, std::map<int, int> freq) {
    float median = 0;
    int sum_freq = 0;
    if(d % 2 == 0) {
        // average of d/2 - 1 th and d/2 th number is median
        int n = -1, m = -1;
        for(int j = 0; j<=200; j++) {
            sum_freq += freq[j];
            if(n<0 && sum_freq >= d/2){
                n = j;
            }
            if( sum_freq >= d/2 + 1){
                m = j;
                median = (m+n)/2.0;
                break;
            }
        }
    } else {
        // d/2 th is median
        for(int j = 0; j<=200; j++){
            sum_freq += freq[j];
            if(sum_freq >= d/2 + 1){
                median = j;
                break;
            }
        }
    }

    return median;
}

int activityNotifications(int *expenditure, int size, int d) {
    if(size <= d) {
        return 0;
    }

    int count = 0;
    std::map<int, int> freq;
    int m = d / 2;

    for(int i=0; i<d; i++) {
        freq[expenditure[i]]++;
    }

    int count_freq;
    float median;

    for(int i = d; i < size; i++) {
        median = findMedian_nguoita(d, freq);

        if(expenditure[i] >= (2 * median)) {
            count++;
        }

        freq[expenditure[i]]++;
        freq[expenditure[i-d]]--;
    }


    return count;
}

int main(int argc, char** args) {
    if(argc < 2) {
        std::cout << "Argument file missing." << std::endl;
        return -1;
    }
    int len;
    int d;
    int *arr = readFromFile(args[1], len, d);
    int count = activityNotifications(arr, len, d);
    
    std::cout << "Count of notification: " << count << std::endl;
    delete[] arr;
    return 0;
}