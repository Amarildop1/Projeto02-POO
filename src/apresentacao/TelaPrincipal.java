package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class TelaPrincipal {

	private JFrame frmSistemaever;
	private JButton btnEventos;
	private JButton btnIngressos;
	private JButton btnParticipantes;
	private JLabel label;

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
		frmSistemaever.setBounds(350, 100, 650, 500);
		frmSistemaever.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaever.getContentPane().setLayout(null);
		
		btnEventos = new JButton("Eventos");
		btnEventos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEventos telaEventos = new TelaEventos();
			}
		});
		btnEventos.setBounds(73, 142, 145, 83);
		frmSistemaever.getContentPane().add(btnEventos);
		
		btnIngressos = new JButton("Ingressos");
		btnIngressos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaIngressos telaIngressos = new TelaIngressos();
			}
		});
		btnIngressos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIngressos.setBounds(424, 142, 145, 83);
		frmSistemaever.getContentPane().add(btnIngressos);
		
		btnParticipantes = new JButton("Participantes");
		btnParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaParticipantes telaParticipantes = new TelaParticipantes();
			}
		});
		btnParticipantes.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnParticipantes.setBounds(245, 298, 145, 83);
		frmSistemaever.getContentPane().add(btnParticipantes);
		
		label = new JLabel("Sistema 4ever");
		label.setFont(new Font("Segoe Print", Font.BOLD, 18));
		label.setBounds(245, 35, 134, 36);
		frmSistemaever.getContentPane().add(label);
	}
}
