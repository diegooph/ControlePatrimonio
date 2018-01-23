package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.impl.UsuarioController;
import entity.Patrimonio;
import entity.PermisaoEnum;
import entity.Usuario;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class EditarUsuarioUi extends JInternalFrame {

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
	private JPasswordField jtfsSenhaUsuarioField;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnUsuario;
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
	public EditarUsuarioUi() {
		this.usuarioSec = UsuarioController.getUsuario();
		ConstruirTela();
		setVisible(true);
	}

	public EditarUsuarioUi(Usuario usuario) {
		this.usuarioSec = usuario;
		ConstruirTela();
		setVisible(true);
	}

	private void ConstruirTela() {
		setTitle("Editar Conta ");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 462);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 204));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Cadastro",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 475, 410);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUsurio.setBounds(109, 97, 73, 24);
		panel.add(lblUsurio);

		JLabel lblSenha = new JLabel("Nova Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSenha.setBounds(69, 165, 113, 24);
		panel.add(lblSenha);

		JLabel lblConfimeSuaSenha = new JLabel("Confime sua Senha:");
		lblConfimeSuaSenha.setFont(new Font("Arial", Font.PLAIN, 18));
		lblConfimeSuaSenha.setBounds(20, 200, 162, 24);
		panel.add(lblConfimeSuaSenha);

		jtfUsuario = new JTextField();
		jtfUsuario.setBounds(192, 102, 253, 20);
		panel.add(jtfUsuario);
		jtfUsuario.setColumns(10);

		jtfsSenha = new JPasswordField();
		jtfsSenha.setBounds(192, 170, 253, 20);
		panel.add(jtfsSenha);

		jtfsConfima = new JPasswordField();
		jtfsConfima.setBounds(192, 202, 253, 20);
		panel.add(jtfsConfima);

		JButton btnCadastrarse = new JButton("Editar Conta");
		btnCadastrarse.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				UsuarioController usuarioController = new UsuarioController();
				if (usuarioSec.getIdUsuario() != UsuarioController.getUsuario().getIdUsuario()) {
					if (rdbtnAdmin.isSelected()) {
						usuarioSec.setPermisaoUsuario(PermisaoEnum.ADMIN);

					} else {
						usuarioSec.setPermisaoUsuario(PermisaoEnum.USUARIO);
					}
					usuarioController.salvar(usuarioSec);
				} else {
					if (jtfsSenhaUsuarioField.getText().equals(usuarioSec.getSenha())) {

						if (jtfsSenha.getText().equals(jtfsConfima.getText())) {
							usuarioSec.setSenha(jtfsSenha.getText());
							usuarioController.salvar(usuarioSec);
							JOptionPane.showMessageDialog(null, "Sua conta foi alterada com sucesso ");
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Confira se você confirmou corretamente a senha");

						}
					}
				}
				dispose();
			}
		});
		btnCadastrarse.setBounds(83, 322, 113, 23);
		panel.add(btnCadastrarse);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(279, 322, 113, 23);
		panel.add(btnCancelar);

		JLabel lblDgm = new JLabel("DGM");
		lblDgm.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDgm.setBounds(187, 21, 49, 24);
		panel.add(lblDgm);

		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNomeCompleto.setBounds(49, 64, 133, 31);
		panel.add(lblNomeCompleto);

		jtfNomeCompleto = new JTextField();
		jtfNomeCompleto.setBounds(192, 71, 253, 20);
		panel.add(jtfNomeCompleto);
		jtfNomeCompleto.setColumns(10);

		JLabel lblSenha_1 = new JLabel("Senha:");
		lblSenha_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSenha_1.setBounds(121, 132, 61, 24);
		panel.add(lblSenha_1);

		jtfsSenhaUsuarioField = new JPasswordField();
		jtfsSenhaUsuarioField.setBounds(192, 137, 253, 20);
		panel.add(jtfsSenhaUsuarioField);

		JLabel lblPermissao = new JLabel("Permiss\u00E3o :");
		lblPermissao.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPermissao.setBounds(88, 235, 94, 24);
		panel.add(lblPermissao);

		rdbtnAdmin = new JRadioButton("Administrador");
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBounds(192, 236, 94, 23);
		panel.add(rdbtnAdmin);

		rdbtnUsuario = new JRadioButton("Usu\u00E1rio");
		buttonGroup.add(rdbtnUsuario);
		rdbtnUsuario.setBounds(323, 236, 61, 23);
		panel.add(rdbtnUsuario);

		JButton button = new JButton("Excluir Usuario");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UsuarioController pcont = new UsuarioController();
				Usuario usuariosel = usuarioSec;
				String mensagemConfirmacao;
				boolean autodelete;
				if (usuariosel.getIdUsuario() == UsuarioController.getUsuario().getIdUsuario()) {
					autodelete = true;
					mensagemConfirmacao = "Você deseja Excluir Sua Conta?";

				} else {
					autodelete = false;
					mensagemConfirmacao = "Deseja realmente Excluir: " + usuariosel.getNomeUsuario() + "?";
				}

				if (JOptionPane.showInternalConfirmDialog(getParent(), mensagemConfirmacao) == 0) {

					try {
						pcont.verificarExclusao(usuariosel);
						pcont.remover(usuariosel);

						if (autodelete) {
							JOptionPane.showInternalMessageDialog(getParent(),
									"Você Deletou Sua conta \n Faça Outro cadastro para Acessar novamente o Sistema!");
							UsuarioController.setUsuario(null);
							PrincipalUI.mntmSair.doClick();
						}
					} catch (Exception es) {

						es.printStackTrace();
						JOptionPane.showMessageDialog(getParent(), es.getMessage());
					}

				}
			}

		});
		button.setBounds(161, 356, 154, 23);
		panel.add(button);
		if (usuarioSec.getIdUsuario() != UsuarioController.getUsuario().getIdUsuario()) {
			listarAdminConfig();
		} else {
			listarUsuarioProprietario();
		}

	}

	private void SelecionarPermissao() {
		if (usuarioSec.getPermisaoUsuario() == PermisaoEnum.ADMIN) {
			rdbtnAdmin.setSelected(true);
		} else {
			rdbtnUsuario.setSelected(true);
		}

	}

	private void listarUsuarioProprietario() {
		preencherValores();
		jtfUsuario.setEnabled(false);
		jtfNomeCompleto.setEnabled(false);
		SelecionarPermissao();
		rdbtnAdmin.setEnabled(false);
		rdbtnUsuario.setEnabled(false);
	}

	private void listarAdminConfig() {
		preencherValores();
		jtfUsuario.setEnabled(false);
		jtfNomeCompleto.setEnabled(false);
		jtfsSenha.setVisible(false);
		jtfsConfima.setVisible(false);
		jtfsSenhaUsuarioField.setVisible(false);

	}

	private void preencherValores() {
		jtfNomeCompleto.setText(usuarioSec.getNomeUsuario());
		jtfUsuario.setText(usuarioSec.getNomeUsuario());

		SelecionarPermissao();
	}
}
