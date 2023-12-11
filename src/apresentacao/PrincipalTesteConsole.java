package apresentacao;

import java.util.ArrayList;

import modelo.Ingresso;
import modelo.Participante;

public class PrincipalTesteConsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Ingresso> ingressoTeste = new ArrayList <> ();
		
		Participante pessoa1 = new Participante("123456789", "01/02/1992", ingressoTeste);
		
		System.out.println(pessoa1.toString());
		System.out.println(pessoa1.calcularIdade());

	}

}
