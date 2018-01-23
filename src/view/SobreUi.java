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
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;

public class SobreUi extends JInternalFrame {

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

	public SobreUi( ) {
		setClosable(true);
		
		setTitle("Sobre");
		setBounds(100, 100, 524, 303);
		
		setVisible(true);
		
		JTextPane txtpnAsdfasd = new JTextPane();
		txtpnAsdfasd.setBackground(SystemColor.activeCaptionBorder);
		txtpnAsdfasd.setFont(new Font("Arial", Font.ITALIC, 16));
		txtpnAsdfasd.setText("Esse trabalho foi desenvolvido pelos alunos Diego Luiz e Guilherme Ferreira no ensino SENAI CTAI Flori\u00E1nopolis, com o intuito de passar um projeto limpo e claro para todos.\r\n\r\nAgradecemos a turma, professores e a cordena\u00E7\u00E3o por ter nos ajudados e nos apoiado nesse projeto.");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(txtpnAsdfasd, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addComponent(txtpnAsdfasd, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(70, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
