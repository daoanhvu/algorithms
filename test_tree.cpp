#include <iostream>
#include <string>
#include <algorithm>
#include <functional>
#include <unordered_map>

int countTriplets(std::vector<int> arr) {
    return -1;
}

std::vector<int> freqQueries(std::vector<std::vector<int>> queries) {
    std::vector<int> outputs;
    std::unordered_map<int, int> valueFreq;
    std::unordered_map<int, int> freqValues; 

    int queryCount = queries.size();

    for(auto i=0; i<queryCount; ++i) {
        std::vector<int> query = queries[i];
        int cmd = query[0];
        int value = query[1];

        switch(cmd) {
            case 1:
                int oldFreq = valueFreq[value];
                valueFreq[value]++;
                freqValues[valueFreq[value]]++;
            break;

            case 2:
                if(valueFreq[value] > 0)
                    valueFreq[value]--;
            break;

            case 3:

            break;

            default:
            // Should not reach here!
            break;
        }
    }

    return outputs;
}
#include "trees/bsearch.hpp"

int main(int argc, char** args) {
    algo::BTreeNode<int> *tree;

    algo::addNode(&tree, 100);
    algo::printTree(tree, std::cout);
    algo::deleteKey(&tree, 100);

    if(tree == nullptr) {
        std::cout << "Tree released completely" << std::endl;
    }
    
    return 0;
}