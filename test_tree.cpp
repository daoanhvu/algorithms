#include <iostream>
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