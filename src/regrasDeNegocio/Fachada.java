package regrasDeNegocio;


import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

import java.util.ArrayList;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();

    //private static ArrayList<Evento> eventos;
    //private static ArrayList<Participante> participantes = new ArrayList<>();

/*   
    public Fachada() {
        eventos = new ArrayList<>();
        participantes = new ArrayList<>();
    }
*/

    public static void criarEvento(String dataEvento, String descricaoEvento, int capacidadeEvento, double precoEvento) {
        Evento novoEvento = new Evento(dataEvento, descricaoEvento, capacidadeEvento, precoEvento);
        repositorio.adicionar(novoEvento);
    }

    public static void criarParticipante(String cpf, String dataDeNascimento) {
        //ArrayList<Ingresso> ingressosParticipante = new ArrayList<>();
        Participante novoParticipante = new Participante(cpf, dataDeNascimento);
        repositorio.adicionar(novoParticipante);
    }

    public static void criarConvidado(String cpf, String dataDeNascimento, String empresa) {
        ArrayList<Ingresso> ingressosConvidado = new ArrayList<>();
        Convidado novoConvidado = new Convidado(cpf, dataDeNascimento, empresa);
        novoConvidado.setEmpresa(empresa);
        repositorio.adicionar(novoConvidado);
    }
/*
    public static void criarIngresso(int id, String cpf, String telefone) {
        Ingresso novoIngresso = new Ingresso(id, cpf, telefone);
        participante.getIngressos().add(novoIngresso);
        //evento.venderIngresso();
    }
*/    
    public static void criarIngresso(int id, String cpf, String telefone) {

        Evento evento = repositorio.localizarEvento(id);

        if (evento == null) {
            throw new IllegalArgumentException("Evento n�o encontrado com o ID fornecido.");
        }

        Participante participante = repositorio.localizarParticipante(cpf);

        if (participante == null) {
            throw new IllegalArgumentException("Participante n�o encontrado com o CPF fornecido.");
        }

        Ingresso ingresso = new Ingresso(telefone, evento, participante);

        repositorio.adicionar(ingresso);

        // Salva as altera��es no arquivo
        repositorio.salvarObjetos();
    }

    public static void apagarEvento(int idEvento) {
        Evento eventoParaApagar = repositorio.localizarEvento(idEvento);
        if (eventoParaApagar != null) {
            repositorio.remover(eventoParaApagar);
        } else {
            throw new RuntimeException("Evento n�o encontrado para o ID: " + idEvento);
        }
    }



    public static void apagarParticipante(String cpf) {

        Participante participanteParaApagar = null;

        for (Participante participante : repositorio.getParticipantes()) {
            if (participante.getCPF().equals(cpf)) {
                participanteParaApagar = participante;
                break;
            }
        }

        // Verificar se o participante foi encontrado
        if (participanteParaApagar != null) {
            // Verificar se o �ltimo ingresso do participante est� ultrapassado
            if (ultimoIngressoUltrapassado(participanteParaApagar)) {
                // Remover todos os ingressos do participante do reposit�rio
                for (Ingresso ingresso : participanteParaApagar.getIngressos()) {
                    repositorio.removerIngresso(ingresso);
                }

                // Limpar a lista de ingressos do participante
                participanteParaApagar.getIngressos().clear();

                // Remover o participante do reposit�rio de participantes
                repositorio.removerParticipante(participanteParaApagar);
            } else {
                throw new IllegalStateException("O participante n�o pode ser apagado pois o �ltimo ingresso n�o est� ultrapassado.");
            }
        } else {
            throw new IllegalArgumentException("Participante n�o encontrado.");
        }
    } // Final m�todo apagarParticipante
    
    
    private static boolean ultimoIngressoUltrapassado(Participante participante) {
        // Verificar se o participante possui ingressos
        if (!participante.getIngressos().isEmpty()) {
            //Ingresso ultimoIngresso = participante.getIngressos().get(participante.getIngressos().size() - 1);

            // verificar se o ingresso est� ultrapassado

            // Retornar true se estiver ultrapassado
            return true;
        }

        // Se n�o tiver ingressos, considera que est� ultrapassado
        return true;
    }


    public static void apagarIngresso(String codigoIngresso) {
        Ingresso ingressoParaRemover = repositorio.localizarIngresso(codigoIngresso);

        if (ingressoParaRemover != null) {
            // Remover o ingresso do participante e do reposit�rio
            Participante participante = ingressoParaRemover.getParticipante();
            participante.removerIngresso(ingressoParaRemover);
            repositorio.removerIngresso(ingressoParaRemover);
        } else {
            throw new RuntimeException("Ingresso n�o encontrado para o c�digo: " + codigoIngresso);
        }
    }


    public static ArrayList<Evento> listarEventos() {
        return repositorio.getEventos();
    }

    public static ArrayList<Participante> listarParticipantes() {
        return repositorio.getParticipantes() ;
    }
    
    public static ArrayList<Ingresso> listarIngressos() {
        return repositorio.getIngressos();
    }
    

/*
    public static ArrayList<Ingresso> listarIngressos() {
        ArrayList<Ingresso> todosIngressos = new ArrayList<>();
        for (Participante participante : participantes) {
            todosIngressos.addAll(participante.getIngressos());
        }
        return todosIngressos;
    }
*/ 

    
    // Com o m�todo static o atributo eventos d� problema ainda
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
            // Verificar se o evento n�o possui ingressos
            if (eventoParaRemover.getIngressos().isEmpty()) {
                // Remover o evento
                eventos.remove(eventoParaRemover);
            } else {
                throw new IllegalStateException("O evento n�o pode ser apagado pois possui ingressos associados.");
            }
        } else {
            throw new IllegalArgumentException("Evento n�o encontrado.");
        }
    }
*/


} // Final da class Fachada


