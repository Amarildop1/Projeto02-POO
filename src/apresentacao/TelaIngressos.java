package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;


public class TelaIngressos extends JFrame{

	private JFrame frameTelaIngressos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaIngressos window = new TelaIngressos();
					window.frameTelaIngressos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaIngressos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("INGRESSOS | Sistema 4ever");
		initialize();
		frameTelaIngressos.setVisible(true);
		frameTelaIngressos.setLocationRelativeTo(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTelaIngressos = new JFrame();
		frameTelaIngressos.setResizable(false);
		frameTelaIngressos.setTitle("INGRESSOS | Sistema 4ever");
		frameTelaIngressos.setBounds(100, 100, 539, 433);
		frameTelaIngressos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTelaIngressos.getContentPane().setLayout(null);
	}

}
