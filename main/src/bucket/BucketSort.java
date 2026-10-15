package bucket;

import java.util.ArrayList;
import java.util.List;

/** Exercício 3 - Bucket Sort com teste de mesa e contagem de comparações. */
public final class BucketSort {

    /** Resultado do Bucket Sort: vetor ordenado + total de comparações do insertion sort. */
    public record Resultado(double[] ordenado, int comparacoes) {
    }

    private BucketSort() {
    }

    /** Insertion sort em um balde; devolve o número de comparações feitas. */
    public static int insertionSort(List<Double> b) {
        int comparacoes = 0;
        for (int j = 1; j < b.size(); j++) {
            double chave = b.get(j);
            int i = j - 1;
            while (i >= 0) {
                comparacoes++;
                if (b.get(i) > chave) {
                    b.set(i + 1, b.get(i));
                    i--;
                } else {
                    break;
                }
            }
            b.set(i + 1, chave);
        }
        return comparacoes;
    }

    /** Ordena valores em [0, 1). O vetor de entrada não é alterado. */
    public static Resultado bucketSort(double[] a, boolean verbose) {
        int n = a.length;
        List<List<Double>> baldes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            baldes.add(new ArrayList<>());
        }

        for (double x : a) {
            if (x < 0 || x >= 1) {
                throw new IllegalArgumentException("Valor fora do intervalo [0, 1): " + x);
            }
            baldes.get((int) (n * x)).add(x);
        }
        if (verbose) {
            System.out.println("Baldes após distribuição:");
            for (int i = 0; i < n; i++) {
                System.out.println("  B[" + i + "] = " + baldes.get(i));
            }
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            List<Double> b = baldes.get(i);
            int c = insertionSort(b);
            total += c;
            if (verbose && b.size() > 1) {
                System.out.println("  insertion sort em B[" + i + "] -> " + b + "  (" + c + " comparações)");
            }
        }

        double[] resultado = new double[n];
        int k = 0;
        for (List<Double> b : baldes) {
            for (double x : b) {
                resultado[k++] = x;
            }
        }
        if (verbose) {
            System.out.println("Resultado: " + java.util.Arrays.toString(resultado));
            System.out.println("Comparações totais (insertion sort): " + total
                    + "  | n = " + n + ", n² = " + (n * n));
        }
        return new Resultado(resultado, total);
    }

    public static Resultado bucketSort(double[] a) {
        return bucketSort(a, false);
    }
}