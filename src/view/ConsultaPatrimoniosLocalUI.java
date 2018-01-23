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
import controller.impl.UsuarioController;
import entity.Local;
import entity.LocalPatrimonioTableModel;
import entity.Patrimonio;
import entity.PermisaoEnum;
import entity.Requisicao;
import entity.TipoRequerimentoEnum;

public class ConsultaPatrimoniosLocalUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jtListaClientes;
	private JScrollPane jspTabelaPatrimonio;
	private Local local;
	private JButton btnSolicitarPatrimonio;

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
	public ConsultaPatrimoniosLocalUI(Local local) {

		setClosable(true);
		setTitle("Consulta de Patrim\u00F4nios");
		setBounds(100, 100, 630, 468);
		this.local = local;
		AtualizarTablemodel();
		setVisible(true);
		JPanel jpPatrimonios = new JPanel();
		jpPatrimonios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Patrimônios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		btnSolicitarPatrimonio = new JButton("Solicitar Devolução");
		btnSolicitarPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatrimonioController pcon = new PatrimonioController();
				Patrimonio patrimonio = lModel.getPatrimonio(jtListaClientes.getSelectedRow());
				try {

					getParent().add(new CadastrarRequisicaoUI(TipoRequerimentoEnum.DEVOLUCAO, patrimonio,
							new Requisicao(), null), 0);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();

				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(jpPatrimonios,
								GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(193).addComponent(btnSolicitarPatrimonio,
								GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(jpPatrimonios, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
						.addGap(8).addComponent(btnSolicitarPatrimonio)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jspTabelaPatrimonio = new JScrollPane();

		jtListaClientes = new JTable();
		jtListaClientes.setModel(AtualizarTablemodel());
		if (lModel.getRowCount() > 0) {
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
		if (UsuarioController.getUsuario().getPermisaoUsuario() == PermisaoEnum.USUARIO) {
			btnSolicitarPatrimonio.setVisible(false);
		}
	}

	LocalPatrimonioTableModel lModel;

	private TableModel AtualizarTablemodel() {
		lModel = new LocalPatrimonioTableModel(local);

		return lModel;
	}
}
