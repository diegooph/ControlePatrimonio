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

public class ConsultaLocalUI extends JInternalFrame {
	private JTable jtListalocais;

	private LocalTableModel tmodel;

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

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getParent().add(
						new CadastrarLocalUI(tmodel.getLocal(jtListalocais.getSelectedRow())),
						0);

			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalController lcont = new LocalController();
				lcont.remover(tmodel.getLocal(jtListalocais.getSelectedRow()));
				AtualizarTablemodel();
				JOptionPane.showMessageDialog(null, "Local excluído com sucesso");
			}
		});
		
		JButton btnVisualizarPatrimonios = new JButton("Visualizar Patrimonios");
		btnVisualizarPatrimonios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaLocalPatrimoniosUI consultaMeusPatrimonioUI = new ConsultaLocalPatrimoniosUI();
				getParent().add(consultaMeusPatrimonioUI,0);
				
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jpPesquisa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(146)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(btnExcluir)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(269, Short.MAX_VALUE)
					.addComponent(btnVisualizarPatrimonios)
					.addGap(264))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpPesquisa, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnVisualizarPatrimonios)
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnEditar, btnExcluir});

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

	}

	private TableModel AtualizarTablemodel() {
		tmodel = new LocalTableModel();

		return tmodel;
	}
}
