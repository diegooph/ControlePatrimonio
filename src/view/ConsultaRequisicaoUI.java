package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import entity.Requisicao;
import entity.RequisicaoTableModel;

public class ConsultaRequisicaoUI extends JInternalFrame {
	private RequisicaoTableModel requisicaoTableModel;
	private JTable jtListarRequisicao;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ConsultaRequisicaoUI frame = new
	 * ConsultaRequisicaoUI(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 * 
	 * /** Create the frame.
	 * @param isAll 
	 */
	public ConsultaRequisicaoUI(boolean isAll) {
		setClosable(true);
		setTitle("Consulta de Requisi\u00E7\u00F5es");
		setBounds(100, 100, 753, 552);

		setVisible(true);
		JPanel jtRequisicao = new JPanel();
		jtRequisicao.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Requisi\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnEditarPatrimonio = new JButton("Editar Requisição");
		btnEditarPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Requisicao req = requisicaoTableModel.getRequisicao(jtListarRequisicao.getSelectedRow());
				LoginUsuarioUi.principalUI.contentPane.add(new CadastrarRequisicaoUI(LoginUsuarioUi.usuario,
						req.getPatrimonio(), req, null), 0);

			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jtRequisicao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(276)
							.addComponent(btnEditarPatrimonio, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(jtRequisicao, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnEditarPatrimonio)
					.addContainerGap())
		);

		requisicaoTableModel = new RequisicaoTableModel(isAll);

		JScrollPane jspTabelaRequisicao = new JScrollPane();

		jtListarRequisicao = new JTable();
		jtListarRequisicao.setModel(requisicaoTableModel);
		jspTabelaRequisicao.setViewportView(jtListarRequisicao);
		GroupLayout gl_jpRequisicao = new GroupLayout(jtRequisicao);
		gl_jpRequisicao.setHorizontalGroup(
			gl_jpRequisicao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpRequisicao.createSequentialGroup()
					.addContainerGap()
					.addComponent(jspTabelaRequisicao, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE))
		);
		gl_jpRequisicao.setVerticalGroup(
			gl_jpRequisicao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpRequisicao.createSequentialGroup()
					.addComponent(jspTabelaRequisicao, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
					.addGap(22))
		);
		jtRequisicao.setLayout(gl_jpRequisicao);
		getContentPane().setLayout(groupLayout);

	}
}
