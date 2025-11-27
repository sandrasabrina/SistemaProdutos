package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import model.InfoProd;

/**
 * Implementação simples de repositório baseada em HashMap.
 *
 * @param <T> tipo da entidade
 */
@InfoProd(versao = "3.0", categoria = "Persistencia")
public class RepositorioHash<T> implements Repositorio<T> {

    private final Map<String, T> storage = new HashMap<>();
    private final Function<T, String> idExtractor;

    public RepositorioHash(Function<T, String> idExtractor) {
        this.idExtractor = Objects.requireNonNull(idExtractor, "idExtractor não pode ser nulo");
    }

    @Override
    public void cadastrar(T item) {
        Objects.requireNonNull(item, "item não pode ser nulo");
        String id = idExtractor.apply(item);
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID extraído não pode ser nulo/vazio");
        storage.put(id, item);
    }

    @Override
    public T buscar(String id) {
        return storage.get(id);
    }

    @Override
    public void remover(String id) {
        storage.remove(id);
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(storage.values());
    }
}
