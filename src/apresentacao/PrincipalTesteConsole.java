package apresentacao;

import java.util.ArrayList;

import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;

public class PrincipalTesteConsole {

	public static void main(String[] args) {

		//Evento eventoTeste1 = new Evento(123, "25/12/2023", "Festa de Natal", 25, 10.00);
		
		ArrayList<Ingresso> ingressoTeste = new ArrayList <> ();
		Participante pessoa1 = new Participante("123456789", "01/02/1998", ingressoTeste);
		
		System.out.println(pessoa1.toString());
		System.out.println(pessoa1.calcularIdade());

		//System.out.println(ingressoTeste.toString());
		
		//Ingresso ingressoDeTeste = new Ingresso("34572905", eventoTeste1, pessoa1);
		//System.out.println(ingressoDeTeste.getCodigoIngresso());
		//System.out.println(ingressoDeTeste.toString());
		
		
		
	}

} // Final class PrincipalTesteConsole
