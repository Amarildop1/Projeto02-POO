package modelo;

public class Ingresso {
	private String codigoIngresso;
	private String telefone;
	private Evento evento;
	private Participante participante;
	
	public Ingresso(String codigoIngresso, String telefone, Evento evento, Participante participante) {
		super();
		this.codigoIngresso = codigoIngresso;
		this.telefone = telefone;
		this.evento = evento;
		this.participante = participante;
	}

	
	public String getCodigoIngresso() {
		return codigoIngresso;
	}
	public void setCodigoIngresso(String codigoIngresso) {
		this.codigoIngresso = codigoIngresso;
	}

	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	
	

} // Final class Ingresso
