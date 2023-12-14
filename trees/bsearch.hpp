
namespace algo {

    template <typename T>
    struct BTreeNode {
        struct BTreeNode<T> *left;
        struct BTreeNode<T> *right;
        T key;
    };

    template <typename T>
    void addNode(BTreeNode<T> *t, T akey) {
        if(t == nullptr) {
            t = new BTreeNode<T>;
            t->key = akey;
            t->left = nullptr;
            t->right = nullptr;
        }
    }

}