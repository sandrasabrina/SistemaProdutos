package algorithm;

import java.util.ArrayList;
import java.util.List;

// Implementação de Árvore AVL (Adelson-Velsky and Landis) para indexação balanceada
// Assegura complexidade O(log n) para inserção e busca.
public class AVLTree<K extends Comparable<K>, V> implements Arvore<K, V> {
    
    // Representação do Nó
    private class Node {
        K key;
        V value;
        int height; // Altura do nó na árvore
        Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }
    
    private Node root;

    // Métodos utilitários
    private int height(Node N) {
        return (N == null) ? 0 : N.height;
    }

    private int getBalance(Node N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    // Rotações
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Método de inserção recursiva
    private Node insert(Node node, K key, V value) {
        if (node == null)
            return (new Node(key, value));

        int compare = key.compareTo(node.key);

        if (compare < 0)
            node.left = insert(node.left, key, value);
        else if (compare > 0)
            node.right = insert(node.right, key, value);
        else { // Chaves duplicadas: atualiza o valor e retorna
            node.value = value;
            return node;
        }

        // 1. Atualiza altura
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 2. Obtém o fator de balanceamento
        int balance = getBalance(node);

        // 3. Casos de desbalanceamento e Rotações

        // Left Left Case (Rotação Simples à Direita)
        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);

        // Right Right Case (Rotação Simples à Esquerda)
        if (balance < -1 && key.compareTo(node.right.key) > 0)
            return leftRotate(node);

        // Left Right Case (Rotação Dupla: Esquerda, depois Direita)
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case (Rotação Dupla: Direita, depois Esquerda)
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
    
    // Método de busca recursiva
    private V search(Node root, K key) {
        if (root == null) {
            return null;
        }
        int compare = key.compareTo(root.key);
        if (compare == 0) {
            return root.value;
        } else if (compare < 0) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    // Travessia In-Order (Em-Ordem) para listar elementos ordenadamente
    private void inOrder(Node node, List<V> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(node.value);
            inOrder(node.right, list);
        }
    }

    // --- Implementação da Interface Arvore ---

    @Override
    public void inserir(K chave, V valor) {
        root = insert(root, chave, valor);
    }

    @Override
    public V buscar(K chave) {
        return search(root, chave);
    }

    @Override
    public List<V> listarEmOrdem() {
        List<V> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }
}
