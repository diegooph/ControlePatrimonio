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

import controller.impl.UsuarioController;
import entity.PermisaoEnum;
import entity.Requisicao;
import entity.Usuario;
import entity.UsuarioTableModel;

public class ConsultaUsuarioUI extends JInternalFrame {
	private JTable jtListaUsuario;
	private JScrollPane jspTabelaUsuario;
	private JButton btnEditarUsuario;

	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ConsultaUsuarioUI frame = new
	 * ConsultaUsuarioUI(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 **
	 * 
	 * Create the frame.
	 * 
	 * @param usuario
	 */

	public ConsultaUsuarioUI() {

		setClosable(true);
		setTitle("Consulta de Usuarios");
		setBounds(100, 100, 630, 468);
		AtualizarTablemodel();
		setVisible(true);
		JPanel jpUsuarios = new JPanel();
		jpUsuarios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Usuarios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		btnEditarUsuario = new JButton("Editar Usuario");
		btnEditarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				LoginUsuarioUi.principalUI.contentPane
						.add(new EditarUsuarioUi(pModel.getUsuario(jtListaUsuario.getSelectedRow())), 0);

			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jpUsuarios, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(223)
							.addComponent(btnEditarUsuario, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpUsuarios, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(btnEditarUsuario)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		jspTabelaUsuario = new JScrollPane();

		jtListaUsuario = new JTable();
		jtListaUsuario.setModel(AtualizarTablemodel());
		if (pModel.getRowCount() > 0) {
			jtListaUsuario.setRowSelectionInterval(0, 0);
		}

		jspTabelaUsuario.setViewportView(jtListaUsuario);
		GroupLayout gl_jpUsuarios = new GroupLayout(jpUsuarios);
		gl_jpUsuarios.setHorizontalGroup(gl_jpUsuarios.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpUsuarios.createSequentialGroup().addComponent(jspTabelaUsuario).addContainerGap()));
		gl_jpUsuarios.setVerticalGroup(gl_jpUsuarios.createParallelGroup(Alignment.LEADING).addGroup(gl_jpUsuarios
				.createSequentialGroup().addContainerGap().addComponent(jspTabelaUsuario).addContainerGap()));
		jpUsuarios.setLayout(gl_jpUsuarios);
		getContentPane().setLayout(groupLayout);
if (UsuarioController.getUsuario().getPermisaoUsuario()==PermisaoEnum.USUARIO) {
	btnEditarUsuario.setVisible(false);
}
	}

	UsuarioTableModel pModel;

	private TableModel AtualizarTablemodel() {
		pModel = new UsuarioTableModel();

		return pModel;
	}
}
