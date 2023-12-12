package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;


public class Repositorio {
	private ArrayList<Evento> eventos = new ArrayList<>();
	private ArrayList<Participante> participantes = new ArrayList<>();
	private ArrayList<Ingresso> ingressos = new ArrayList<>();;


	public ArrayList<Evento> getEventos() {
		return eventos;
	}
    public ArrayList<Participante> getParticipantes() {
        return this.participantes;
    }
	public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }


	public Repositorio() {
		
		carregarObjetos();
	}
	
	public void carregarObjetos()  	{
		 //eventos.clear();
		 //participantes.clear();
		 //ingressos.clear();
		// carregar para o repositorio os objetos salvos nos arquivos csv
		try {
			//caso os arquivos nao existam, serao criados vazios
			File f1 = new File( new File(".\\eventos.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\participantes.csv").getCanonicalPath() ) ; 
			File f3 = new File( new File(".\\ingressos.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() || !f3.exists()) {
				System.out.println("criando arquivo .csv vazio"); //TAVA COMENTADA NO REPOSITORIO DO PROF
				FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
				FileWriter arquivo3 = new FileWriter(f3); arquivo3.close();
				return;
			}
		}
		catch(Exception ex)		{
			throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
		}

		String linha;	
		String[] partes;	
		Evento evento;
		Participante participante;

		try	{
			String data, descricao, id, capacidade, preco ;
			File f = new File( new File(".\\eventos.csv").getCanonicalPath() )  ;
			Scanner arquivo1 = new Scanner(f);	 //  pasta do projeto
			while(arquivo1.hasNextLine()) 	{
				linha = arquivo1.nextLine().trim();		
				partes = linha.split(";");	
				//System.out.println(Arrays.toString(partes));
				id = partes[0];
				data = partes[1];
				descricao = partes[2];
				capacidade = partes[3];
				preco = partes[4];
				//ALTEREI A ATRIBUIÇÃO ABAIXO (NO ARQ ORIGINAL TEM MAIS PARÂMETROS)
				evento = new Evento(data, descricao,
						Integer.parseInt(capacidade), Double.parseDouble(preco));
				this.adicionar(evento);
			} 
			arquivo1.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de eventos:"+ex.getMessage());
		}

		try	{
			String cpf, nascimento, empresa, listaId;
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			Scanner arquivo2 = new Scanner(f);	 //  pasta do projeto
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				cpf = partes[0];
				nascimento = partes[1];
				if(partes.length==2) {
					participante = new Participante(cpf,nascimento);
					this.adicionar(participante);
				}
				else {
					empresa = partes[2];
					participante = new Convidado(cpf,nascimento,empresa);
					this.adicionar(participante);
				}

			}
			arquivo2.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de participantes:"+ex.getMessage());
		}
		
		try	{
			String codigo, telefone,cpf;
			int id;
			Ingresso ingresso;
			//Evento evento;    // Lá em cima já foi declarada
			//Participante participante;    // Lá em cima já foi declarada
			File f = new File( new File(".\\ingressos.csv").getCanonicalPath())  ;
			Scanner arquivo3 = new Scanner(f);	 //  pasta do projeto
			while(arquivo3.hasNextLine()) 	{
				linha = arquivo3.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				codigo = partes[0];		//contem id e cpf
				telefone = partes[1];
				id = Integer.parseInt(codigo.split("-")[0]);
				cpf = codigo.split("-")[1];
				evento = this.localizarEvento(id);
				participante = this.localizarParticipante(cpf);
				
				ingresso = new Ingresso(telefone,evento,participante);
				evento.adicionar(ingresso);
				participante.adicionar(ingresso);
				this.adicionar(ingresso);
			}
			arquivo3.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de participantes:"+ex.getMessage());
		}
	}

	//--------------------------------------------------------------------
	public void	salvarObjetos()  {
		//gravar nos arquivos csv os objetos que estão no repositório
		try	{
			File f = new File( new File(".\\eventos.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f); 
			for(Evento e : eventos) 	{
				arquivo1.write(e.getIdEvento()+";"+e.getDataEvento()+";"+e.getDescricaoEvento()+";"+e.getCapacidadeEvento()+";"+e.getPrecoEvento()+"\n");	
			} 
			arquivo1.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na criação do arquivo  eventos "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ; 
			for(Participante p : participantes) {
				if(p instanceof Convidado c )
					arquivo2.write(p.getCPF() +";" + p.getDataDeNascimento() +";" + c.getEmpresa()+"\n");	
				else
					arquivo2.write(p.getCPF() +";" + p.getDataDeNascimento() +"\n");	

			} 
			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na criação do arquivo  participantes "+e.getMessage());
		}
		try	{
			File f = new File( new File(".\\ingressos.csv").getCanonicalPath())  ;
			FileWriter arquivo3 = new FileWriter(f) ; 
			for(Ingresso i : this.getIngressos()) {
					arquivo3.write(i.getCodigoIngresso() +";" + i.getTelefone()+"\n");	

			} 
			arquivo3.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na criação do arquivo  participantes "+e.getMessage());
		}

	}


	/*
	 * ************* ADICIONANDO MÉTODOS ****************
	 * */
    public void adicionar(Evento evento) {
        eventos.add(evento);
    }

    public void adicionar(Participante participante) {
        participantes.add(participante);
    }

    public void adicionar(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public Evento localizarEvento(int id) {
        for (Evento evento : eventos) {
            if (evento.getIdEvento() == id) {
                return evento;
            }
        }
        return null; // null se o evento não for encontrado
    }
 
    public Participante localizarParticipante(String cpf) {
        for (Participante participante : participantes) {
            if (participante.getCPF().equals(cpf)) {
                return participante;
            }
        }
        return null; // null se o participante não for encontrado
    }
 
    public void remover(Evento eventoParaRemover) {
        eventos.remove(eventoParaRemover);
    }
    
    public void removerParticipante(Participante participante) {
        participantes.remove(participante);
    }

    public void removerIngresso(Ingresso ingresso) {
    	ingressos.remove(ingresso);
    }
    

    public Ingresso localizarIngresso(String codigoIngresso) {
        for (Ingresso ingresso : this.ingressos) {
        	//TESTANDO INGRESSO SE TA NULL
            //System.err.println("Código Ingresso na Lista: " + ingresso.getCodigoIngresso());

            if (ingresso.getCodigoIngresso().equals(codigoIngresso)) {
            	//TESTANDO SE EXISTE INGRESSO
                return ingresso;
            }
        }
        return null;
    }

    
    
}//Final class Repositorio


