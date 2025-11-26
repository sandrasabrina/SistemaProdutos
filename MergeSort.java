package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Implementação do algoritmo Merge Sort
public class MergeSort<T> implements Ordenacao<T> {

    @Override
    public long ordenar(List<T> lista, Comparator<T> comparator) {
        long startTime = System.nanoTime();
        if (lista == null || lista.size() <= 1) {
            return 0; // Já está ordenada
        }

        // Merge Sort é tipicamente implementado usando recursão com cópias ou arrays auxiliares.
        // Aqui, usaremos a abordagem de cópias para simplificar a manipulação de List<T>.
        List<T> listaOrdenada = mergeSort(new ArrayList<>(lista), comparator);
        
        // Copia os elementos ordenados de volta para a lista original (in-place)
        lista.clear();
        lista.addAll(listaOrdenada);
        
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private List<T> mergeSort(List<T> lista, Comparator<T> comparator) {
        if (lista.size() <= 1) {
            return lista;
        }

        int meio = lista.size() / 2;
        // Divide a lista em duas sublistas
        List<T> esquerda = mergeSort(lista.subList(0, meio), comparator);
        List<T> direita = mergeSort(lista.subList(meio, lista.size()), comparator);

        // Combina as sublistas ordenadas
        return merge(esquerda, direita, comparator);
    }

    private List<T> merge(List<T> esquerda, List<T> direita, Comparator<T> comparator) {
        List<T> resultado = new ArrayList<>();
        int i = 0, j = 0;

        while (i < esquerda.size() && j < direita.size()) {
            // Compara usando o Comparator fornecido
            if (comparator.compare(esquerda.get(i), direita.get(j)) <= 0) {
                resultado.add(esquerda.get(i++));
            } else {
                resultado.add(direita.get(j++));
            }
        }

        // Adiciona elementos restantes
        while (i < esquerda.size()) {
            resultado.add(esquerda.get(i++));
        }

        while (j < direita.size()) {
            resultado.add(direita.get(j++));
        }

        return resultado;
    }
}
