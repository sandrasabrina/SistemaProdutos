package repository;

import java.util.List;

/**
 * Interface genérica que define operações básicas para repositórios de entidades.
 *
 * @param <T> Tipo de entidade gerenciada pelo repositório
 */
public interface Repositorio<T> {

    /**
     * Adiciona um novo item ao repositório.
     * @param item entidade a ser cadastrada
     */
    void cadastrar(T item);

    /**
     * Busca um item pelo identificador único.
     * @param id identificador do item
     * @return item correspondente ou null se não encontrado
     */
    T buscar(String id);

    /**
     * Remove um item, caso exista, com o ID fornecido.
     * @param id identificador do item a ser removido
     */
    void remover(String id);

    /**
     * Retorna uma lista com todos os itens cadastrados.
     * @return lista de todos os itens
     */
    List<T> listarTodos();
}
