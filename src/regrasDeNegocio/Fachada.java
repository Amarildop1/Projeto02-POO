package regrasDeNegocio;


import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

import java.util.ArrayList;

import excecoes.ConvidadoSemEmpresaException;
import excecoes.DataNascimentoInvalidaException;
import excecoes.ParticipanteDuplicadoException;


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

    
    
    
    public static void criarParticipante(String cpf, String dataDeNascimento) throws Exception {
        try {
            if (dataDeNascimento.isEmpty()) {
                throw new DataNascimentoInvalidaException("Data de nascimento não pode ser vazia");
            }

            // Verifica se já existe um participante com o mesmo CPF
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

    
    
    
    
    
    
    
   /* 
    public static void criarParticipante(String cpf, String dataDeNascimento) {
        //ArrayList<Ingresso> ingressosParticipante = new ArrayList<>();
        
        //Participante novoParticipante = new Participante(cpf, dataDeNascimento);
        //repositorio.adicionar(novoParticipante);
        
    	//ESSE CÓDIGO ACIMA FUNCIONA | ESSE ABAIXO É O TESTE PRA ADICIONAR EXCEÇÃO
    	
    	//Repositorio repositorio = new Repositorio();  ACREDITO QUE ISSO TAVA DEIXANDO O PARTICIPANTE NULL

        try {
            // Verifica se já existe um participante com o mesmo CPF
            Participante participanteExistente = repositorio.localizarParticipante(cpf);

            if (participanteExistente != null) {
                throw new ParticipanteDuplicadoException("Participante já existente com o mesmo CPF e data de nascimento");
            }

            // Se não existir, cria o participante normalmente
            Participante participante = new Participante(cpf, dataDeNascimento);
            repositorio.adicionar(participante);
        } catch (ParticipanteDuplicadoException e) {
            // Trata a exceção aqui, se necessário
            System.out.println("Erro ao criar participante: " + e.getMessage());
        }
    }
*/
    
    
    
    
    
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
        	throw exc; 	// Lançando a exceção de convidado sem empresa - item 3
        }
    }

    
    
    
    
    
    

    
    /*
    public static void criarConvidado(String cpf, String dataDeNascimento, String empresa) {
        try {
            // Verifica se já existe um participante com o mesmo CPF
            Participante participanteExistente = repositorio.localizarParticipante(cpf);

            if (participanteExistente != null) {
                throw new ParticipanteDuplicadoException("Participante já existente com o mesmo CPF e data de nascimento");
            }

            // Se não existir, cria o participante como convidado
            Participante participante = new Convidado(cpf, dataDeNascimento, empresa);
            repositorio.adicionar(participante);
        } catch (ParticipanteDuplicadoException e) {
            // Trata a exceção aqui, se necessário
            System.out.println("Erro ao criar convidado: " + e.getMessage());
        }
    }
*/    
    

    
    
/*    
    public static void criarConvidado(String cpf, String dataDeNascimento, String empresa) {
        ArrayList<Ingresso> ingressosConvidado = new ArrayList<>();
        Convidado novoConvidado = new Convidado(cpf, dataDeNascimento, empresa);
        novoConvidado.setEmpresa(empresa);
        repositorio.adicionar(novoConvidado);
    }
*/ 
    
    
    
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
            throw new IllegalArgumentException("Evento não encontrado com o ID fornecido.");
        }

        Participante participante = repositorio.localizarParticipante(cpf);
        
        // ***** TESTANDO *********
        //System.err.println("..... PARTICIPANTE: " + participante);
        //System.err.println("..... REPOSITÓRIO: " + repositorio);
        //System.err.println("..... getParticipantes(): " + repositorio.getParticipantes());


        if (participante == null) {
            throw new IllegalArgumentException("Participante não encontrado com o CPF fornecido.");
        }

        Ingresso ingresso = new Ingresso(telefone, evento, participante);

        repositorio.adicionar(ingresso);

        // Salva as alterações no arquivo
        repositorio.salvarObjetos();
    }

    public static void apagarEvento(int idEvento) {
        Evento eventoParaApagar = repositorio.localizarEvento(idEvento);
        if (eventoParaApagar != null) {
            repositorio.remover(eventoParaApagar);
        } else {
            throw new RuntimeException("Evento não encontrado para o ID: " + idEvento);
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
        //TESTANDO
        //System.err.println("participanteParaApagar: " + participanteParaApagar);

        // Verificar se o participante foi encontrado
        if (participanteParaApagar != null) {
            // Verificar se o último ingresso do participante está ultrapassado
            if (ultimoIngressoUltrapassado(participanteParaApagar)) {
                // Remover todos os ingressos do participante do repositório
                for (Ingresso ingresso : participanteParaApagar.getIngressos()) {
                    repositorio.removerIngresso(ingresso);
                }

                // Limpar a lista de ingressos do participante
                participanteParaApagar.getIngressos().clear();

                // Remover o participante do repositório de participantes
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

            return true; // Retornar true se estiver ultrapassado
        }

        return true; // Se não tiver ingressos, considera que está ultrapassado
    }


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
    

/*
    public static ArrayList<Ingresso> listarIngressos() {
        ArrayList<Ingresso> todosIngressos = new ArrayList<>();
        for (Participante participante : participantes) {
            todosIngressos.addAll(participante.getIngressos());
        }
        return todosIngressos;
    }
*/ 

    
    // Com o método static o atributo eventos dá problema ainda
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


