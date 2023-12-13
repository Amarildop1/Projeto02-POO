package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TelaCriarIngresso extends JFrame{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCriarIngresso window = new TelaCriarIngresso();
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
	public TelaCriarIngresso() {
		setTitle("CRIANDO INGRESSO | Sistema 4ever");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		initialize();
		//frameTelaCriarEvento.setLocationRelativeTo(this); // PRA FUNCIONAR ISSO AQUI, TIVE QUE COLOCAR ESSA JANELA E A PRINCIPAL PRA HERDAR DE JFrame
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 539, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
