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
import entity.Local;
import entity.Requisicao;
import entity.Usuario;
import entity.UsuarioTableModel;

public class SelecionarUsuarioLocalUI extends JInternalFrame {
	private JTable jtListaUsuario;
	private JScrollPane jspTabelaUsuario;

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
	 * @wbp.parser.constructor
	 */
	UsuarioTableModel pModel;
	CadastrarLocalUI cadastrarLocalUI; 
	ActionListener action;
	public SelecionarUsuarioLocalUI(Local local, ActionListener action ) {
		this.action = action;
		setTitle("Consulta Local");
		setBounds(100, 100, 630, 468);
		AtualizarTablemodel();
		setVisible(true);
		JPanel jpUsuarios = new JPanel();
		jpUsuarios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Locais",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				local.setUsuarioGestor( pModel.getUsuario(jtListaUsuario.getSelectedRow()));
				JOptionPane.showInternalMessageDialog(getParent(), "Usuario Selecionado!");
				action.actionPerformed(arg0);
			 
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(jpUsuarios,
										GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addGap(240).addComponent(btnSelecionar,
										GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(jpUsuarios, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSelecionar)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

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

	}

	private TableModel AtualizarTablemodel() {
		pModel = new UsuarioTableModel();

		return pModel;
	}
}
