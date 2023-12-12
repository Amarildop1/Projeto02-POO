package modelo;


public class Convidado extends Participante{
	
	private String empresa;

	public Convidado(String cpf, String dataDeNascimento, String empresa) {
		super(cpf, dataDeNascimento);
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
		return "Convidado: \n [CPF: " + CPF + ", Data de Nascimento: " + dataDeNascimento + 
				", Ingressos: " + ingressos + ", Empresa:" + empresa + "]";
	}

	
	
} // Final da class Convidado

