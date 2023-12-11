package apresentacao;

import modelo.Evento;
import modelo.Participante;
import regrasDeNegocio.Fachada;
import modelo.Convidado;
import modelo.Ingresso;

import java.util.ArrayList;

public class PrincipalTesteConsole {

    public static void main(String[] args) {
        // Criação da Fachada
        Fachada fachada = new Fachada();

        // Teste dos métodos da Fachada
        testeFachada(fachada);

        // Exibição dos resultados
        exibirResultados(fachada);
    }

    private static void testeFachada(Fachada fachada) {
        try {
            // Criar Evento
            fachada.criarEvento("01/01/2023", "Evento Teste", 100, 50.0);

            // Criar Participante
            fachada.criarParticipante("12345678901", "01/01/1990");

            // Criar Convidado
            fachada.criarConvidado("98765432101", "01/01/1980", "Empresa Teste");

            // Criar Ingresso
            ArrayList<Evento> eventos = fachada.listarEventos();
            ArrayList<Participante> participantes = fachada.listarParticipantes();

            if (!eventos.isEmpty() && !participantes.isEmpty()) {
                Evento evento = eventos.get(0);
                Participante participante = participantes.get(0);
                fachada.criarIngresso("123456789", evento, participante);
            }

            // Apagar Evento (altere o ID conforme necessário)
            fachada.apagarEvento(1);

            // Apagar Participante (altere o CPF conforme necessário)
            fachada.apagarParticipante("12345678901");

            // Apagar Ingresso (altere o código conforme necessário)
            ArrayList<Ingresso> ingressos = fachada.listarIngressos();
            if (!ingressos.isEmpty()) {
                Ingresso ingresso = ingressos.get(0);
                fachada.apagarIngresso(ingresso.getCodigoIngresso());
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void exibirResultados(Fachada fachada) {
        // Exibir Eventos
        System.out.println("Eventos:");
        ArrayList<Evento> eventos = fachada.listarEventos();
        for (Evento evento : eventos) {
            System.out.println(evento);
        }

        // Exibir Participantes
        System.out.println("\nParticipantes e Convidados:");
        ArrayList<Participante> participantes = fachada.listarParticipantes();
        for (Participante participante : participantes) {
            System.out.println(participante);
        }

        // Exibir Ingressos
        System.out.println("\nIngressos:");
        ArrayList<Ingresso> ingressos = fachada.listarIngressos();
        for (Ingresso ingresso : ingressos) {
            System.out.println(ingresso);
        }
    }
}
