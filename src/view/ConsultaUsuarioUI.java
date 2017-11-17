package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import controller.impl.UsuarioController;
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
	public ConsultaUsuarioUI(Usuario usuario) {

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

				LoginUsuarioUi.principalUI.contentPane.add(
						new CadastroUsuarioUi( pModel.getUsuario(jtListaUsuario.getSelectedRow())), 0);

			}
		});

		JButton btnExcluirUsuario = new JButton("Excluir Usuario");
		btnExcluirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioController pcont = new UsuarioController();
				pcont.remover(pModel.getUsuario(jtListaUsuario.getSelectedRow()));
				AtualizarTablemodel();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(jpUsuarios, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(75)
							.addComponent(btnEditarUsuario, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(166)
							.addComponent(btnExcluirUsuario, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpUsuarios, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExcluirUsuario, Alignment.TRAILING)
						.addComponent(btnEditarUsuario, Alignment.TRAILING))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnEditarUsuario, btnExcluirUsuario});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnEditarUsuario, btnExcluirUsuario});

		jspTabelaUsuario = new JScrollPane();

		jtListaUsuario = new JTable();
		jtListaUsuario.setModel(AtualizarTablemodel());
		if (pModel.getRowCount()>0) {
			jtListaUsuario.setRowSelectionInterval(0, 0);
		}
		
		jspTabelaUsuario.setViewportView(jtListaUsuario);
		GroupLayout gl_jpUsuarios = new GroupLayout(jpUsuarios);
		gl_jpUsuarios.setHorizontalGroup(gl_jpUsuarios.createParallelGroup(Alignment.LEADING).addGroup(
				gl_jpUsuarios.createSequentialGroup().addComponent(jspTabelaUsuario).addContainerGap()));
		gl_jpUsuarios.setVerticalGroup(gl_jpUsuarios.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpUsuarios.createSequentialGroup().addContainerGap().addComponent(jspTabelaUsuario)
						.addContainerGap()));
		jpUsuarios.setLayout(gl_jpUsuarios);
		getContentPane().setLayout(groupLayout);

	}

	UsuarioTableModel pModel;

	private TableModel AtualizarTablemodel() {
		pModel = new UsuarioTableModel();
		
		return pModel;
	}
}
