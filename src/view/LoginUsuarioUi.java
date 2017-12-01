package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.impl.UsuarioController;
import entity.Usuario;

public class LoginUsuarioUi extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JPasswordField jtfsSenha;
	private static JTextField jtfUsername;
	
	private Usuario usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUsuarioUi frame = new LoginUsuarioUi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUsuarioUi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 468);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDgm = new JLabel("DGM");
		lblDgm.setFont(new Font("Dialog", Font.ITALIC, 42));
		lblDgm.setBackground(Color.LIGHT_GRAY);
		lblDgm.setForeground(Color.BLACK);
		lblDgm.setBounds(297, 55, 108, 76);
		contentPane.add(lblDgm);

		JLabel lblControleDePatrimnio = new JLabel(
				"Controle de Patrim\u00F4nio");
		lblControleDePatrimnio.setFont(new Font("Arial Unicode MS",
				Font.ITALIC, 21));
		lblControleDePatrimnio.setBounds(238, 115, 225, 65);
		contentPane.add(lblControleDePatrimnio);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 204));
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(20, 162, 672, 256);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		lblUsurio.setBounds(105, 63, 87, 22);
		panel.add(lblUsurio);

		jtfUsername = new JTextField();
		jtfUsername.setBounds(210, 63, 248, 22);
		panel.add(jtfUsername);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 21));
		lblSenha.setBackground(Color.BLACK);
		lblSenha.setBounds(105, 114, 87, 22);
		panel.add(lblSenha);

		Button btnEntrar = new Button("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				UsuarioController usuarioController = new UsuarioController();
				usuario = new Usuario();
				usuario.setUsername(jtfUsername.getText());
				usuario.setSenha(jtfsSenha.getText());
				usuario = usuarioController.login(usuario);
				if (usuario.getIdUsuario() == 0) {
					System.out.println(usuario.getSenha());
					JOptionPane.showMessageDialog(null,
							"Usuario ou Senha Incorreto");
				} else {
					PrincipalUI principalUI = new PrincipalUI();
					dispose();
					principalUI.setVisible(true);

				}
			}
		});
		btnEntrar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnEntrar.setBackground(Color.LIGHT_GRAY);
		btnEntrar.setBounds(210, 158, 248, 22);
		panel.add(btnEntrar);

		Button btnCadastrar = new Button("Cadastrar-se");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario = new Usuario();
				CadastroUsuarioUi cadastroUi = new CadastroUsuarioUi(usuario);
				cadastroUi.setVisible(true);
			}
		});
		btnCadastrar.setBackground(Color.LIGHT_GRAY);
		btnCadastrar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnCadastrar.setBounds(210, 195, 248, 22);
		panel.add(btnCadastrar);

		jtfsSenha = new JPasswordField();
		jtfsSenha.setBounds(210, 114, 248, 23);
		panel.add(jtfsSenha);
	}

	public static void limparTela() {
		jtfsSenha.setText("");
		jtfUsername.setText("");
	}
}
