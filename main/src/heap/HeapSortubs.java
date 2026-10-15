package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;

/**
 * Exercício 1 - Heap Sort na agenda de atendimentos da UBS.
 *
 * A agenda é um Max-heap (fila de prioridade) por idade: quem tem mais idade
 * é atendido primeiro. Em empate de idade, quem chegou antes é atendido antes.
 */
public final class HeapSortubs {

    private HeapSortubs() {
    }

    private static int filhoEsq(int i) {
        return 2 * i + 1;
    }

    private static int filhoDir(int i) {
        return 2 * i + 2;
    }

    private static int pai(int i) {
        return (i - 1) / 2;
    }

    /** Desce o elemento i até restaurar a propriedade de max-heap (heap de tamanho n). */
    public static void maxHeapify(List<Paciente> v, int n, int i) {
        int maior = i;
        int esq = filhoEsq(i);
        int dir = filhoDir(i);
        if (esq < n && v.get(esq).compareTo(v.get(maior)) > 0) {
            maior = esq;
        }
        if (dir < n && v.get(dir).compareTo(v.get(maior)) > 0) {
            maior = dir;
        }
        if (maior != i) {
            Collections.swap(v, i, maior);
            maxHeapify(v, n, maior);
        }
    }

    public static void buildMaxHeap(List<Paciente> v) {
        for (int i = v.size() / 2 - 1; i >= 0; i--) {
            maxHeapify(v, v.size(), i);
        }
    }

    /** Insere um paciente na agenda (sobe o elemento). */
    public static void inserir(List<Paciente> heap, Paciente paciente) {
        heap.add(paciente);
        int i = heap.size() - 1;
        while (i > 0 && heap.get(pai(i)).compareTo(heap.get(i)) < 0) {
            Collections.swap(heap, pai(i), i);
            i = pai(i);
        }
    }

    /** Heap Sort in-place: deixa a lista em ordem CRESCENTE de prioridade. */
    public static List<Paciente> heapSort(List<Paciente> v) {
        buildMaxHeap(v);
        for (int fim = v.size() - 1; fim > 0; fim--) {
            Collections.swap(v, 0, fim);
            maxHeapify(v, fim, 0);
        }
        return v;
    }

    /**
     * Clona o heap, aplica Heap Sort no clone e devolve a lista em ordem
     * DECRESCENTE de idade (o heap original não é alterado).
     */
    public static List<Paciente> agendaOrdenadaDecrescente(List<Paciente> heap) {
        List<Paciente> clone = new ArrayList<>(heap);
        heapSort(clone);
        Collections.reverse(clone);
        return clone;
    }

    public static void imprimirAgenda(List<Paciente> heap) {
        int pos = 1;
        for (Paciente p : agendaOrdenadaDecrescente(heap)) {
            System.out.printf("%2d. %s - %d anos%n", pos++, p.nome(), p.idade());
        }
    }

    /**
     * Quantas pessoas faltam (estão à frente) para o paciente {@code nome} ser
     * atendido. Retorna vazio se o paciente não estiver na agenda.
     */
    public static OptionalInt faltaQuantos(List<Paciente> heap, String nome) {
        List<Paciente> ordem = agendaOrdenadaDecrescente(heap);
        for (int pos = 0; pos < ordem.size(); pos++) {
            if (ordem.get(pos).nome().equals(nome)) {
                return OptionalInt.of(pos);
            }
        }
        return OptionalInt.empty();
    }
}