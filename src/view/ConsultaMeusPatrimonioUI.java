package view;

import java.awt.Color;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import controller.impl.PatrimonioController;
import entity.MeusPatrimonioTableModel;
import entity.Patrimonio;
import entity.Requisicao;
import entity.TipoRequerimentoEnum;

public class ConsultaMeusPatrimonioUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jtListaClientes;
	private JScrollPane jspTabelaPatrimonio;

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
	public ConsultaMeusPatrimonioUI() {

		setClosable(true);
		setTitle("Consulta de Patrimonios");
		setBounds(100, 100, 630, 468);
		AtualizarTablemodel();
		setVisible(true);
		JPanel jpPatrimonios = new JPanel();
		jpPatrimonios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Patrimonios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnSolicitarPatrimonio = new JButton("Solicitar Devolução");
		btnSolicitarPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatrimonioController pcon = new PatrimonioController();
				Patrimonio patrimonio = pModel.getPatrimonio(jtListaClientes.getSelectedRow());
				try {
				
					getParent().add(new CadastrarRequisicaoUI(TipoRequerimentoEnum.DEVOLUCAO , patrimonio, new Requisicao(), null), 0);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jpPatrimonios, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(193)
							.addComponent(btnSolicitarPatrimonio, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpPatrimonios, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(btnSolicitarPatrimonio)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

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

	MeusPatrimonioTableModel pModel;

	private TableModel AtualizarTablemodel() {
		pModel = new MeusPatrimonioTableModel();

		return pModel;
	}
}
