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
import controller.impl.PatrimonioController;
import entity.Categoria;
import entity.CategoriaComboBox;
import entity.Patrimonio;
import entity.Usuario;

public class CadastrarCategoriaUI extends JInternalFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfModelo;
	private JEditorPane jtfDescricao;
	private JPanel jpCadastroProdutos;
	private Categoria categoria;

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
	public CadastrarCategoriaUI() {
		construirTela();
		this.categoria = new Categoria();
		setVisible(true);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public CadastrarCategoriaUI( Categoria categoria) {
		construirTela();
		this.categoria = categoria;
		preencherFormulario();
		setVisible(true);
	}

	private void preencherFormulario() {

		jtfModelo.setText(categoria.getModelo());
		jtfDescricao.setText(categoria.getDescricao());

	}

	private void construirTela() {
		setClosable(true);

		setTitle("Cadastro de Patrimonio");
		setBounds(100, 100, 599, 378);

		jpCadastroProdutos = new JPanel();
		jpCadastroProdutos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Patrimonio",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CategoriaController cateCont = new CategoriaController();

				categoria.setDescricao(jtfDescricao.getText());
				categoria.setModelo(jtfModelo.getText());

				cateCont.salvar(categoria);
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
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(jpCadastroProdutos,
								GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(157)
								.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnCancelar)))
				.addContainerGap(4, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(jpCadastroProdutos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED).addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING).addComponent(btnCancelar).addComponent(btnSalvar))
				.addGap(21)));
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] { btnSalvar, btnCancelar });
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] { btnSalvar, btnCancelar });

		JLabel lblmodelo = new JLabel("Modelo:");

		jtfModelo = new JTextField();
		jtfModelo.setColumns(10);

		JLabel lblDetalhamentoTecnico = new JLabel("Descri\u00E7\u00E3o:");

		jtfDescricao = new JEditorPane();
		GroupLayout gl_jpCadastroProdutos = new GroupLayout(jpCadastroProdutos);
		gl_jpCadastroProdutos.setHorizontalGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jpCadastroProdutos.createSequentialGroup().addContainerGap().addGroup(gl_jpCadastroProdutos
						.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING,
								gl_jpCadastroProdutos.createSequentialGroup().addComponent(lblDetalhamentoTecnico)
										.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_jpCadastroProdutos.createSequentialGroup().addComponent(lblmodelo)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING, false)
								.addComponent(jtfModelo, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
								.addComponent(jtfDescricao, GroupLayout.PREFERRED_SIZE, 453,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_jpCadastroProdutos.setVerticalGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCadastroProdutos.createSequentialGroup().addGap(61)
						.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.BASELINE).addComponent(lblmodelo)
								.addComponent(jtfModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(36)
						.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDetalhamentoTecnico).addComponent(jtfDescricao,
										GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(31, Short.MAX_VALUE)));
		gl_jpCadastroProdutos.linkSize(SwingConstants.HORIZONTAL, new Component[] { jtfModelo, jtfDescricao });
		jpCadastroProdutos.setLayout(gl_jpCadastroProdutos);
		getContentPane().setLayout(groupLayout);
	}
}
