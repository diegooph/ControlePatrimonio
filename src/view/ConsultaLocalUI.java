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
import controller.impl.LocalController;
import controller.impl.PatrimonioController;
import controller.impl.UsuarioController;
import entity.CategoriaTableModel;
import entity.LocalTableModel;
import entity.PermisaoEnum;

public class ConsultaLocalUI extends JInternalFrame {
	private JTable jtListalocais;

	private LocalTableModel tmodel;

	private JButton btnExcluir;

	private JButton btnEditar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaLocalUI frame = new ConsultaLocalUI();
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
	public ConsultaLocalUI() {
		setClosable(true);
		setTitle("Consulta Locais");
		setBounds(100, 100, 684, 515);
		setVisible(true);
		JPanel jpPesquisa = new JPanel();
		jpPesquisa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista Locais",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getParent().add(new CadastrarLocalUI(tmodel.getLocal(jtListalocais.getSelectedRow())), 0);

			}
		});

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalController lcont = new LocalController();
				lcont.remover(tmodel.getLocal(jtListalocais.getSelectedRow()));
				AtualizarTablemodel();
				JOptionPane.showMessageDialog(null, "Local excluído com sucesso");
			}
		});

		JButton btnVisualizarPatrimonios = new JButton("Visualizar Patrim\u00F4nios");
		btnVisualizarPatrimonios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaPatrimoniosLocalUI consultaMeusPatrimonioUI = new ConsultaPatrimoniosLocalUI(
						tmodel.getLocal(jtListalocais.getSelectedRow()));
				getParent().add(consultaMeusPatrimonioUI, 0);

			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(jpPesquisa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(41)
								.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addGap(28).addComponent(btnVisualizarPatrimonios)
								.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
								.addComponent(btnExcluir).addGap(41)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(jpPesquisa, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE).addGap(47)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEditar).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnExcluir).addComponent(btnVisualizarPatrimonios)))
						.addContainerGap()));
		groupLayout.linkSize(SwingConstants.VERTICAL,
				new Component[] { btnEditar, btnExcluir, btnVisualizarPatrimonios });
		groupLayout.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { btnEditar, btnExcluir, btnVisualizarPatrimonios });

		JScrollPane jspTabelaProduto = new JScrollPane();

		jtListalocais = new JTable();
		jtListalocais.setModel(AtualizarTablemodel());
		jspTabelaProduto.setViewportView(jtListalocais);
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
			funcaoadmin();
		}
	}

	private void funcaoadmin() {
		btnEditar.setVisible(false);
		btnExcluir.setVisible(false);
	}

	private TableModel AtualizarTablemodel() {
		tmodel = new LocalTableModel();

		return tmodel;
	}
}
