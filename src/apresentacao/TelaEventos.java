package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class TelaEventos {

	private JFrame frame;
	private JButton button;
	private JButton button_1;
	private JButton button_2;

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
		button.setBounds(209, 38, 177, 65);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("Criar Evento");
		button_1.setBounds(53, 59, 135, 23);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("Apagar Evento");
		button_2.setBounds(53, 148, 143, 23);
		frame.getContentPane().add(button_2);
	}
}
