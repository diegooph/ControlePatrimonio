package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import entity.Relatorio;
import entity.RelatorioTableModel;
import javax.swing.SwingConstants;

public class RelatorioUI extends JInternalFrame {
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
	RelatorioTableModel pModel;
	CadastrarLocalUI cadastrarLocalUI;
	private JFormattedTextField jftfDatainicio;
	private JFormattedTextField jftfDatafim;
	private JLabel lblQuantidadeTotal;
	private JLabel lblUsadosQuantidade;
	private JLabel lblDisponiveisQuantidade;

	public RelatorioUI() throws ParseException {
		setClosable(true);

		setTitle("Consulta Local");
		setBounds(100, 100, 1132, 681);
		buscarAtual();
		setVisible(true);
		JPanel jpUsuarios = new JPanel();
		jpUsuarios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Relatorios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpUsuarios, GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpUsuarios, GroupLayout.PREFERRED_SIZE, 629, Short.MAX_VALUE)
					.addContainerGap())
		);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(228, 26, 640, 49);
		panel_1.setBorder(new TitledBorder(null, "Relatorio de Patrimonios :", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(19, 86, 1056, 533);
		panel_2.setBorder(new TitledBorder(null, "Relatorio Geral de Requisi\u00E7\u00F5es por categoria:",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Quantidade :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(12, 22, 97, 16);
		panel_1.add(lblNewLabel_1);

		JLabel lblUsados = new JLabel("Usados:");
		lblUsados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsados.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsados.setBounds(221, 24, 97, 16);
		panel_1.add(lblUsados);

		JLabel lblDisponiveis = new JLabel("Dispon\u00EDveis:");
		lblDisponiveis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDisponiveis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDisponiveis.setBounds(430, 24, 97, 16);
		panel_1.add(lblDisponiveis);

		lblQuantidadeTotal = new JLabel("Quantidade :");
		lblQuantidadeTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantidadeTotal.setBounds(121, 15, 88, 29);
		panel_1.add(lblQuantidadeTotal);

		lblUsadosQuantidade = new JLabel("Quantidade :");
		lblUsadosQuantidade.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsadosQuantidade.setBounds(330, 15, 88, 29);
		panel_1.add(lblUsadosQuantidade);

		lblDisponiveisQuantidade = new JLabel("Quantidade :");
		lblDisponiveisQuantidade.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDisponiveisQuantidade.setBounds(539, 15, 88, 29);
		panel_1.add(lblDisponiveisQuantidade);
		panel_2.setLayout(null);

		jspTabelaUsuario = new JScrollPane();
		jspTabelaUsuario.setBounds(10, 76, 1023, 446);
		panel_2.add(jspTabelaUsuario);

		jtListaUsuario = new JTable();
		jtListaUsuario.setModel(buscarAtual());

		jspTabelaUsuario.setViewportView(jtListaUsuario);

		MaskFormatter mascara = new MaskFormatter("##/##/####");
		mascara.setPlaceholderCharacter('_');
		jpUsuarios.setLayout(null);
		jpUsuarios.add(panel_2);
		
				JPanel panel = new JPanel();
				panel.setBounds(250, 21, 555, 48);
				panel_2.add(panel);
				panel.setBorder(new TitledBorder(null, "Buscar Por Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setLayout(null);
				jftfDatainicio = new JFormattedTextField(mascara);
				jftfDatainicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
				jftfDatainicio.setBounds(88, 17, 84, 20);
				
						panel.add(jftfDatainicio);
						
								JButton btnBuscar = new JButton("Buscar");
								btnBuscar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										BuscarPorDataemodel();
									}
								});
								btnBuscar.setBounds(376, 14, 145, 23);
								panel.add(btnBuscar);
								
										jftfDatafim = new JFormattedTextField(mascara);
										jftfDatafim.setFont(new Font("Tahoma", Font.PLAIN, 14));
										jftfDatafim.setBounds(260, 17, 84, 20);
										panel.add(jftfDatafim);
										
												JLabel lblNewLabel = new JLabel("De :");
												lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
												lblNewLabel.setBounds(32, 23, 46, 14);
												panel.add(lblNewLabel);
												
														JLabel lblAt = new JLabel("At\u00E9:");
														lblAt.setFont(new Font("Tahoma", Font.BOLD, 14));
														lblAt.setBounds(204, 23, 46, 14);
														panel.add(lblAt);
														
																JButton btnBuscarAtual = new JButton("Buscar Atual");
																btnBuscarAtual.setBounds(897, 42, 136, 23);
																panel_2.add(btnBuscarAtual);
																btnBuscarAtual.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		atualizarLabelsPatrimonio();
																		buscarAtual();
																	}
																});
		jpUsuarios.add(panel_1);
		getContentPane().setLayout(groupLayout);
		atualizarLabelsPatrimonio();
	}

	private TableModel buscarAtual() {
		pModel = new RelatorioTableModel();
		return pModel;

	}

	private void BuscarPorDataemodel() {
		try {
			Date datainicio = new Date(jftfDatainicio.getText());

			Date datafim = new Date(jftfDatafim.getText());
			pModel = new RelatorioTableModel(datainicio, datafim);
			jtListaUsuario.setModel(pModel);
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(getParent(), "Preencha todos os formularios");
		}
	

	
		
	}

	private void atualizarLabelsPatrimonio() {
		lblDisponiveisQuantidade.setText(Relatorio.getQuantidadeLivres() + "");
		lblQuantidadeTotal.setText(Relatorio.getQuantidadePatrimonios() + "");
		lblUsadosQuantidade.setText(Relatorio.getQuantidadeUsados() + "");
	}
}
