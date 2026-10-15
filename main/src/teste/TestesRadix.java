package teste;

import radix.RadixSort;

import static teste.Teste.*;


/** Testes do Exercício 2 (Radix Sort). */
final class TestesRadix {

    private TestesRadix() {
    }

    static void executar() {
        System.out.println("Exercício 2 - Radix");

        caso("exemplo", () ->
                igual(new int[]{45, 75, 90, 170}, RadixSort.radixSort(new int[]{170, 45, 75, 90})));

        caso("um dígito", () -> {
            int[] a = {5, 3, 9, 1};
            RadixSort.countingSortPorDigito(a, 1);
            igual(new int[]{1, 3, 5, 9}, a);
        });

        caso("repetidos", () ->
                igual(new int[]{3, 11, 22, 22}, RadixSort.radixSort(new int[]{22, 11, 22, 3})));

        caso("vetor vazio", () ->
                igual(new int[]{}, RadixSort.radixSort(new int[]{})));
    }
}