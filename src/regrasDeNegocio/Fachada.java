package regrasDeNegocio;


import modelo.Evento;
import modelo.Ingresso;
import java.util.ArrayList;

public class Fachada {

    private static ArrayList<Evento> eventos = new ArrayList<>();

    // (outros métodos)

    public static void apagarEvento(int idEvento) {
        Evento eventoParaRemover = null;

        // Encontrar o evento pelo ID
        for (Evento evento : eventos) {
            if (evento.getIdEvento() == idEvento) {
                eventoParaRemover = evento;
                break;
            }
        }

        // Verificar se o evento foi encontrado
        if (eventoParaRemover != null) {
            // Verificar se o evento não possui ingressos
            if (eventoParaRemover.getIngressos().isEmpty()) {
                // Remover o evento
                eventos.remove(eventoParaRemover);
            } else {
                throw new IllegalStateException("O evento não pode ser apagado pois possui ingressos associados.");
            }
        } else {
            throw new IllegalArgumentException("Evento não encontrado.");
        }
    }

    // (outros métodos)

} // Final da class Fachada


