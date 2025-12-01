package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implementação de Merge Sort que opera sobre List<T> usando Comparator.
 *
 * Retorna o tempo de execução em nanos (para profiling).
 */
public class MergeSort<T> implements Ordenacao<T> {

    @Override
    public long ordenar(List<T> lista, Comparator<T> comparator) {
        if (lista == null || comparator == null) {
            throw new IllegalArgumentException("lista e comparator não podem ser nulos");
        }
        long start = System.nanoTime();
        if (lista.size() > 1) {
            List<T> sorted = mergeSort(new ArrayList<>(lista), comparator);
            lista.clear();
            lista.addAll(sorted);
        }
        long end = System.nanoTime();
        return end - start;
    }

    private List<T> mergeSort(List<T> lista, Comparator<T> comparator) {
        if (lista.size() <= 1) return lista;

        int mid = lista.size() / 2;
        // Cria cópias e divide a lista em duas sublistas para evitar views ligadas à lista original
        List<T> left = mergeSort(new ArrayList<>(lista.subList(0, mid)), comparator);
        List<T> right = mergeSort(new ArrayList<>(lista.subList(mid, lista.size())), comparator);
        
        // Combina as sublistas ordenadas
        return merge(left, right, comparator);
    }

    private List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
        List<T> out = new ArrayList<>(left.size() + right.size());
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                out.add(left.get(i++));
            } else {
                out.add(right.get(j++));
            }
        }
        while (i < left.size()) out.add(left.get(i++));
        while (j < right.size()) out.add(right.get(j++));
        return out;
    }
}
