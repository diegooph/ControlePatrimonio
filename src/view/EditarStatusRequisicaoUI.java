package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

import controller.impl.LocalController;
import controller.impl.PatrimonioController;
import controller.impl.RequisicaoController;
import controller.impl.UsuarioController;
import entity.Local;
import entity.Patrimonio;
import entity.Requisicao;
import entity.StatusRequerimentoEnum;
import entity.TipoRequerimentoEnum;
import entity.Usuario;

import javax.swing.DropMode;

public class EditarStatusRequisicaoUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfTitulo;

	private Requisicao requisicaoUpdate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton rdbtnDevolucao;
	JRadioButton rdbtnRequirirPatrimonio;
	private JEditorPane jtfMensagem;
	private JLabel lblDestinado;
	private Local local;
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
	public EditarStatusRequisicaoUI(Patrimonio patrimonio, Requisicao requisicao, Local local) {
		this.local=local;
		this.requisicaoUpdate = requisicao;
		setTitle("Requisi\u00E7\u00E3o");
		setBounds(100, 100, 661, 389);

		setVisible(true);

		JPanel jpNovoModeloPatrimonio = new JPanel();
		jpNovoModeloPatrimonio.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Nova Requisi\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnSalvar = new JButton("Deferir");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RequisicaoController rcon = new RequisicaoController();
				PatrimonioController pcon = new PatrimonioController();

				try {
					if (requisicao.getTipoRequerimento() == TipoRequerimentoEnum.REQUERERPATRIMONIO) {

						rcon.verificarEdicao(requisicaoUpdate);
						pcon.verificarDisponibilidade(patrimonio);
						requisicaoUpdate.setStatusRequerimento(StatusRequerimentoEnum.DEFERIDO);
						requisicaoUpdate.setDataParecer(new Date());
						rcon.salvar(patrimonio, requisicaoUpdate, null);
					} else {
						requisicaoUpdate.setStatusRequerimento(StatusRequerimentoEnum.DEFERIDO);
						requisicaoUpdate.setDataParecer(new Date());
						requisicaoUpdate.setDataFinalizacao(new Date());
						rcon.salvar(patrimonio, requisicaoUpdate, null);

					}
					JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

				} catch (Exception e) {
					JOptionPane.showInternalMessageDialog(getParent(), e.getMessage());
					e.printStackTrace();
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

		JButton btnIndeferir = new JButton("Indeferir");
		btnIndeferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RequisicaoController rcon = new RequisicaoController();

				try {
					rcon.verificarEdicao(requisicaoUpdate);
					requisicaoUpdate.setStatusRequerimento(StatusRequerimentoEnum.INDEFERIDO);
					requisicaoUpdate.setDataParecer(new Date());
					requisicaoUpdate.setDataFinalizacao(new Date());
					rcon.salvar(patrimonio, requisicaoUpdate, null);
					JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}

				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, groupLayout
								.createSequentialGroup().addGap(10).addComponent(btnSalvar)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnIndeferir, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
								.addGap(19))
						.addGroup(
								groupLayout
										.createSequentialGroup().addComponent(jpNovoModeloPatrimonio,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(jpNovoModeloPatrimonio, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnCancelar)
						.addComponent(btnSalvar).addComponent(btnIndeferir))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] { btnSalvar, btnCancelar });

		JLabel lblNome = new JLabel("Titulo:");

		jtfTitulo = new JTextField();
		jtfTitulo.setEnabled(false);
		jtfTitulo.setEditable(false);

		JLabel lblMensagem = new JLabel("Mensagem:");

		jtfMensagem = new JEditorPane();
		jtfMensagem.setEnabled(false);
		jtfMensagem.setEditable(false);

		rdbtnDevolucao = new JRadioButton("Devolu\u00E7\u00E3o");
		rdbtnDevolucao.setEnabled(false);
		buttonGroup.add(rdbtnDevolucao);

		rdbtnRequirirPatrimonio = new JRadioButton("Requirir Patrimonio");
		rdbtnRequirirPatrimonio.setEnabled(false);
		rdbtnRequirirPatrimonio.setSelected(true);
		buttonGroup.add(rdbtnRequirirPatrimonio);

		JLabel lblTipoDeRequisio = new JLabel("Tipo de Requisi\u00E7\u00E3o:");
		
		JLabel label = new JLabel("Detinado \u00E0:");
		
		lblDestinado = new JLabel("Detinado \u00E0:");
		GroupLayout gl_jpNovoModeloPatrimonio = new GroupLayout(jpNovoModeloPatrimonio);
		gl_jpNovoModeloPatrimonio.setHorizontalGroup(
			gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
					.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
							.addGap(73)
							.addComponent(lblNome))
						.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTipoDeRequisio)))
					.addGap(4)
					.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING)
						.addComponent(jtfTitulo, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
							.addComponent(rdbtnDevolucao)
							.addGap(10)
							.addComponent(rdbtnRequirirPatrimonio)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_jpNovoModeloPatrimonio.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.TRAILING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMensagem))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING)
						.addComponent(jtfMensagem, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDestinado, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		gl_jpNovoModeloPatrimonio.setVerticalGroup(
			gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
							.addComponent(lblNome)
							.addGap(41))
						.addGroup(gl_jpNovoModeloPatrimonio.createSequentialGroup()
							.addComponent(jtfTitulo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnDevolucao)
								.addComponent(lblTipoDeRequisio)
								.addComponent(rdbtnRequirirPatrimonio))))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(lblDestinado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jpNovoModeloPatrimonio.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMensagem)
						.addComponent(jtfMensagem, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		jpNovoModeloPatrimonio.setLayout(gl_jpNovoModeloPatrimonio);
		getContentPane().setLayout(groupLayout);
		preencherCamposParaEdicao();
		RadioButonSelected();
	}

	public TipoRequerimentoEnum RadioButonSelect() {

		if (rdbtnDevolucao.isSelected()) {

			return TipoRequerimentoEnum.DEVOLUCAO;

		} else {

			return TipoRequerimentoEnum.REQUERERPATRIMONIO;
		}

	}

	public void RadioButonSelected() {
		if (TipoRequerimentoEnum.DEVOLUCAO == requisicaoUpdate.getTipoRequerimento()) {
			rdbtnDevolucao.setSelected(true);

		} else if (TipoRequerimentoEnum.REQUERERPATRIMONIO == requisicaoUpdate.getTipoRequerimento()) {
			rdbtnRequirirPatrimonio.setSelected(true);

		}

	}

	public void preencherCamposParaEdicao() {
		if (requisicaoUpdate != null) {

			jtfTitulo.setText(requisicaoUpdate.getTitulo());
			jtfMensagem.setText(requisicaoUpdate.getMensagem());
			this.local = new LocalController().BuscarLocalPorRequisicao(requisicaoUpdate);
			if (this.local != null) {
				lblDestinado.setText(local.getNomeLocal());
				
			} else {
				lblDestinado.setText("Uso particular");
			}
			
			RadioButonSelect();
		}
	}
}
