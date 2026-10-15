package teste;

import java.util.ArrayList;
import java.util.List;

import heap.HeapSortubs;
import heap.Paciente;

import static teste.Teste.*;

/** Testes do Exercício 1 (Heap Sort / agenda da UBS). */
final class TestesHeap {

    private TestesHeap() {
    }

    private static List<Paciente> agendaExemplo() {
        String[] nomes = {"Ana", "Bruno", "Carla", "Davi", "Eva", "Fabio"};
        int[] idades = {34, 71, 8, 52, 71, 45};
        List<Paciente> h = new ArrayList<>();
        for (int i = 0; i < nomes.length; i++) {
            HeapSortubs.inserir(h, new Paciente(nomes[i], idades[i], i));
        }
        return h;
    }

    private static boolean ehMaxHeap(List<Paciente> v) {
        for (int i = 1; i < v.size(); i++) {
            if (v.get((i - 1) / 2).compareTo(v.get(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    private static List<Paciente> pacientes(int... idades) {
        List<Paciente> v = new ArrayList<>();
        for (int idade : idades) {
            v.add(new Paciente(String.valueOf(idade), idade, idade));
        }
        return v;
    }

    private static List<Integer> idades(List<Paciente> v) {
        return v.stream().map(Paciente::idade).toList();
    }

    static void executar() {
        System.out.println("Exercício 1 - Heap");

        caso("inserir mantém max-heap", () -> {
            List<Paciente> h = agendaExemplo();
            verdadeiro(ehMaxHeap(h));
            igual(71, h.get(0).idade());
        });

        caso("build_max_heap", () -> {
            List<Paciente> v = pacientes(3, 9, 2, 7, 1, 8);
            HeapSortubs.buildMaxHeap(v);
            verdadeiro(ehMaxHeap(v));
        });

        caso("max_heapify", () -> {
            List<Paciente> v = new ArrayList<>(List.of(
                    new Paciente("a", 1, 0), new Paciente("b", 9, 1), new Paciente("c", 5, 2)));
            HeapSortubs.maxHeapify(v, 3, 0);
            igual(9, v.get(0).idade());
        });

        caso("heap sort crescente", () -> {
            List<Paciente> v = pacientes(5, 3, 9, 1, 7);
            HeapSortubs.heapSort(v);
            igual(List.of(1, 3, 5, 7, 9), idades(v));
        });

        caso("ordem decrescente e não altera o original", () -> {
            List<Paciente> h = agendaExemplo();
            List<Paciente> copia = new ArrayList<>(h);
            List<Paciente> ordem = HeapSortubs.agendaOrdenadaDecrescente(h);
            igual(List.of(71, 71, 52, 45, 34, 8), idades(ordem));
            igual(copia, h);
        });

        caso("desempate por chegada", () -> {
            List<Paciente> ordem = HeapSortubs.agendaOrdenadaDecrescente(agendaExemplo());
            igual(List.of("Bruno", "Eva"), List.of(ordem.get(0).nome(), ordem.get(1).nome()));
        });

        caso("falta_quantos", () -> {
            List<Paciente> h = agendaExemplo();
            igual(0, HeapSortubs.faltaQuantos(h, "Bruno").getAsInt());
            igual(2, HeapSortubs.faltaQuantos(h, "Davi").getAsInt());
            igual(5, HeapSortubs.faltaQuantos(h, "Carla").getAsInt());
        });

        caso("falta_quantos de paciente inexistente", () ->
                verdadeiro(HeapSortubs.faltaQuantos(agendaExemplo(), "Zé").isEmpty()));

        caso("heap vazio", () -> {
            igual(List.of(), HeapSortubs.agendaOrdenadaDecrescente(new ArrayList<>()));
            verdadeiro(HeapSortubs.faltaQuantos(new ArrayList<>(), "Ana").isEmpty());
        });
    }
}