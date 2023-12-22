#include <ostream>
#include <iostream>

namespace algo {

    template <typename T>
    struct BTreeNode {
        struct BTreeNode<T> *left;
        struct BTreeNode<T> *right;
        struct BTreeNode<T> *parent;
        T key;
    };

    template <typename T>
    BTreeNode<T>* addNode(BTreeNode<T> **t, T akey) {
        if(*t == nullptr) {
            *t = new BTreeNode<T>;
            (*t)->key = akey;
            (*t)->left = nullptr;
            (*t)->right = nullptr;
            (*t)->parent = nullptr;
            return *t;
        }

        bool done = false;
        BTreeNode<T> *subt = *t;
        while(!done) {
            if(akey < subt->key) {
                if(subt->left == nullptr) {
                    subt->left = new BTreeNode<T>;
                    subt->left->key = akey;
                    subt->left->left = nullptr;
                    subt->left->right = nullptr;
                    subt->left->parent = subt;
                    done = true;
                } else {
                    subt = subt->left;
                }
            } else {
                if(subt->right == nullptr) {
                    subt->right = new BTreeNode<T>;
                    subt->right->key = akey;
                    subt->right->left = nullptr;
                    subt->right->right = nullptr;
                    subt->right->parent = subt;
                    done = true;
                } else {
                    subt = subt->right;
                }
            }
        }
        return subt;
    }

    template <typename T>
    void deleteKey(BTreeNode<T> **t, T akey) {
        BTreeNode<T> *subt = *t;

        // Special case, *t doesn't have children
        if((subt != nullptr) && (subt->left == nullptr) && (subt->right == nullptr)) {
            if(subt->parent != nullptr) {
                if(subt->parent->left == subt) {
                    subt->parent->left = nullptr;
                } else if(subt->parent->right == subt) {
                    subt->parent->right = nullptr;
                }
                subt->parent = nullptr;
            }

            delete subt;
            *t = nullptr;
            return;
        }


        while(subt != nullptr) {
            if(akey == subt->key) {
                // Find the left-most of right child of t and move it to t
                BTreeNode<T> *pLMChild = findLeftMostChild(subt->right);
                if(pLMChild != nullptr) {
                    subt->key = pLMChild->key;
                    BTreeNode<T> *childParent = pLMChild->parent;
                    if(pLMChild->right == nullptr) {
                        // Case 1: The left-most child doesn't have right child
                        if(childParent == subt) {
                            subt->right = nullptr;
                        } else {
                            childParent->left = nullptr;
                        }
                    } else {
                        // Case 2: The left-most child has right child
                        if(childParent == subt) {
                            childParent->right = pLMChild->right;
                            childParent->right->parent = childParent;
                        } else {
                            childParent->left = pLMChild->right;
                            childParent->left->parent = childParent;
                        }
                    }
                    pLMChild->parent = nullptr;
                    delete pLMChild;
                    subt = nullptr;
                } else {
                    if(subt->left == nullptr) {
                        if(subt->parent != nullptr) {
                            if(subt->parent->left == subt) {
                                subt->parent->left = nullptr;
                            } else if(subt->parent->right == subt) {
                                subt->parent->right = nullptr;
                            }
                            subt->parent = nullptr;
                        }

                        delete subt;
                        subt = nullptr;
                    }
                }
            } else {
                if(akey < subt->key) {
                    subt = subt->left;
                } else {
                    subt = subt->right;
                }
            }
        }
    }

    template <typename T>
    BTreeNode<T>* findLeftMostChild(BTreeNode<T> *t) {
        BTreeNode<T> *node = t;
        while(node != nullptr) {
            if(node->left == nullptr) {
                return node;
            }
            node = node->left;
        }
        return node;
    }

    template <typename T>
    void printTree(BTreeNode<T> *t, std::ostream& out) {
        if(t == nullptr) {
            return;
        }

        printTree(t->left, out);
        out << t->key << " ";
        printTree(t->right, out);
    }

}