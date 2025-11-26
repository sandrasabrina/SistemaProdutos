package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import model.InfoProd;

// Classe Genérica RepositorioHash<T>
@InfoProd(versao = "3.0", categoria = "Persistencia")
public class RepositorioHash<T> implements Repositorio<T> {
    
    // Armazenamento interno: HashMap para acesso rápido por ID
    private final Map<String, T> storage = new HashMap<>();
    
    // Função para extrair o ID do objeto T (idExtractor)
    private final Function<T, String> idExtractor;

    /**
     * Construtor.
     * @param idExtractor Uma função que recebe um objeto T e retorna sua String ID.
     */
    public RepositorioHash(Function<T, String> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void cadastrar(T item) {
        String id = idExtractor.apply(item);
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
