package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import regrasDeNegocio.Fachada;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCriarEvento {

	private JFrame frame;
	private JLabel label;
	private JLabel lblDataEvento;
	private JLabel lblDescricaoEvento;
	private JLabel lblCapacidadeEvento;
	private JLabel lblPrecoEvento;
	private JTextField inputDataEvento;
	private JTextPane inputDescricaoEvento;
	private JTextField inputCapacidadeEvento;
	private JTextField inputPrecoEvento;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCriarEvento window = new TelaCriarEvento();
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
	public TelaCriarEvento() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("CRIANDO EVENTO");
		frame.setBounds(100, 100, 524, 394);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("Criando Evento");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(182, 24, 116, 23);
		frame.getContentPane().add(label);
		
		lblDataEvento = new JLabel("Data: ");
		lblDataEvento.setBounds(32, 83, 46, 14);
		frame.getContentPane().add(lblDataEvento);
		
		lblDescricaoEvento = new JLabel("Descri\u00E7\u00E3o");
		lblDescricaoEvento.setBounds(32, 140, 46, 14);
		frame.getContentPane().add(lblDescricaoEvento);
		
		lblCapacidadeEvento = new JLabel("Capacidade:");
		lblCapacidadeEvento.setBounds(32, 247, 85, 14);
		frame.getContentPane().add(lblCapacidadeEvento);
		
		lblPrecoEvento = new JLabel("Pre\u00E7o:");
		lblPrecoEvento.setBounds(32, 292, 46, 14);
		frame.getContentPane().add(lblPrecoEvento);
		
		inputDataEvento = new JTextField();
		inputDataEvento.setText("12/06/2025");
		inputDataEvento.setToolTipText("Exemplo:  10/12/2025");
		inputDataEvento.setBounds(146, 80, 106, 20);
		frame.getContentPane().add(inputDataEvento);
		inputDataEvento.setColumns(10);
		
		inputDescricaoEvento = new JTextPane();
		inputDescricaoEvento.setText("Testando descri\u00E7\u00E3o de evento");
		inputDescricaoEvento.setBounds(146, 140, 209, 62);
		frame.getContentPane().add(inputDescricaoEvento);
		
		inputCapacidadeEvento = new JTextField();
		inputCapacidadeEvento.setText("7");
		inputCapacidadeEvento.setBounds(146, 244, 86, 20);
		frame.getContentPane().add(inputCapacidadeEvento);
		inputCapacidadeEvento.setColumns(10);
		
		inputPrecoEvento = new JTextField();
		inputPrecoEvento.setText("160");
		inputPrecoEvento.setBounds(146, 289, 86, 20);
		frame.getContentPane().add(inputPrecoEvento);
		inputPrecoEvento.setColumns(10);
		
		button = new JButton("SALVAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int capacidade = Integer.parseInt(inputCapacidadeEvento.getText());
				double preco = Double.parseDouble(inputPrecoEvento.getText());
				Fachada.criarEvento(inputDataEvento.getText(), inputDescricaoEvento.getText(), capacidade, preco);
				frame.dispose();
			}
		});
		button.setBounds(385, 288, 89, 23);
		frame.getContentPane().add(button);
	}
}
