package radix;

import java.util.Arrays;

/** Exercício 2 - Radix Sort com Counting Sort por dígito (com teste de mesa). */
public final class RadixSort {

    private RadixSort() {
    }

    /**
     * <pre>
     * countingSortPorDigito(A, exp):
     *     n <- tamanho(A); B <- vetor de n posições; C <- vetor [0..9] zerado
     *     para i de 0 até n-1:   C[(A[i] / exp) mod 10]++
     *     para d de 1 até 9:     C[d] <- C[d] + C[d-1]        (acumulado)
     *     para i de n-1 até 0:   (de trás p/ frente = estável)
     *         d <- (A[i] / exp) mod 10
     *         C[d]--;  B[C[d]] <- A[i]
     *     A <- B
     * </pre>
     */
    public static void countingSortPorDigito(int[] a, int exp, boolean verbose) {
        int n = a.length;
        int[] b = new int[n];
        int[] c = new int[10];

        for (int x : a) {
            c[(x / exp) % 10]++;
        }
        if (verbose) {
            System.out.println("  contagem      C = " + Arrays.toString(c));
        }

        for (int d = 1; d < 10; d++) {
            c[d] += c[d - 1];
        }
        if (verbose) {
            System.out.println("  acumulado     C = " + Arrays.toString(c));
        }

        for (int i = n - 1; i >= 0; i--) {
            int d = (a[i] / exp) % 10;
            c[d]--;
            b[c[d]] = a[i];
            if (verbose) {
                System.out.printf("  A[%d]=%3d dígito=%d -> B[%d]%n", i, a[i], d, c[d]);
            }
        }
        System.arraycopy(b, 0, a, 0, n);
    }

    public static void countingSortPorDigito(int[] a, int exp) {
        countingSortPorDigito(a, exp, false);
    }

    /** Ordena {@code a} in-place (inteiros não negativos) e devolve o próprio vetor. */
    public static int[] radixSort(int[] a, boolean verbose) {
        if (a.length == 0) {
            return a;
        }
        int maior = Arrays.stream(a).max().getAsInt();
        for (int exp = 1; maior / exp > 0; exp *= 10) {
            if (verbose) {
                System.out.println("\nexp = " + exp + "  (entrada: " + Arrays.toString(a) + ")");
            }
            countingSortPorDigito(a, exp, verbose);
            if (verbose) {
                System.out.println("  saída: " + Arrays.toString(a));
            }
        }
        return a;
    }

    public static int[] radixSort(int[] a) {
        return radixSort(a, false);
    }
}