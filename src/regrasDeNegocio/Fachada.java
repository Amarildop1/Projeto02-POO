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
                throw new DataNascimentoInvalidaException("Data de nascimento n�o pode ser vazia");
            }

            // Verifica se j� existe um participante com o mesmo CPF
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

    
    
    
    
    
    
    
   /* 
    public static void criarParticipante(String cpf, String dataDeNascimento) {
        //ArrayList<Ingresso> ingressosParticipante = new ArrayList<>();
        
        //Participante novoParticipante = new Participante(cpf, dataDeNascimento);
        //repositorio.adicionar(novoParticipante);
        
    	//ESSE C�DIGO ACIMA FUNCIONA | ESSE ABAIXO � O TESTE PRA ADICIONAR EXCE��O
    	
    	//Repositorio repositorio = new Repositorio();  ACREDITO QUE ISSO TAVA DEIXANDO O PARTICIPANTE NULL

        try {
            // Verifica se j� existe um participante com o mesmo CPF
            Participante participanteExistente = repositorio.localizarParticipante(cpf);

            if (participanteExistente != null) {
                throw new ParticipanteDuplicadoException("Participante j� existente com o mesmo CPF e data de nascimento");
            }

            // Se n�o existir, cria o participante normalmente
            Participante participante = new Participante(cpf, dataDeNascimento);
            repositorio.adicionar(participante);
        } catch (ParticipanteDuplicadoException e) {
            // Trata a exce��o aqui, se necess�rio
            System.out.println("Erro ao criar participante: " + e.getMessage());
        }
    }
*/
    
    
    
    
    
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
        	throw exc; 	// Lan�ando a exce��o de convidado sem empresa - item 3
        }
    }

    
    
    
    
    
    

    
    /*
    public static void criarConvidado(String cpf, String dataDeNascimento, String empresa) {
        try {
            // Verifica se j� existe um participante com o mesmo CPF
            Participante participanteExistente = repositorio.localizarParticipante(cpf);

            if (participanteExistente != null) {
                throw new ParticipanteDuplicadoException("Participante j� existente com o mesmo CPF e data de nascimento");
            }

            // Se n�o existir, cria o participante como convidado
            Participante participante = new Convidado(cpf, dataDeNascimento, empresa);
            repositorio.adicionar(participante);
        } catch (ParticipanteDuplicadoException e) {
            // Trata a exce��o aqui, se necess�rio
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
            throw new IllegalArgumentException("Evento n�o encontrado com o ID fornecido.");
        }

        Participante participante = repositorio.localizarParticipante(cpf);
        
        // ***** TESTANDO *********
        //System.err.println("..... PARTICIPANTE: " + participante);
        //System.err.println("..... REPOSIT�RIO: " + repositorio);
        //System.err.println("..... getParticipantes(): " + repositorio.getParticipantes());


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
        //TESTANDO
        //System.err.println("participanteParaApagar: " + participanteParaApagar);

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

            return true; // Retornar true se estiver ultrapassado
        }

        return true; // Se n�o tiver ingressos, considera que est� ultrapassado
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


