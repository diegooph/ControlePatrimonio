package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.impl.UsuarioController;
import entity.Usuario;

public class CadastroUsuarioUi extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtfUsuario;
	private JPasswordField jtfsSenha;
	private JPasswordField jtfsConfima;
	private JTextField jtfNomeCompleto;
	private Usuario usuarioSec;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { CadastroUsuarioUi frame = new
	 * CadastroUsuarioUi(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 * 
	 * /** Create the frame.
	 */
	public CadastroUsuarioUi(Usuario usuario) {
		setTitle("Cadastre-se");
		this.usuarioSec = usuario;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 204));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Cadastro", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 422, 251);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUsurio.setBounds(129, 98, 73, 24);
		panel.add(lblUsurio);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSenha.setBounds(139, 133, 73, 24);
		panel.add(lblSenha);

		JLabel lblConfimeSuaSenha = new JLabel("Confime sua Senha:");
		lblConfimeSuaSenha.setFont(new Font("Arial", Font.PLAIN, 18));
		lblConfimeSuaSenha.setBounds(37, 166, 186, 24);
		panel.add(lblConfimeSuaSenha);

		jtfUsuario = new JTextField();
		jtfUsuario.setBounds(212, 103, 130, 20);
		panel.add(jtfUsuario);
		jtfUsuario.setColumns(10);

		jtfsSenha = new JPasswordField();
		jtfsSenha.setBounds(212, 138, 130, 20);
		panel.add(jtfsSenha);

		jtfsConfima = new JPasswordField();
		jtfsConfima.setBounds(212, 170, 130, 20);
		panel.add(jtfsConfima);

		JButton btnCadastrarse = new JButton("Cadastrar-se");
		btnCadastrarse.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				UsuarioController usuarioController = new UsuarioController();
				Usuario usuario = new Usuario();
				usuario.setNomeUsuario(jtfNomeCompleto.getText());
				usuario.setUsername(jtfUsuario.getText());
				if (jtfsSenha.getText().equals(jtfsConfima.getText())) {
					usuario.setSenha(jtfsSenha.getText());
					usuarioController.salvar(usuario);
					JOptionPane.showMessageDialog(null,
							"Cadastrado com Sucesso");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Confira se você confirmou corretamente a senha");

				}
			}
		});
		btnCadastrarse.setBounds(65, 201, 113, 23);
		panel.add(btnCadastrarse);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(243, 201, 113, 23);
		panel.add(btnCancelar);

		JLabel lblDgm = new JLabel("DGM");
		lblDgm.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDgm.setBounds(187, 21, 49, 24);
		panel.add(lblDgm);

		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNomeCompleto.setBounds(69, 65, 133, 31);
		panel.add(lblNomeCompleto);

		jtfNomeCompleto = new JTextField();
		jtfNomeCompleto.setBounds(212, 72, 130, 20);
		panel.add(jtfNomeCompleto);
		jtfNomeCompleto.setColumns(10);
		if (usuarioSec.getIdUsuario() > 0) {
			listarUpdate();
		}
	}

	private void listarUpdate() {
		jtfNomeCompleto.setText(usuarioSec.getNomeUsuario());
		jtfUsuario.setText(usuarioSec.getNomeUsuario());
		jtfsSenha.setText(usuarioSec.getSenha());
		jtfsConfima.setText(usuarioSec.getSenha());
		jtfsSenha.setVisible(false);
		jtfsConfima.setVisible(false);

	}
}
