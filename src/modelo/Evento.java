package modelo;

import java.util.ArrayList;

public class Evento {
	
	private static int proximoIdEvento = 1;
	 
	private int idEvento;
	private String dataEvento;
	private String descricaoEvento;
	private int capacidadeEvento;
	private double precoEvento;
	private ArrayList<Ingresso> ingressos;  // Lista de ingressos associados ao evento

	
	public Evento(String dataEvento, String descricaoEvento, int capacidadeEvento, double precoEvento) {
		super();
		 // Data e a descri��o do evento obrigat�rias
        if (dataEvento != null && !dataEvento.isEmpty() && descricaoEvento != null && !descricaoEvento.isEmpty()) {
            this.idEvento = proximoIdEvento++;
            this.dataEvento = dataEvento;
            this.descricaoEvento = descricaoEvento;

            if (capacidadeEvento >= 2) {
                this.capacidadeEvento = capacidadeEvento;
            } else {
                throw new IllegalArgumentException("A capacidade do evento deve ser no m�nimo 2 ingressos.");
            }

            // Pre�o do evento n�o pode ser negativo
            if (precoEvento >= 0) {
                this.precoEvento = precoEvento;
            } else {
                throw new IllegalArgumentException("O pre�o do evento n�o pode ser negativo.");
            }

            this.ingressos = new ArrayList<>();
        } else {
            throw new IllegalArgumentException("A data e a descri��o do evento s�o obrigat�rias.");
        }

	} // Final do construtor Evento




	public int getIdEvento() {
		return idEvento;
	}



	public String getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}


	public String getDescricaoEvento() {
		return descricaoEvento;
	}

	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
	}


	public int getCapacidadeEvento() {
		return capacidadeEvento;
	}

	public void setCapacidadeEvento(int capacidadeEvento) {
		this.capacidadeEvento = capacidadeEvento;
	}


	public double getPrecoEvento() {
		return precoEvento;
	}

	public void setPrecoEvento(double precoEvento) {
		this.precoEvento = precoEvento;
	}


    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }


    public void adicionarIngresso(Ingresso ingresso) {
        // Verifica se a quantidade de ingressos n�o ultrapassa a capacidade do evento
        if (ingressos.size() < capacidadeEvento) {
            this.ingressos.add(ingresso);
        } else {
            throw new IllegalStateException("A quantidade de ingressos n�o pode ultrapassar a capacidade do evento.");
        }
    }


	public boolean lotado() {
		return ingressos.size() >= capacidadeEvento;
	}



} // Final class Evento

