package algorithm;

import java.util.List;

// Interface Arvore<K, V> (Chave, Valor)
public interface Arvore<K extends Comparable<K>, V> {
    void inserir(K chave, V valor);
    V buscar(K chave);
    // Retorna a lista de valores em ordem crescente das chaves
    List<V> listarEmOrdem();
}
