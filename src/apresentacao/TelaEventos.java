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
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Dimension;

public class TelaEventos {

	private JFrame frame;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;

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
		frame.setBounds(100, 100, 503, 352);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		button = new JButton("Listar Eventos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Evento event : Fachada.listarEventos()) 
					System.out.println(event);
				
				label.setText("Evento: " + Fachada.listarEventos());
				//Fachada.listarEventos();
			}
		});
		button.setBounds(272, 38, 177, 44);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("Criar Evento");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCriarEvento telaEvento = new TelaCriarEvento();
			}
		});
		button_1.setBounds(44, 38, 154, 44);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("Apagar Evento");
		button_2.setBounds(166, 103, 143, 23);
		frame.getContentPane().add(button_2);
		
		label = new JLabel("New label");
		label.setBounds(30, 152, 420, 139);
		frame.getContentPane().add(label);

	}
}
