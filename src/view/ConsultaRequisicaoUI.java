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
import entity.TipoRequerimentoEnum;

import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;

import controller.impl.UsuarioController;

import javax.swing.event.ChangeEvent;

public class ConsultaRequisicaoUI extends JInternalFrame {
	private RequisicaoTableModel requisicaoTableModel;
	private JTable jtListarRequisicao;
	private JCheckBox chckbxInderefido;
	private JCheckBox chckbxDeferido;
	private JCheckBox chckbxPendente;
	private JCheckBox chckbxRequererPatrimonio;
	private JCheckBox chckbxDevoluo;
	private JScrollPane jspTabelaRequisicao;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ConsultaRequisicaoUI frame = new
	 * ConsultaRequisicaoUI(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 * 
	 * /** Create the frame.
	 * 
	 * @param isAll
	 */
	public ConsultaRequisicaoUI(boolean isAll) {
		setClosable(true);


		setTitle("Consulta de Requisi\u00E7\u00F5es");
		setBounds(100, 100, 819, 576);

		setVisible(true);
		JPanel jtRequisicao = new JPanel();
		jtRequisicao.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Requisi\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnEditarPatrimonio = new JButton("Editar Requisição");
		btnEditarPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isAll) {
					Requisicao req = requisicaoTableModel.getRequisicao(jtListarRequisicao.getSelectedRow());
					getParent().add(new EditarStatusRequisicaoUI( req.getPatrimonio(),req, null), 0);
				} else {
					Requisicao req = requisicaoTableModel.getRequisicao(jtListarRequisicao.getSelectedRow());
					getParent().add(
							new CadastrarRequisicaoUI(req.getTipoRequerimento(), req.getPatrimonio(), req, null), 0);
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(279).addComponent(btnEditarPatrimonio,
								GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(jtRequisicao,
								GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(51, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(42)
						.addComponent(jtRequisicao, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnEditarPatrimonio).addGap(18)));
		chckbxPendente = new JCheckBox("Pendente");
		chckbxPendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requisicaoTableModel = new RequisicaoTableModel(isAll, chckbxInderefido.isSelected(),
						chckbxPendente.isSelected(), chckbxDeferido.isSelected(), chckbxDevoluo.isSelected(),
						chckbxRequererPatrimonio.isSelected());
				jtListarRequisicao.setModel(requisicaoTableModel);
				jspTabelaRequisicao.setViewportView(jtListarRequisicao);
			}
		});

		chckbxPendente.setBounds(30, 42, 109, 23);

		chckbxDeferido = new JCheckBox("Deferido");
		chckbxDeferido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requisicaoTableModel = new RequisicaoTableModel(isAll, chckbxInderefido.isSelected(),
						chckbxPendente.isSelected(), chckbxDeferido.isSelected(), chckbxDevoluo.isSelected(),
						chckbxRequererPatrimonio.isSelected());
				jtListarRequisicao.setModel(requisicaoTableModel);
				jspTabelaRequisicao.setViewportView(jtListarRequisicao);
			}
		});

		chckbxDeferido.setBounds(34, 68, 105, 23);

		chckbxInderefido = new JCheckBox("Inderefido");
		chckbxInderefido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requisicaoTableModel = new RequisicaoTableModel(isAll, chckbxInderefido.isSelected(),
						chckbxPendente.isSelected(), chckbxDeferido.isSelected(), chckbxDevoluo.isSelected(),
						chckbxRequererPatrimonio.isSelected());
				jtListarRequisicao.setModel(requisicaoTableModel);
				jspTabelaRequisicao.setViewportView(jtListarRequisicao);
			}
		});

		chckbxInderefido.setBounds(30, 94, 109, 23);

		chckbxDevoluo = new JCheckBox("Devolu\u00E7\u00E3o");
		chckbxDevoluo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requisicaoTableModel = new RequisicaoTableModel(isAll, chckbxInderefido.isSelected(),
						chckbxPendente.isSelected(), chckbxDeferido.isSelected(), chckbxDevoluo.isSelected(),
						chckbxRequererPatrimonio.isSelected());
				jtListarRequisicao.setModel(requisicaoTableModel);
				jspTabelaRequisicao.setViewportView(jtListarRequisicao);
			}
		});

		chckbxDevoluo.setBounds(28, 32, 139, 23);

		chckbxRequererPatrimonio = new JCheckBox("Requerer Patrimonio");
		chckbxRequererPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requisicaoTableModel = new RequisicaoTableModel(isAll, chckbxInderefido.isSelected(),
						chckbxPendente.isSelected(), chckbxDeferido.isSelected(), chckbxDevoluo.isSelected(),
						chckbxRequererPatrimonio.isSelected());
				jtListarRequisicao = new JTable();
				jtListarRequisicao.setModel(requisicaoTableModel);
				jspTabelaRequisicao.setViewportView(jtListarRequisicao);

			}
		});

		chckbxRequererPatrimonio.setBounds(6, 58, 161, 23);

		jspTabelaRequisicao = new JScrollPane();

		jtListarRequisicao = new JTable();

		requisicaoTableModel = new RequisicaoTableModel(isAll, false, false, false, false, false);

		jtListarRequisicao.setModel(requisicaoTableModel);
		jspTabelaRequisicao.setViewportView(jtListarRequisicao);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filtros", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_jpRequisicao = new GroupLayout(jtRequisicao);
		gl_jpRequisicao.setHorizontalGroup(gl_jpRequisicao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpRequisicao.createSequentialGroup().addContainerGap()
						.addComponent(jspTabelaRequisicao, GroupLayout.PREFERRED_SIZE, 542, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE).addContainerGap()));
		gl_jpRequisicao.setVerticalGroup(gl_jpRequisicao.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jpRequisicao.createSequentialGroup()
						.addGroup(gl_jpRequisicao.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_jpRequisicao.createSequentialGroup().addGap(11).addComponent(panel,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(jspTabelaRequisicao, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
						.addGap(22)));
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Etapa do Processo", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 194, 173, 154);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Requerimento",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 32, 173, 147);
		panel_1.add(chckbxInderefido);
		panel_1.add(chckbxPendente);
		panel_1.add(chckbxDeferido);
		panel.add(panel_2);
		panel_2.add(chckbxDevoluo);
		panel_2.add(chckbxRequererPatrimonio);
		jtRequisicao.setLayout(gl_jpRequisicao);
		getContentPane().setLayout(groupLayout);

	}
}
