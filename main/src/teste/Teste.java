package teste;

import java.util.Objects;

/** Mini-framework de testes (sem dependências externas). */
final class Teste {

    static int ok = 0;
    static int falhas = 0;

    private Teste() {
    }

    static void caso(String nome, Runnable corpo) {
        try {
            corpo.run();
            ok++;
            System.out.println("  [OK]    " + nome);
        } catch (AssertionError | RuntimeException e) {
            falhas++;
            System.out.println("  [FALHA] " + nome + " -> " + e.getMessage());
        }
    }

    static void verdadeiro(boolean condicao) {
        if (!condicao) {
            throw new AssertionError("esperado verdadeiro");
        }
    }

    static void igual(Object esperado, Object atual) {
        if (!Objects.deepEquals(esperado, atual)) {
            throw new AssertionError("esperado " + mostrar(esperado) + " mas veio " + mostrar(atual));
        }
    }

    static void maior(int a, int b) {
        if (!(a > b)) {
            throw new AssertionError(a + " deveria ser maior que " + b);
        }
    }

    private static String mostrar(Object o) {
        if (o instanceof int[] v) return java.util.Arrays.toString(v);
        if (o instanceof double[] v) return java.util.Arrays.toString(v);
        return String.valueOf(o);
    }
}