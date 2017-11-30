package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import controller.impl.CategoriaController;
import controller.impl.LocalController;
import controller.impl.PatrimonioController;
import entity.Categoria;
import entity.CategoriaComboBox;
import entity.Local;
import entity.Patrimonio;
import entity.Usuario;

import javax.swing.border.EtchedBorder;

public class CadastrarLocalUI extends JInternalFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jpCadastroProdutos;
	private Local local;
	private JTextField jtfNomeLocal;
	
	//Dados em brancos
	private JLabel lblNNome;
	private JLabel lblNId;
	private JLabel lblNPermissao;
	private Usuario usuario;
	
	
	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { CadastrarPatrimonioUI frame = new
	 * CadastrarPatrimonioUI(null); frame.setVisible(true); } catch (Exception
	 * e) { e.printStackTrace(); } } }); }
	 * 
	 * 
	 * Create the frame.
	 * 
	 * @param usuario
	 */
	public CadastrarLocalUI(Usuario usuario) {
		construirTela();
		this.local = new Local();
		setVisible(true);
	}

	/**
	 * @wbp.parser.constructor
	 */
	
	//editor
	public CadastrarLocalUI(Usuario usuario, Local local) {
		construirTela();
		this.local = local;
		preencherFormulario();
		setVisible(true);
	}

	private void preencherFormulario() {
		jtfNomeLocal.setText(local.getNomeLocal());
		lblNNome.setText(local.getUsuarioGestor().getNomeUsuario());
		lblNId.setText(local.getIdLocal()+"");
		lblNPermissao.setText(local.getUsuarioGestor().getPermisaoUsuario().getDescricao());
	}
	
	

	private void construirTela() {
		setClosable(true);

		setTitle("Cadastro Local");
		setBounds(100, 100, 599, 378);

		jpCadastroProdutos = new JPanel();
		jpCadastroProdutos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Cadastro Local", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalController locCont = new LocalController();

				local.setNomeLocal(jtfNomeLocal.getText());
				//local.setModelo(jtfModelo.getText());

				locCont.salvar(local);
				JOptionPane.showMessageDialog(null, "Categoria Salva com Sucesso");
				dispose();
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(157)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancelar)
					.addContainerGap(14, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addComponent(jpCadastroProdutos, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpCadastroProdutos, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(21))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnSalvar, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnSalvar, btnCancelar});

		JLabel lblNomeLocal = new JLabel("Nome do Local:");
		lblNomeLocal.setBounds(156, 52, 101, 14);
		
		jtfNomeLocal = new JTextField();
		jtfNomeLocal.setBounds(267, 49, 151, 20);
		jtfNomeLocal.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(119, 121, 361, 148);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Usuario adm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		jpCadastroProdutos.setLayout(null);
		jpCadastroProdutos.add(lblNomeLocal);
		jpCadastroProdutos.add(jtfNomeLocal);
		jpCadastroProdutos.add(panel);
		panel.setLayout(null);
		
		JButton btnSelecionarUsuario = new JButton("Selecionar Usuario");
		btnSelecionarUsuario.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				SelecionarUsuarioLocalUI ccUsuario = new SelecionarUsuarioLocalUI(usuario, local);
				LoginUsuarioUi.principalUI.contentPane.add(ccUsuario,0);
			}
		});
		
		btnSelecionarUsuario.setBounds(197, 114, 154, 23);
		panel.add(btnSelecionarUsuario);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 23, 46, 14);
		panel.add(lblNome);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(20, 48, 47, 14);
		panel.add(lblId);
		
		JLabel lblPermisso = new JLabel("Permiss\u00E3o:");
		lblPermisso.setBounds(10, 69, 68, 14);
		panel.add(lblPermisso);
		
		lblNNome = new JLabel("");
		lblNNome.setBounds(61, 23, 46, 14);
		panel.add(lblNNome);
		
		lblNId = new JLabel("");
		lblNId.setBounds(61, 48, 46, 14);
		panel.add(lblNId);
		
		lblNPermissao = new JLabel("");
		lblNPermissao.setBounds(71, 69, 46, 14);
		panel.add(lblNPermissao);
		getContentPane().setLayout(groupLayout);
	}
}
