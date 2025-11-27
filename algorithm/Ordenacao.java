package algorithm;

import java.util.Comparator;
import java.util.List;

/**
 * Contrato para algoritmos de ordenação que operam sobre List<T>.
 *
 * @param <T> tipo de elementos da lista
 */
public interface Ordenacao<T> {
    /**
     * Ordena a lista usando o comparator e retorna tempo em nanos.
     */
    long ordenar(List<T> lista, Comparator<T> comparator);
}
