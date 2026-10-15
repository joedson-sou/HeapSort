package heap;

/**
 * heap.Paciente da agenda da UBS.
 *
 * Prioridade: maior idade primeiro; em empate de idade, quem chegou antes
 * (menor valor de {@code chegada}) tem prioridade.
 * Por isso {@code compareTo} devolve "maior" para o paciente mais prioritário.
 */

public record Paciente(String nome, int idade, int chegada) implements Comparable<Paciente> {

    @Override
    public int compareTo(Paciente outro) {
        int porIdade = Integer.compare(this.idade, outro.idade);
        if (porIdade != 0) {
            return porIdade;
        }
        // menor "chegada" => maior prioridade
        return Integer.compare(outro.chegada, this.chegada);
    }
}