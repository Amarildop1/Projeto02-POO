package modelo;

import java.util.ArrayList;

public class Convidado extends Participante{
	
	private String empresa;

	public Convidado(String cpf, String dataDeNascimento, ArrayList<Ingresso> alunos, String empresa) {
		super(cpf, dataDeNascimento, alunos);
		this.empresa = empresa;
	}

	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	
	@Override
	public String toString() {
		return "Convidado [empresa=" + empresa + "]";
	}

	
	
} // Final da class Convidado

