package repository;

import java.util.List;

/**
 * Contrato genérico para repositórios.
 *
 * @param <T> tipo de entidade armazenada
 */
public interface Repositorio<T> {
    void cadastrar(T item);
    T buscar(String id);
    void remover(String id);
    List<T> listarTodos();
}
