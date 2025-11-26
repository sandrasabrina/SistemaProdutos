package repository;

import java.util.List;

// Interface genérica Repositorio<T>
public interface Repositorio<T> {
    void cadastrar(T item);
    T buscar(String id);
    void remover(String id);
    List<T> listarTodos();
}
