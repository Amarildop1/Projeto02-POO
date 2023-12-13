package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;

import modelo.Evento;
import regrasDeNegocio.Fachada;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class TelaEventos extends JFrame{

	private JFrame frameTelaEventos;
	private JButton btnListarEventos;
	private JButton btnCriarEvento;
	private JButton btnApagarEvento;
	private JLabel lblExibicaoDeEventos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEventos window = new TelaEventos();
					window.frameTelaEventos.setVisible(true);
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
		frameTelaEventos.setVisible(true);
		frameTelaEventos.setLocationRelativeTo(this); // PRA FUNCIONAR ISSO AQUI, TIVE QUE COLOCAR ESSA JANELA E A PRINCIPAL PRA HERDAR DE JFrame
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTelaEventos = new JFrame();
		frameTelaEventos.setResizable(false);
		frameTelaEventos.setTitle("EVENTOS | Sistema 4ever");
		frameTelaEventos.setBounds(100, 100, 539, 433);
		frameTelaEventos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTelaEventos.getContentPane().setLayout(null);
		
		btnListarEventos = new JButton("Listar Eventos");
		btnListarEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Evento event : Fachada.listarEventos()) 
					System.out.println(event);
				
				lblExibicaoDeEventos.setText("Evento: " + Fachada.listarEventos());
				//Fachada.listarEventos();
			}
		});
		btnListarEventos.setBounds(301, 54, 160, 44);
		frameTelaEventos.getContentPane().add(btnListarEventos);
		
		btnCriarEvento = new JButton("Criar Evento");
		btnCriarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCriarEvento telaEvento = new TelaCriarEvento();
			}
		});
		btnCriarEvento.setBounds(73, 54, 160, 44);
		frameTelaEventos.getContentPane().add(btnCriarEvento);
		
		btnApagarEvento = new JButton("Apagar Evento");
		btnApagarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int idDoEventoParaApagar;
				idDoEventoParaApagar = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID:"));
				Fachada.apagarEvento(idDoEventoParaApagar);
				
			}
		});
		btnApagarEvento.setBounds(195, 131, 143, 35);
		frameTelaEventos.getContentPane().add(btnApagarEvento);
		
		lblExibicaoDeEventos = new JLabel("Eventos");
		lblExibicaoDeEventos.setBounds(44, 202, 448, 146);
		frameTelaEventos.getContentPane().add(lblExibicaoDeEventos);

	}
}
