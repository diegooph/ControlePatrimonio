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
import entity.PermisaoEnum;
import entity.Requisicao;
import entity.TipoRequerimentoEnum;
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
		setTitle("Consulta de Patrim\u00F4nios");
		setBounds(100, 100, 630, 468);
		AtualizarTablemodel();
		setVisible(true);
		JPanel jpPatrimonios = new JPanel();
		jpPatrimonios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Patrimônios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnSolicitarPatrimonio = new JButton("Solicitar Patrim\u00F4nio");
		btnSolicitarPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatrimonioController pcon = new PatrimonioController();
				Patrimonio patrimonio = pModel.getPatrimonio(jtListaClientes.getSelectedRow());
				try {
					pcon.verificarDisponibilidade(patrimonio);
					getParent().add(new CadastrarRequisicaoUI(TipoRequerimentoEnum.REQUERERPATRIMONIO,patrimonio, new Requisicao(), null), 0);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				
				}
			}
		});

		btnEditarPatrimonio = new JButton("Editar Patrim\u00F4nio");
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

		JButton btnExcluirPatrimonio = new JButton("Excluir Patrim\u00F4nio");
		btnExcluirPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatrimonioController pcont = new PatrimonioController();
				pcont.remover(pModel.getPatrimonio(jtListaClientes.getSelectedRow()));
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
							.addComponent(jpPatrimonios, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(58)
							.addComponent(btnEditarPatrimonio, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSolicitarPatrimonio)
							.addGap(18)
							.addComponent(btnExcluirPatrimonio, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpPatrimonios, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditarPatrimonio)
						.addComponent(btnSolicitarPatrimonio)
						.addComponent(btnExcluirPatrimonio))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnSolicitarPatrimonio, btnEditarPatrimonio, btnExcluirPatrimonio});

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
if (UsuarioController.getUsuario().getPermisaoUsuario() == PermisaoEnum.USUARIO) {
	btnExcluirPatrimonio.setVisible(false);
	btnEditarPatrimonio.setVisible(false);
}
	}

	PatrimonioTableModel pModel;

	private TableModel AtualizarTablemodel() {
		pModel = new PatrimonioTableModel();

		return pModel;
	}
}
