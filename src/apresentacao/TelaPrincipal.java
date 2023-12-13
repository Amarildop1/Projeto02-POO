package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPrincipal extends JFrame{

	private JFrame frmSistemaever;
	private JButton btnEventos;
	private JButton btnIngressos;
	private JButton btnParticipantes;
	private JLabel labelTituloPrincipal;
	private JMenuBar menuBar;
	private JMenu menuSair;
	private JMenu menuSobre;
	private JMenu menuArquivo;

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
		frmSistemaever.setResizable(false);
		frmSistemaever.setTitle("Sistema 4ever - POO");
		frmSistemaever.setBounds(350, 100, 743, 564);
		frmSistemaever.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaever.getContentPane().setLayout(null);
		
		btnEventos = new JButton("Eventos");
		btnEventos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEventos telaEventos = new TelaEventos();
			}
		});
		btnEventos.setBounds(103, 142, 145, 83);
		frmSistemaever.getContentPane().add(btnEventos);
		
		btnIngressos = new JButton("Ingressos");
		btnIngressos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
			}
		});
		btnIngressos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIngressos.setBounds(454, 142, 145, 83);
		frmSistemaever.getContentPane().add(btnIngressos);
		
		btnParticipantes = new JButton("Participantes");
		btnParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaParticipantes telaParticipantes = new TelaParticipantes();
			}
		});
		btnParticipantes.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnParticipantes.setBounds(281, 297, 145, 83);
		frmSistemaever.getContentPane().add(btnParticipantes);
		
		labelTituloPrincipal = new JLabel("Sistema 4ever");
		labelTituloPrincipal.setFont(new Font("Segoe Print", Font.BOLD, 18));
		labelTituloPrincipal.setBounds(281, 35, 134, 36);
		frmSistemaever.getContentPane().add(labelTituloPrincipal);
		
		menuBar = new JMenuBar();
		frmSistemaever.setJMenuBar(menuBar);
		
		menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);
		
		menuSobre = new JMenu("Sobre");
		menuSobre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sobre = "Sistema 4ever \n v.: 1.0 > Em desenvolvimento \nPOO com Java.";
				JOptionPane.showMessageDialog(null, sobre, "Sistema 4ever | 2023 ", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuBar.add(menuSobre);
		
		menuSair = new JMenu("Sair");
		menuSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmSistemaever.dispose();
			}
		});
		menuBar.add(menuSair);
	}
}
