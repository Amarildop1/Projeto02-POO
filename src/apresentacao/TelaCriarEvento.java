package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import regrasDeNegocio.Fachada;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCriarEvento extends JFrame{

	private JFrame frameTelaCriarEvento;
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
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCriarEvento window = new TelaCriarEvento();
					window.frameTelaCriarEvento.setVisible(true);
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
		frameTelaCriarEvento.setLocationRelativeTo(this); // PRA FUNCIONAR ISSO AQUI, TIVE QUE COLOCAR ESSA JANELA E A PRINCIPAL PRA HERDAR DE JFrame
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTelaCriarEvento = new JFrame();
		frameTelaCriarEvento.setVisible(true);
		frameTelaCriarEvento.setResizable(false);
		frameTelaCriarEvento.setTitle("CRIANDO EVENTO | Sistema 4ever");
		frameTelaCriarEvento.setBounds(100, 100, 539, 433);
		frameTelaCriarEvento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTelaCriarEvento.getContentPane().setLayout(null);
		
		label = new JLabel("Criando Evento");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(182, 11, 132, 36);
		frameTelaCriarEvento.getContentPane().add(label);
		
		lblDataEvento = new JLabel("Data: ");
		lblDataEvento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataEvento.setBounds(32, 77, 57, 23);
		frameTelaCriarEvento.getContentPane().add(lblDataEvento);
		
		lblDescricaoEvento = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricaoEvento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescricaoEvento.setBounds(32, 128, 85, 26);
		frameTelaCriarEvento.getContentPane().add(lblDescricaoEvento);
		
		lblCapacidadeEvento = new JLabel("Capacidade:");
		lblCapacidadeEvento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCapacidadeEvento.setBounds(32, 241, 96, 23);
		frameTelaCriarEvento.getContentPane().add(lblCapacidadeEvento);
		
		lblPrecoEvento = new JLabel("Pre\u00E7o:");
		lblPrecoEvento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecoEvento.setBounds(32, 289, 57, 23);
		frameTelaCriarEvento.getContentPane().add(lblPrecoEvento);
		
		inputDataEvento = new JTextField();
		inputDataEvento.setText("12/06/2025");
		inputDataEvento.setToolTipText("Exemplo:  10/12/2025");
		inputDataEvento.setBounds(146, 77, 96, 23);
		frameTelaCriarEvento.getContentPane().add(inputDataEvento);
		inputDataEvento.setColumns(10);
		
		inputDescricaoEvento = new JTextPane();
		inputDescricaoEvento.setText("Testando descri\u00E7\u00E3o de evento");
		inputDescricaoEvento.setBounds(146, 128, 223, 80);
		frameTelaCriarEvento.getContentPane().add(inputDescricaoEvento);
		
		inputCapacidadeEvento = new JTextField();
		inputCapacidadeEvento.setText("7");
		inputCapacidadeEvento.setBounds(146, 241, 66, 23);
		frameTelaCriarEvento.getContentPane().add(inputCapacidadeEvento);
		inputCapacidadeEvento.setColumns(10);
		
		inputPrecoEvento = new JTextField();
		inputPrecoEvento.setText("160");
		inputPrecoEvento.setBounds(146, 289, 66, 23);
		frameTelaCriarEvento.getContentPane().add(inputPrecoEvento);
		inputPrecoEvento.setColumns(10);
		
		button = new JButton("SALVAR");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int capacidade = Integer.parseInt(inputCapacidadeEvento.getText());
				double preco = Double.parseDouble(inputPrecoEvento.getText());
				Fachada.criarEvento(inputDataEvento.getText(), inputDescricaoEvento.getText(), capacidade, preco);
				JOptionPane.showMessageDialog(null, "EVENTO SALVO COM SUCESSO!", "Sistema 4ever", JOptionPane.INFORMATION_MESSAGE);
				frameTelaCriarEvento.dispose();
			}
		});
		button.setBounds(397, 310, 90, 36);
		frameTelaCriarEvento.getContentPane().add(button);
		
		label_1 = new JLabel("R$");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(125, 289, 18, 23);
		frameTelaCriarEvento.getContentPane().add(label_1);
	}
}
