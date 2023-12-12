package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;

import modelo.Evento;
import regrasDeNegocio.Fachada;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class TelaEventos {

	private JFrame frame;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEventos window = new TelaEventos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaEventos() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("EVENTOS | Sistema 4ever - POO");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		button = new JButton("Listar Eventos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Evento event : Fachada.listarEventos()) 
					System.out.println(event);
			}
		});
		button.setBounds(228, 38, 177, 44);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("Criar Evento");
		button_1.setBounds(34, 38, 154, 44);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("Apagar Evento");
		button_2.setBounds(34, 105, 143, 23);
		frame.getContentPane().add(button_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 139, 414, 111);
		frame.getContentPane().add(scrollPane);
	}
}
