
import bucket.BucketSort;
import heap.HeapSortubs;
import heap.Paciente;
import radix.RadixSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Executa os três exercícios: Heap Sort (UBS), Radix Sort e Bucket Sort. */
public class Main {

    public static void main(String[] args) {
        exercicio1();
        exercicio2();
        exercicio3();
    }

    private static void titulo(String texto) {
        System.out.println("==================================================");
        System.out.println(texto);
        System.out.println("==================================================");
    }

    private static void exercicio1() {
        titulo("Exercício 1 - Heap Sort (agenda da UBS)");
        String[] nomes = {"Ana", "Bruno", "Carla", "Davi", "Eva", "Fabio"};
        int[] idades = {34, 71, 8, 52, 71, 45};

        List<Paciente> agenda = new ArrayList<>();
        for (int i = 0; i < nomes.length; i++) {
            HeapSortubs.inserir(agenda, new Paciente(nomes[i], idades[i], i));
        }
        System.out.println("Agenda ordenada (decrescente de idade):");
        HeapSortubs.imprimirAgenda(agenda);
        System.out.println("\nFaltam " + HeapSortubs.faltaQuantos(agenda, "Davi").orElse(-1)
                + " pessoas para o Davi.");
        System.out.println();
    }

    private static void exercicio2() {
        titulo("Exercício 2 - Radix Sort");
        int[] a = {170, 45, 75, 90};
        System.out.println("A = " + Arrays.toString(a));
        RadixSort.radixSort(a, true);
        System.out.println("\nResultado final: " + Arrays.toString(a));
        System.out.println();
    }

    private static void exercicio3() {
        titulo("Exercício 3 - Bucket Sort");
        double[][] casos = {
                {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.68, 0.35},
                {0.19, 0.11, 0.18, 0.12, 0.17, 0.13, 0.16, 0.14, 0.15, 0.10}
        };
        for (double[] a : casos) {
            System.out.println("\nA = " + Arrays.toString(a));
            BucketSort.bucketSort(a, true);
        }
    }
}