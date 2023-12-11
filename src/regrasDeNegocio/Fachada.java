package regrasDeNegocio;


import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;

import java.util.ArrayList;

public class Fachada {

    private ArrayList<Evento> eventos;
    private ArrayList<Participante> participantes;

    
    public Fachada() {
        eventos = new ArrayList<>();
        participantes = new ArrayList<>();
    }


    public void criarEvento(String dataEvento, String descricaoEvento, int capacidadeEvento, double precoEvento) {
        Evento novoEvento = new Evento(dataEvento, descricaoEvento, capacidadeEvento, precoEvento);
        eventos.add(novoEvento);
    }

    public void criarParticipante(String cpf, String dataDeNascimento) {
        ArrayList<Ingresso> ingressosParticipante = new ArrayList<>();
        Participante novoParticipante = new Participante(cpf, dataDeNascimento, ingressosParticipante);
        participantes.add(novoParticipante);
    }

    public void criarConvidado(String cpf, String dataDeNascimento, String empresa) {
        ArrayList<Ingresso> ingressosConvidado = new ArrayList<>();
        Convidado novoConvidado = new Convidado(cpf, dataDeNascimento, ingressosConvidado, empresa);
        novoConvidado.setEmpresa(empresa);
        participantes.add(novoConvidado);
    }

    public void criarIngresso(String telefone, Evento evento, Participante participante) {
        Ingresso novoIngresso = new Ingresso(telefone, evento, participante);
        participante.getIngressos().add(novoIngresso);
        //evento.venderIngresso();
    }
    

    public void apagarEvento(int idEvento) {
        Evento eventoParaRemover = null;
        for (Evento evento : eventos) {
            if (evento.getIdEvento() == idEvento) {
                eventoParaRemover = evento;
                break;
            }
        }
        if (eventoParaRemover != null) {
            eventos.remove(eventoParaRemover);
        } else {
            throw new RuntimeException("Evento não encontrado para o ID: " + idEvento);
        }
    }


    public void apagarParticipante(String cpf) {
        Participante participanteParaRemover = null;
        for (Participante participante : participantes) {
            if (participante.getCPF().equals(cpf)) {
                participanteParaRemover = participante;
                break;
            }
        }
        if (participanteParaRemover != null) {
            participantes.remove(participanteParaRemover);
        } else {
            throw new RuntimeException("Participante não encontrado para o CPF: " + cpf);
        }
    }


    public void apagarIngresso(String codigoIngresso) {
        Ingresso ingressoParaRemover = null;
        for (Participante participante : participantes) {
            for (Ingresso ingresso : participante.getIngressos()) {
                if (ingresso.getCodigoIngresso().equals(codigoIngresso)) {
                    ingressoParaRemover = ingresso;
                    break;
                }
            }
            if (ingressoParaRemover != null) {
                participante.getIngressos().remove(ingressoParaRemover);
                break;
            }
        }
        if (ingressoParaRemover == null) {
            throw new RuntimeException("Ingresso não encontrado para o código: " + codigoIngresso);
        }
    }

    public ArrayList<Evento> listarEventos() {
        return eventos;
    }

    public ArrayList<Participante> listarParticipantes() {
        return participantes;
    }

    public ArrayList<Ingresso> listarIngressos() {
        ArrayList<Ingresso> todosIngressos = new ArrayList<>();
        for (Participante participante : participantes) {
            todosIngressos.addAll(participante.getIngressos());
        }
        return todosIngressos;
    }
    

    
    
 /*   public static void apagarEvento(int idEvento) {

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
*/


} // Final da class Fachada


