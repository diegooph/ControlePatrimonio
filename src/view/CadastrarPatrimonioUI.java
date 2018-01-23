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

import controller.impl.PatrimonioController;
import entity.Categoria;
import entity.CategoriaComboBox;
import entity.Patrimonio;
import entity.Usuario;
import javax.swing.border.EtchedBorder;

public class CadastrarPatrimonioUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfNome;
	private JComboBox jcbCategoria;
	private JTextField jtfCodigo;
	private JEditorPane jtfDetalhamentoTecnico;
	private JPanel jpCadastroProdutos;
	private Patrimonio patrimonio;
	private CategoriaComboBox categBox;

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
	public CadastrarPatrimonioUI(Usuario usuario) {
		construirTela();
		this.patrimonio = new Patrimonio();
		setVisible(true);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public CadastrarPatrimonioUI(Usuario usuario, Patrimonio patrimonio) {
		construirTela();
		this.patrimonio = patrimonio;
		preencherFormulario();
		setVisible(true);
	}

	private void preencherFormulario() {
		
		
		for (int i = 0; i < jcbCategoria.getItemCount(); i++) {
			if (patrimonio.getCategoria().getIdCategoria() == ((Categoria) (jcbCategoria.getItemAt(i))).getIdCategoria()) {
				jcbCategoria.setSelectedIndex(i);
				System.out.println("oi");
			}
		}

		jtfCodigo.setText(patrimonio.getCodigo());
		jtfDetalhamentoTecnico.setText(patrimonio.getDetalhamentoTecnico());
		jtfNome.setText(patrimonio.getNomePatrimonio());

	}

	private void construirTela() {
		setClosable(true);

		setTitle("Cadastro de Patrim\u00F4nio");
		setBounds(100, 100, 599, 378);

		jpCadastroProdutos = new JPanel();
		jpCadastroProdutos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Dados do Patrim\u00F4nio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatrimonioController pdao = new PatrimonioController();

				patrimonio.setCategoria((Categoria) jcbCategoria.getSelectedItem());
				patrimonio.setCodigo(jtfCodigo.getText());
				patrimonio.setDetalhamentoTecnico(jtfDetalhamentoTecnico.getText());
				patrimonio.setNomePatrimonio(jtfNome.getText());
				pdao.salvar(patrimonio);
				JOptionPane.showMessageDialog(null, "Patrimônio Salvo com Sucesso");
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

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setToolTipText("");

		jtfNome = new JTextField();
		jtfNome.setColumns(10);

		JLabel lblcodigo = new JLabel("C\u00F3digo:");

		jtfCodigo = new JTextField();
		jtfCodigo.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria:");
		categBox = new CategoriaComboBox();
		jcbCategoria = new JComboBox<Categoria>(categBox);

		JLabel lblDetalhamentoTecnico = new JLabel("Detalhamento T\u00E9cnico:");

		jtfDetalhamentoTecnico = new JEditorPane();
		GroupLayout gl_jpCadastroProdutos = new GroupLayout(jpCadastroProdutos);
		gl_jpCadastroProdutos.setHorizontalGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jpCadastroProdutos.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDetalhamentoTecnico).addComponent(lblNome).addComponent(lblcodigo)
								.addComponent(lblCategoria, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING, false)
								.addComponent(jtfNome, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE).addComponent(
										jcbCategoria, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jtfCodigo, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
								.addComponent(jtfDetalhamentoTecnico))
						.addGap(132)));
		gl_jpCadastroProdutos.setVerticalGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCadastroProdutos.createSequentialGroup().addGap(36)
						.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtfCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblcodigo))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCategoria).addComponent(jcbCategoria, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDetalhamentoTecnico).addComponent(jtfDetalhamentoTecnico,
										GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(19, Short.MAX_VALUE)));
		jpCadastroProdutos.setLayout(gl_jpCadastroProdutos);
		getContentPane().setLayout(groupLayout);
	}
}
