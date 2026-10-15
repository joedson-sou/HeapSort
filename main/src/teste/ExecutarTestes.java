package teste;

/** Roda todos os testes e mostra o resumo. */
public class ExecutarTestes {

    public static void main(String[] args) {
        TestesHeap.executar();
        TestesRadix.executar();
        TestesBucket.executar();

        System.out.println();
        System.out.println("Resumo: " + Teste.ok + " passaram, " + Teste.falhas + " falharam.");
        if (Teste.falhas > 0) {
            System.exit(1);
        }
    }
}