package teste;

import bucket.BucketSort;

import java.util.Arrays;

import static teste.Teste.*;

/** Testes do Exercício 3 (Bucket Sort). */
final class TestesBucket {

    private static final double[] A1 = {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.68, 0.35};
    private static final double[] A2 = {0.19, 0.11, 0.18, 0.12, 0.17, 0.13, 0.16, 0.14, 0.15, 0.10};

    private TestesBucket() {
    }

    private static double[] ordenado(double[] a) {
        double[] c = a.clone();
        Arrays.sort(c);
        return c;
    }

    static void executar() {
        System.out.println("Exercício 3 - Bucket");

        caso("ordena os dois exemplos", () -> {
            for (double[] a : new double[][]{A1, A2}) {
                igual(ordenado(a), BucketSort.bucketSort(a.clone()).ordenado());
            }
        });

        caso("A2 é pior caso que A1 (mais comparações)", () -> {
            int c1 = BucketSort.bucketSort(A1.clone()).comparacoes();
            int c2 = BucketSort.bucketSort(A2.clone()).comparacoes();
            maior(c2, c1);
        });

        caso("não altera o vetor de entrada", () -> {
            double[] a = A1.clone();
            BucketSort.bucketSort(a);
            igual(A1, a);
        });

        caso("valor fora de [0,1) é rejeitado", () -> {
            try {
                BucketSort.bucketSort(new double[]{0.5, 1.2});
                throw new AssertionError("deveria lançar IllegalArgumentException");
            } catch (IllegalArgumentException esperado) {
                // ok
            }
        });
    }
}