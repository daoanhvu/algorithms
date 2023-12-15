
namespace algo {

    template <typename T>
    struct BTreeNode {
        struct BTreeNode<T> *left;
        struct BTreeNode<T> *right;
        T key;
    };

    template <typename T>
    BTreeNode<T>* addNode(BTreeNode<T> *t, T akey) {
        if(t == nullptr) {
            t = new BTreeNode<T>;
            t->key = akey;
            t->left = nullptr;
            t->right = nullptr;
            return t;
        }

        if(akey < t->key) {
            return addNode(t->left, akey);
        }
        return addNode(t->right, akey);
    }

}