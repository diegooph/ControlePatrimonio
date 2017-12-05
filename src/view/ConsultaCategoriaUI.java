package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import controller.impl.CategoriaController;
import controller.impl.PatrimonioController;
import controller.impl.UsuarioController;
import entity.CategoriaTableModel;
import entity.PermisaoEnum;

public class ConsultaCategoriaUI extends JInternalFrame {
	private JTable jtListaCategoria;

	private CategoriaTableModel pModel;

	private JButton btnEditar;

	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaCategoriaUI frame = new ConsultaCategoriaUI();
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
	public ConsultaCategoriaUI() {
		setClosable(true);
		setTitle("Consulta Categorias");
		setBounds(100, 100, 684, 515);
		setVisible(true);
		JPanel jpPesquisa = new JPanel();
		jpPesquisa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista Categorias",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getParent().add(new CadastrarCategoriaUI(pModel.getCategoria(jtListaCategoria.getSelectedRow())), 0);

			}
		});
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CategoriaController Ccont = new CategoriaController();
				Ccont.remover(pModel.getCategoria(jtListaCategoria.getSelectedRow()));
				AtualizarTablemodel();
				
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(jpPesquisa,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(154)
								.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnExcluir)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(jpPesquisa, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(btnExcluir)
								.addComponent(btnEditar))
						.addGap(25)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] { btnEditar, btnExcluir });

		JScrollPane jspTabelaProduto = new JScrollPane();

		jtListaCategoria = new JTable();
		jtListaCategoria.setModel(AtualizarTablemodel());
		jspTabelaProduto.setViewportView(jtListaCategoria);
		GroupLayout gl_jpPesquisa = new GroupLayout(jpPesquisa);
		gl_jpPesquisa
				.setHorizontalGroup(gl_jpPesquisa.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						gl_jpPesquisa.createSequentialGroup()
								.addComponent(jspTabelaProduto, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
								.addContainerGap()));
		gl_jpPesquisa.setVerticalGroup(gl_jpPesquisa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpPesquisa.createSequentialGroup().addContainerGap()
						.addComponent(jspTabelaProduto, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
						.addContainerGap()));
		jpPesquisa.setLayout(gl_jpPesquisa);
		getContentPane().setLayout(groupLayout);
		if (UsuarioController.getUsuario().getPermisaoUsuario() == PermisaoEnum.USUARIO) {
			btnEditar.setVisible(false);
			btnExcluir.setVisible(false);
		}
	}

	private TableModel AtualizarTablemodel() {
		pModel = new CategoriaTableModel();

		return pModel;
	}
}
