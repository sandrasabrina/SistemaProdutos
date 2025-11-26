package algorithm;

import java.util.Comparator;
import java.util.List;

// Interface genérica para algoritmos de ordenação
public interface Ordenacao<T> {
    /**
     * Ordena a lista usando o Comparator fornecido.
     * @param lista A lista a ser ordenada.
     * @param comparator O critério de comparação.
     * @return O tempo de execução em nanosegundos.
     */
    long ordenar(List<T> lista, Comparator<T> comparator);
}
