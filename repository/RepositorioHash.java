package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import model.InfoProd;

/**
 * Implementação de um repositório genérico utilizando HashMap para armazenamento.
 *
 * @param <T> Tipo da entidade a ser armazenada
 */
@InfoProd(versao = "3.0", categoria = "Persistencia")
public class RepositorioHash<T> implements Repositorio<T> {

    // Mapa para armazenamento dos itens, indexados por ID
    private final Map<String, T> armazenamento = new HashMap<>();

    // Função que extrai o ID de uma entidade do tipo T
    private final Function<T, String> extratorId;

    /**
     * Cria um novo repositório hash.
     * @param extratorId função que extrai o identificador único de cada entidade
     */
    public RepositorioHash(Function<T, String> extratorId) {
        this.extratorId = Objects.requireNonNull(extratorId, "O extrator de ID não pode ser nulo.");
    }

    /**
     * Cadastra um novo item no repositório.
     * @param item entidade a ser cadastrada
     */
    @Override
    public void cadastrar(T item) {
        Objects.requireNonNull(item, "O item não pode ser nulo.");
        String id = extratorId.apply(item);
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("O ID extraído não pode ser nulo ou vazio.");
        }
        armazenamento.put(id, item);
    }

    /**
     * Busca um item pelo ID.
     * @param id identificador do item
     * @return item correspondente ou null se não encontrado
     */
    @Override
    public T buscar(String id) {
        return armazenamento.get(id);
    }

    /**
     * Remove um item pelo ID.
     * @param id identificador do item a ser removido
     */
    @Override
    public void remover(String id) {
        armazenamento.remove(id);
    }

    /**
     * Lista todos os itens cadastrados.
     * @return lista de todas as entidades armazenadas
     */
    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(armazenamento.values());
    }
}
