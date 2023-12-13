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


	//CRIAÇÃO DE EVENTO
    public static void criarEvento(String dataEvento, String descricaoEvento, int capacidadeEvento, double precoEvento) {
        Evento novoEvento = new Evento(dataEvento, descricaoEvento, capacidadeEvento, precoEvento);
        repositorio.adicionar(novoEvento);
    }


    //CRIAÇÃO DE PARTICIPANTE
    public static void criarParticipante(String cpf, String dataDeNascimento) throws Exception {
        try {
            if (dataDeNascimento.isEmpty()) {
                throw new DataNascimentoInvalidaException("Data de nascimento não pode ser vazia");
            }

            Participante participanteExistente = repositorio.localizarParticipante(cpf);

            if (participanteExistente != null) {
                throw new ParticipanteDuplicadoException("Participante já existente com o mesmo CPF e data de nascimento");
            }

            // Se não existir, cria o participante normalmente
            Participante participante = new Participante(cpf, dataDeNascimento);
            repositorio.adicionar(participante);
        } catch (DataNascimentoInvalidaException | ParticipanteDuplicadoException e) {
            // Propaga a exceção para ser tratada no código de teste
            throw e;
        }
    }


    //CRIAÇÃO DE PARTICIPANTE CONVIDADO
    public static void criarConvidado(String cpf, String dataDeNascimento, String empresa) throws Exception {
        try {
            // Verifica se já existe um participante com o mesmo CPF
            Participante participanteExistente = repositorio.localizarParticipante(cpf);

            if (participanteExistente != null) {
                throw new ParticipanteDuplicadoException("Participante já existente com o mesmo CPF e data de nascimento");
            }

            if (empresa.isEmpty()) {	// Verifica se o convidado tem uma empresa
                throw new ConvidadoSemEmpresaException("Convidado deve ter uma empresa");
            }

            // Se não existir, cria o participante como convidado
            Participante participante = new Convidado(cpf, dataDeNascimento, empresa);
            repositorio.adicionar(participante);
        } catch (ConvidadoSemEmpresaException exc) {
        	throw exc; 	// Exceção de convidado sem empresa - item 3
        }
    }


    //CRIAÇÃO DE INGRESSO
    public static void criarIngresso(int id, String cpf, String telefone) {

        Evento evento = repositorio.localizarEvento(id);

        if (evento == null) {
            throw new IllegalArgumentException("Evento não encontrado com o ID fornecido.");
        }

        Participante participante = repositorio.localizarParticipante(cpf);

        if (participante == null) {
            throw new IllegalArgumentException("Participante não encontrado com o CPF fornecido.");
        }

        Ingresso ingresso = new Ingresso(telefone, evento, participante);
        
        // Verificar se já existe um ingresso com o mesmo código
        if (repositorio.localizarIngresso(ingresso.getCodigoIngresso()) != null) {
            throw new IngressoDuplicadoException("Ingresso duplicado: já existe um ingresso com o mesmo código.");
        }
        
        if (evento.getIngressos().size() > evento.getCapacidadeEvento()) {
            throw new CapacidadeMaximaExcedidaException("Capacidade máxima de ingressos atingida para o evento.");
        }
        if (evento.lotado()) {
        	throw new CapacidadeMaximaExcedidaException("Capacidade máxima de ingressos atingida para o evento.");
        }
        
        repositorio.adicionar(ingresso);


        repositorio.salvarObjetos();   // Salva as alterações no arquivo
    }



    //APAGANDO EVENTO
    public static void apagarEvento(int idEvento) {
        Evento eventoParaApagar = repositorio.localizarEvento(idEvento);
        
        if (eventoParaApagar != null) {
            repositorio.remover(eventoParaApagar);
        } else {
            throw new RuntimeException("Evento não encontrado para o ID: " + idEvento);
        }

        if (!eventoParaApagar.getIngressos().isEmpty()) {
            throw new ApagarEventoComIngressosException("Não é possível apagar um evento com ingressos associados.");
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
            
            if (ultimoIngressoUltrapassado(participanteParaApagar)) {	// Verificar se o último ingresso do participante está ultrapassado
                
                for (Ingresso ingresso : participanteParaApagar.getIngressos()) {	// Remover todos os ingressos do participante do repositório
                    repositorio.removerIngresso(ingresso);
                }

                participanteParaApagar.getIngressos().clear(); // Limpar a lista de ingressos do participante

                repositorio.removerParticipante(participanteParaApagar);
            } else {
                throw new IllegalStateException("O participante não pode ser apagado pois o último ingresso não está ultrapassado.");
            }
        } else {
            throw new IllegalArgumentException("Participante não encontrado.");
        }

    } // Final método apagarParticipante
    


    private static boolean ultimoIngressoUltrapassado(Participante participante) {
        // Verificar se o participante possui ingressos
        if (!participante.getIngressos().isEmpty()) {
            //Ingresso ultimoIngresso = participante.getIngressos().get(participante.getIngressos().size() - 1);

            // verificar se o ingresso está ultrapassado

            return true; // true se estiver ultrapassado
        }

        return true; // Se não tiver ingressos, considero que está ultrapassado
    }


    
    //APAGANDO INGRESSO
    public static void apagarIngresso(String codigoIngresso) {
        Ingresso ingressoParaRemover = repositorio.localizarIngresso(codigoIngresso);

        if (ingressoParaRemover != null) {
            // Remover o ingresso do participante e do repositório
            Participante participante = ingressoParaRemover.getParticipante();
            participante.removerIngresso(ingressoParaRemover);
            repositorio.removerIngresso(ingressoParaRemover);
        } else {
            throw new RuntimeException("Ingresso não encontrado para o código: " + codigoIngresso);
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


