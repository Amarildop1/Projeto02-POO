package modelo;

import java.util.Random;

public class Ingresso {
	
	private static final Random geradorRandom = new Random();
	
	private String codigoIngresso;
	private String telefone;
	private Evento evento;
	private Participante participante;
	
	public Ingresso(String telefone, Evento evento, Participante participante) {
		super();
		this.codigoIngresso = gerarCodigoIngresso();
		this.telefone = telefone;
		this.evento = evento;
		this.participante = participante;
	}

	
	public String getCodigoIngresso() {
		return codigoIngresso;
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


	@Override
	public String toString() {
		return "Ingresso [codigoIngresso=" + codigoIngresso + ", telefone=" + telefone + ", evento=" + evento
				+ ", participante=" + participante + "]";
	}

	
	//CALCULAR COM BASE NA TABELA DA REGRA DE NEGÓCIO
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
	}

	 private String gerarCodigoIngresso() {
		 return String.valueOf(geradorRandom.nextInt(100000));
	 }

	 
	 
} // Final class Ingresso

