
namespace algo {
    template <typename T>
    struct RedBlackNode {
        struct RedBlackNode<T> *left;
        struct RedBlackNode<T> *right;
        T key;
        T median;
    };

    template <typename T>
    void addKey(RedBlackNode<T>* t, T aKey) {

    }

    template <typename T>
    void removeKey(RedBlackNode<T>* t, T aKey) {

    }

    template <typename T>
    RedBlackNode<T> *searcgKey(RedBlackNode<T>* t, T key) {

    }
}