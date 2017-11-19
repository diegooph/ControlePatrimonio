package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.impl.RequisicaoController;
import entity.Local;
import entity.Patrimonio;
import entity.Requisicao;
import entity.StatusRequerimentoEnum;
import entity.TipoRequerimentoEnum;
import entity.Usuario;

import javax.swing.DropMode;

public class CadastrarRequisicaoUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfTitulo;

	private Requisicao requisicaoUpdate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton rdbtnDevolucao;
	JRadioButton rdbtnRelatarProblemaCom;
	JRadioButton rdbtnRequirirPatrimonio;
	private JEditorPane jtfMensagem;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { CadastrarRequisicaoUI frame = new
	 * CadastrarRequisicaoUI(null, null, null, null); frame.setVisible(true); }
	 * catch (Exception e) { e.printStackTrace(); } } }); }
	 * 
	 * /** Create the frame.
	 */
	public CadastrarRequisicaoUI(Usuario usuario, Patrimonio patrimonio, Requisicao requisicao, Local local) {
		this.requisicaoUpdate = requisicao;
		setMaximizable(true);
		setClosable(true);
		setTitle("Requisi\u00E7\u00E3o");
		setBounds(100, 100, 661, 389);

		setVisible(true);

		JPanel jpNovoModeloPatrimonio = new JPanel();
		jpNovoModeloPatrimonio.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Nova Requisi\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RequisicaoController rcon = new RequisicaoController();


				try {
					rcon.verificarEdicao(requisicao);
					requisicaoUpdate.setPatrimonio(patrimonio);
					requisicaoUpdate.setTitulo(jtfTitulo.getText());
					requisicaoUpdate.setUsuarioRequerente(usuario);
					requisicaoUpdate.setTipoRequerimento(RadioButonSelect());
					requisicaoUpdate.setMensagem(jtfMensagem.getText());
					requisicaoUpdate.setStatusRequerimento(StatusRequerimentoEnum.PENDENTE);
					rcon.salvar(usuario, patrimonio, requisicaoUpdate, local);
					JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					
				}

				dispose();
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(
										jpNovoModeloPatrimonio, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addGap(117).addComponent(btnSalvar)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnCancelar,
												GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(jpNovoModeloPatrimonio, GroupLayout.PREFERRED_SIZE, 309,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnSalvar)
								.addComponent(btnCancelar))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] { btnSalvar, btnCancelar });

		JLabel lblNome = new JLabel("Titulo:");

		jtfTitulo = new JTextField();
		jtfTitulo.setColumns(10);

		JLabel lblMensagem = new JLabel("Mensagem:");

		jtfMensagem = new JEditorPane();

		rdbtnDevolucao = new JRadioButton("Devolu\u00E7\u00E3o");
		buttonGroup.add(rdbtnDevolucao);

		rdbtnRelatarProblemaCom = new JRadioButton("Relatar Problema com Patrimonio");
		buttonGroup.add(rdbtnRelatarProblemaCom);

		rdbtnRequirirPatrimonio = new JRadioButton("Requirir Patrimonio");
		rdbtnRequirirPatrimonio.setSelected(true);
		buttonGroup.add(rdbtnRequirirPatrimonio);

		JLabel lblTipoDeRequisio = new JLabel("Tipo de Requisi\u00E7\u00E3o:");
		GroupLayout gl_jpNovoModeloPatrimonio = new GroupLayout(jpNovoModeloPatrimonio);
		gl_jpNovoModeloPatrimonio.setHorizontalGroup(
				gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING).addGroup(gl_jpNovoModeloPatrimonio
						.createSequentialGroup().addGroup(gl_jpNovoModeloPatrimonio
								.createParallelGroup(Alignment.LEADING).addGroup(gl_jpNovoModeloPatrimonio
										.createSequentialGroup().addGap(48)
										.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNome).addComponent(lblMensagem)))
								.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup().addContainerGap()
										.addComponent(lblTipoDeRequisio)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(jtfTitulo, GroupLayout.PREFERRED_SIZE, 429,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtnDevolucao)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(rdbtnRelatarProblemaCom)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(rdbtnRequirirPatrimonio)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(jtfMensagem,
												GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)))
						.addGap(19)));
		gl_jpNovoModeloPatrimonio.setVerticalGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup().addContainerGap()
						.addGroup(gl_jpNovoModeloPatrimonio
								.createParallelGroup(Alignment.TRAILING).addGroup(gl_jpNovoModeloPatrimonio
										.createSequentialGroup().addComponent(lblNome).addGap(41))
								.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
										.addComponent(jtfTitulo, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_jpNovoModeloPatrimonio
														.createParallelGroup(Alignment.BASELINE)
														.addComponent(rdbtnRelatarProblemaCom)
														.addComponent(rdbtnRequirirPatrimonio))
												.addGroup(gl_jpNovoModeloPatrimonio
														.createParallelGroup(Alignment.BASELINE)
														.addComponent(rdbtnDevolucao)
														.addComponent(lblTipoDeRequisio)))))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING)
								.addComponent(jtfMensagem, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMensagem))
						.addGap(32)));
		jpNovoModeloPatrimonio.setLayout(gl_jpNovoModeloPatrimonio);
		getContentPane().setLayout(groupLayout);
		preencherCamposParaEdicao();
		RadioButonSelected();
	}

	public TipoRequerimentoEnum RadioButonSelect() {

		if (rdbtnDevolucao.isSelected()) {

			return TipoRequerimentoEnum.DEVOLUCAO;
		} else if (rdbtnRelatarProblemaCom.isSelected()) {

			return TipoRequerimentoEnum.RELATARPROBLEMA;
		} else {

			return TipoRequerimentoEnum.REQUERERPATRIMONIO;
		}

	}

	public void RadioButonSelected() {
		if (TipoRequerimentoEnum.DEVOLUCAO == requisicaoUpdate.getTipoRequerimento()) {
			rdbtnDevolucao.setSelected(true);
			System.out.println("11111sakfo");
		} else if (TipoRequerimentoEnum.RELATARPROBLEMA == requisicaoUpdate.getTipoRequerimento()) {
			rdbtnRelatarProblemaCom.setSelected(true);
			System.out.println("des2");
		} else if (TipoRequerimentoEnum.REQUERERPATRIMONIO == requisicaoUpdate.getTipoRequerimento()) {
			rdbtnRequirirPatrimonio.setSelected(true);
			System.out.println("elseif3");
		}
		System.out.println("saioFora");
	}

	public void preencherCamposParaEdicao() {
		if (requisicaoUpdate != null) {

			jtfTitulo.setText(requisicaoUpdate.getTitulo());
			jtfMensagem.setText(requisicaoUpdate.getMensagem());
			RadioButonSelect();
		}
	}

}
