package algorithm;

import java.util.List;

/**
 * Interface para estruturas árvore indexadas por chave comparável.
 *
 * @param <K> tipo da chave (Comparable)
 * @param <V> tipo do valor associado
 */
public interface ArvoreBusca<K extends Comparable<K>, V> {
    /**
     * Insere ou atualiza um par (chave, valor) na estrutura.
     */
    void inserir(K chave, V valor);

    /**
     * Busca o valor associado à chave; retorna null se não encontrado.
     */
    V buscar(K chave);

    /**
     * Retorna lista dos valores em ordem crescente das chaves.
     */
    List<V> listarEmOrdem();
}
