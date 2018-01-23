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
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;

public class AjudaUi extends JInternalFrame {

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
	
	public AjudaUi() {
		setClosable(true);
		
		setTitle("Ajuda");
		setBounds(100, 100, 564, 357);
		getContentPane().setLayout(null);
		
		JTextPane txtpnAsdasdadgsds = new JTextPane();
		txtpnAsdasdadgsds.setBackground(SystemColor.activeCaptionBorder);
		txtpnAsdasdadgsds.setFont(new Font("Arial", Font.ITALIC, 16));
		txtpnAsdasdadgsds.setText("Esse programa foi desenvolvido para ajudar as empresas a ter um controle total de seus patrim\u00F4nios, mostrar qual foi o ultimo usu\u00E1rio a solicitar um patrim\u00F4nio, quem est\u00E1 com o patrim\u00F4nio, quem autorizou a retirada desse patrim\u00F4nio e qual setor est\u00E1 o patrim\u00F4nio.");
		txtpnAsdasdadgsds.setBounds(40, 115, 477, 101);
		getContentPane().add(txtpnAsdasdadgsds);
		
		JLabel lblDgm = new JLabel("DGM");
		lblDgm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDgm.setBounds(251, 51, 56, 53);
		getContentPane().add(lblDgm);
	
		setVisible(true);
	

	}


}
