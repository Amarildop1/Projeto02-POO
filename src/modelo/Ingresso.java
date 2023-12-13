package modelo;


public class Ingresso {
	
	private String codigoIngresso;
	private String telefone;
	private Evento evento;
	private Participante participante;
	
	public Ingresso(String telefone, Evento evento, Participante participante) {
		super();

		if (telefone != null && !telefone.isEmpty()) {
            this.codigoIngresso = gerarCodigoIngresso(evento.getIdEvento(), participante.getCPF());
            this.telefone = telefone;
            this.evento = evento;
            this.participante = participante;
        } else {
            throw new IllegalArgumentException("O telefone de contato � obrigat�rio.");
        }

	}// Final do construtor Ingresso

	
	
	public String getCodigoIngresso() {
		return codigoIngresso;
	}

	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		if (telefone != null && !telefone.isEmpty()) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("O telefone de contato � obrigat�rio.");
        }
	}

	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}


	
	@Override
	public String toString() {
	    return "C�digo: " + codigoIngresso + ", Telefone: " + telefone;
	}



	//CALCULAR COM BASE NA TABELA DA REGRA DE NEG�CIO - OK
	public double calcularPreco() {
		
		double precoBase = evento.getPrecoEvento();
        int idadeParticipante = participante.calcularIdade();

        if (idadeParticipante < 18) {
            return precoBase * 0.9;
        } else if (idadeParticipante >= 60) {
            return precoBase * 0.8;
        } else {
            return precoBase;
        }

	} // Final do m�todo calcularPreco


	//C�digo para identificar o ingresso com base na regra 11 do prof.
	 private String gerarCodigoIngresso(int idEvento, String cpfParticipante) {
		 return idEvento + "-" + cpfParticipante;
	 }

	 
} // Final class Ingresso


