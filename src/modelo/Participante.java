package modelo;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Participante {
	protected String CPF;
	protected String dataDeNascimento;
	//private ArrayList<Ingresso> ingressos = new ArrayList <> ();
	protected ArrayList<Ingresso> ingressos;
	
	
	public Participante(String cpf, String dataDeNascimento) {
		super();
		CPF = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.ingressos = new ArrayList<>();
	}


	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cpf) {
		CPF = cpf;
	}


	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}


	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}



	//TA FUNCIONANDO
	public int calcularIdade() {
		// De string de data para LocalDate
        DateTimeFormatter meuFormatoPadrao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(this.dataDeNascimento, meuFormatoPadrao);

        // Calcula a diferença entre a data de nascimento e a data atual
        LocalDate dataAtual = LocalDate.now();
        long anos = ChronoUnit.YEARS.between(dataNascimento, dataAtual);

        return (int) anos;
	}


	@Override
	public String toString() {
		return "Participante: \n [CPF: " + CPF + ", Data de Nascimento: " + dataDeNascimento + 
				", \n Ingressos: " + ingressos + "]" + "\n";
	}
	

	
	public void adicionar(Ingresso ingresso) {
        this.ingressos.add(ingresso);
    }
    public void removerIngresso(Ingresso ingresso) {
        this.ingressos.remove(ingresso);
    }
    
    
    
    
    
} //Final class Participante


