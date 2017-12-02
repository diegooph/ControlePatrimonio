package view;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import controller.impl.PatrimonioController;
import controller.impl.UsuarioController;
import entity.Patrimonio;
import entity.PatrimonioTableModel;
import entity.Requisicao;
import entity.Usuario;

public class ConsultaPatrimonioUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jtListaClientes;
	private JScrollPane jspTabelaPatrimonio;
	private JButton btnEditarPatrimonio;

	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ConsultaPatrimonioUI frame = new
	 * ConsultaPatrimonioUI(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 **
	 * 
	 * Create the frame.
	 * 
	 * @param usuario
	 */
	public ConsultaPatrimonioUI() {

		setClosable(true);
		setTitle("Consulta de Patrimonios");
		setBounds(100, 100, 630, 468);
		AtualizarTablemodel();
		setVisible(true);
		JPanel jpPatrimonios = new JPanel();
		jpPatrimonios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Patrimonios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnSolicitarPatrimonio = new JButton("Solicitar Patrimonio");
		btnSolicitarPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatrimonioController pcon = new PatrimonioController();
				Patrimonio patrimonio = pModel.getPatrimonio(jtListaClientes.getSelectedRow());
				try {
					pcon.verificarDisponibilidade(patrimonio);
					getParent().add(new CadastrarRequisicaoUI(UsuarioController.getUsuario(),patrimonio, new Requisicao(), null), 0);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				
				}
			}
		});

		btnEditarPatrimonio = new JButton("Editar Patrimonio");
		btnEditarPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatrimonioController pcon = new PatrimonioController();
				Patrimonio patrimonio = pModel.getPatrimonio(jtListaClientes.getSelectedRow());
				try {
					pcon.verificarDisponibilidade(patrimonio);
					getParent().add(new CadastrarPatrimonioUI(UsuarioController.getUsuario(), patrimonio), 0);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Apenas Pratrimonios Disponiveis podem ser alterados !");
					e.printStackTrace();

				}
			}
		});

		JButton btnExcluirPatrimonio = new JButton("Excluir Patrimonio");
		btnExcluirPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatrimonioController pcont = new PatrimonioController();
				pcont.remover(pModel.getPatrimonio(jtListaClientes.getSelectedRow()));
				AtualizarTablemodel();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING).addGroup(
										groupLayout.createSequentialGroup().addContainerGap().addComponent(
												jpPatrimonios, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addGap(75)
										.addComponent(btnEditarPatrimonio, GroupLayout.PREFERRED_SIZE, 95,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnSolicitarPatrimonio)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnExcluirPatrimonio,
												GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(jpPatrimonios, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
						.addGap(8)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnExcluirPatrimonio, Alignment.TRAILING)
								.addComponent(btnSolicitarPatrimonio, Alignment.TRAILING)
								.addComponent(btnEditarPatrimonio, Alignment.TRAILING))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.linkSize(SwingConstants.VERTICAL,
				new Component[] { btnSolicitarPatrimonio, btnEditarPatrimonio, btnExcluirPatrimonio });
		groupLayout.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { btnSolicitarPatrimonio, btnEditarPatrimonio, btnExcluirPatrimonio });

		jspTabelaPatrimonio = new JScrollPane();

		jtListaClientes = new JTable();
		jtListaClientes.setModel(AtualizarTablemodel());
		if (pModel.getRowCount() > 0) {
			jtListaClientes.setRowSelectionInterval(0, 0);
		}

		jspTabelaPatrimonio.setViewportView(jtListaClientes);
		GroupLayout gl_jpPatrimonios = new GroupLayout(jpPatrimonios);
		gl_jpPatrimonios.setHorizontalGroup(gl_jpPatrimonios.createParallelGroup(Alignment.LEADING).addGroup(
				gl_jpPatrimonios.createSequentialGroup().addComponent(jspTabelaPatrimonio).addContainerGap()));
		gl_jpPatrimonios.setVerticalGroup(gl_jpPatrimonios.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpPatrimonios.createSequentialGroup().addContainerGap().addComponent(jspTabelaPatrimonio)
						.addContainerGap()));
		jpPatrimonios.setLayout(gl_jpPatrimonios);
		getContentPane().setLayout(groupLayout);

	}

	PatrimonioTableModel pModel;

	private TableModel AtualizarTablemodel() {
		pModel = new PatrimonioTableModel();

		return pModel;
	}
}
