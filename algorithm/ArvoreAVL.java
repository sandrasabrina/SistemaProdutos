package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de árvore AVL (balanceada) para indexação por chave.
 * Mantém O(log n) em inserções e buscas.
 *
 * @param <K> tipo da chave (Comparable)
 * @param <V> tipo do valor
 */
public class ArvoreAVL<K extends Comparable<K>, V> implements ArvoreBusca<K, V> {

    // Nó interno
    private class Node {
        K key;
        V value;
        int height;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node root;

    // --- utilitários ---
    private int nodeHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalanceFactor(Node node) {
        return (node == null) ? 0 : nodeHeight(node.left) - nodeHeight(node.right);
    }

    // rotações
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(nodeHeight(y.left), nodeHeight(y.right)) + 1;
        x.height = Math.max(nodeHeight(x.left), nodeHeight(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(nodeHeight(x.left), nodeHeight(x.right)) + 1;
        y.height = Math.max(nodeHeight(y.left), nodeHeight(y.right)) + 1;

        return y;
    }

    // inserção recursiva mantendo balanceamento
    private Node insertNode(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insertNode(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insertNode(node.right, key, value);
        } else {
            // mesma chave: atualiza valor
            node.value = value;
            return node;
        }

        node.height = 1 + Math.max(nodeHeight(node.left), nodeHeight(node.right));
        int balance = getBalanceFactor(node);

        // Left Left
        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }
        // Right Right
        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }
        // Left Right
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Left
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // busca recursiva
    private V searchNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node.value;
        if (cmp < 0) return searchNode(node.left, key);
        return searchNode(node.right, key);
    }

    // travessia em ordem
    private void inOrderTraversal(Node node, List<V> out) {
        if (node == null) return;
        inOrderTraversal(node.left, out);
        out.add(node.value);
        inOrderTraversal(node.right, out);
    }

    // --- interface Arvore ---
    @Override
    public void inserir(K chave, V valor) {
        if (chave == null) throw new IllegalArgumentException("chave não pode ser nula");
        root = insertNode(root, chave, valor);
    }

    @Override
    public V buscar(K chave) {
        if (chave == null) return null;
        return searchNode(root, chave);
    }

    @Override
    public List<V> listarEmOrdem() {
        List<V> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }
}
