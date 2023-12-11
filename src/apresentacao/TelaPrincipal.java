package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TelaPrincipal {

	private JFrame frmSistemaever;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmSistemaever.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaever = new JFrame();
		frmSistemaever.setTitle("Sistema 4ever - POO");
		frmSistemaever.setBounds(100, 100, 450, 300);
		frmSistemaever.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaever.getContentPane().setLayout(null);
	}

}
