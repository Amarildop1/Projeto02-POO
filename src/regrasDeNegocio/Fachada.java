package regrasDeNegocio;


import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

import java.util.ArrayList;

import excecoes.ApagarEventoComIngressosException;
import excecoes.ApagarParticipanteComIngressosException;
import excecoes.CapacidadeMaximaExcedidaException;
import excecoes.ConvidadoSemEmpresaException;
import excecoes.DataNascimentoInvalidaException;
import excecoes.IngressoDuplicadoException;
import excecoes.ParticipanteDuplicadoException;


public class Fachada {
	private static Repositorio repositorio = new Repositorio();


	//CRIA��O DE EVENTO
    public static void criarEvento(String dataEvento, String descricaoEvento, int capacidadeEvento, double precoEvento) {
        Evento novoEvento = new Evento(dataEvento, descricaoEvento, capacidadeEvento, precoEvento);
        repositorio.adicionar(novoEvento);
    }


    //CRIA��O DE PARTICIPANTE
    public static void criarParticipante(String cpf, String dataDeNascimento) throws Exception {
        try {
            if (dataDeNascimento.isEmpty()) {
                throw new DataNascimentoInvalidaException("Data de nascimento n�o pode ser vazia");
            }

            Participante participanteExistente = repositorio.localizarParticipante(cpf);

            if (participanteExistente != null) {
                throw new ParticipanteDuplicadoException("Participante j� existente com o mesmo CPF e data de nascimento");
            }

            // Se n�o existir, cria o participante normalmente
            Participante participante = new Participante(cpf, dataDeNascimento);
            repositorio.adicionar(participante);
        } catch (DataNascimentoInvalidaException | ParticipanteDuplicadoException e) {
            // Propaga a exce��o para ser tratada no c�digo de teste
            throw e;
        }
    }


    //CRIA��O DE PARTICIPANTE CONVIDADO
    public static void criarConvidado(String cpf, String dataDeNascimento, String empresa) throws Exception {
        try {
            // Verifica se j� existe um participante com o mesmo CPF
            Participante participanteExistente = repositorio.localizarParticipante(cpf);

            if (participanteExistente != null) {
                throw new ParticipanteDuplicadoException("Participante j� existente com o mesmo CPF e data de nascimento");
            }

            if (empresa.isEmpty()) {	// Verifica se o convidado tem uma empresa
                throw new ConvidadoSemEmpresaException("Convidado deve ter uma empresa");
            }

            // Se n�o existir, cria o participante como convidado
            Participante participante = new Convidado(cpf, dataDeNascimento, empresa);
            repositorio.adicionar(participante);
        } catch (ConvidadoSemEmpresaException exc) {
        	throw exc; 	// Exce��o de convidado sem empresa - item 3
        }
    }


    //CRIA��O DE INGRESSO
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
        
        // Verificar se j� existe um ingresso com o mesmo c�digo
        if (repositorio.localizarIngresso(ingresso.getCodigoIngresso()) != null) {
            throw new IngressoDuplicadoException("Ingresso duplicado: j� existe um ingresso com o mesmo c�digo.");
        }
        
        if (evento.getIngressos().size() > evento.getCapacidadeEvento()) {
            throw new CapacidadeMaximaExcedidaException("Capacidade m�xima de ingressos atingida para o evento.");
        }
        if (evento.lotado()) {
        	throw new CapacidadeMaximaExcedidaException("Capacidade m�xima de ingressos atingida para o evento.");
        }
        
        repositorio.adicionar(ingresso);


        repositorio.salvarObjetos();   // Salva as altera��es no arquivo
    }



    //APAGANDO EVENTO
    public static void apagarEvento(int idEvento) {
        Evento eventoParaApagar = repositorio.localizarEvento(idEvento);
        
        if (eventoParaApagar != null) {
            repositorio.remover(eventoParaApagar);
        } else {
            throw new RuntimeException("Evento n�o encontrado para o ID: " + idEvento);
        }

        if (!eventoParaApagar.getIngressos().isEmpty()) {
            throw new ApagarEventoComIngressosException("N�o � poss�vel apagar um evento com ingressos associados.");
        }
    }



    //APAGANDO PARTICIPANTE
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
            
            if (ultimoIngressoUltrapassado(participanteParaApagar)) {	// Verificar se o �ltimo ingresso do participante est� ultrapassado
                
                for (Ingresso ingresso : participanteParaApagar.getIngressos()) {	// Remover todos os ingressos do participante do reposit�rio
                    repositorio.removerIngresso(ingresso);
                }

                participanteParaApagar.getIngressos().clear(); // Limpar a lista de ingressos do participante

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

            return true; // true se estiver ultrapassado
        }

        return true; // Se n�o tiver ingressos, considero que est� ultrapassado
    }


    
    //APAGANDO INGRESSO
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
    


} // Final da class Fachada


