package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Usuario;

public class PrincipalUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public JPanel contentPane;

	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { PrincipalUI frame = new
	 * PrincipalUI(usuario); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 * 
	 * /** Create the frame.
	 * 
	 * @param usuario
	 */
	static Usuario usuario;

	public PrincipalUI(Usuario usuario) {
		setTitle("Sistema de Controle de Patrimonios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1286, 795);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPatrimonios = new JMenu("Patrimonios");
		menuBar.add(mnPatrimonios);

		JMenuItem mntmListaPatrimonios = new JMenuItem("Lista de Patrimonios");
		mntmListaPatrimonios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaPatrimonioUI cadastrarPatrimonioUI = new ConsultaPatrimonioUI(usuario);
				contentPane.add(cadastrarPatrimonioUI);
			}
		});
		mnPatrimonios.add(mntmListaPatrimonios);

		JMenuItem mntmAdicionarNovoPatrimonio = new JMenuItem("Adicionar Novo Patrimonio");
		mntmAdicionarNovoPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarPatrimonioUI cadastrarPatrimonioUI = new CadastrarPatrimonioUI(usuario);
				contentPane.add(cadastrarPatrimonioUI);

			}
		});
		mnPatrimonios.add(mntmAdicionarNovoPatrimonio);
		
		JMenu mnCategorias = new JMenu("Categorias");
		menuBar.add(mnCategorias);
		
		JMenuItem mntmListaDeCategoria = new JMenuItem("Lista de Categorias");
		mntmListaDeCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaCategoriaUI consultaCategoriaUI = new ConsultaCategoriaUI();
				contentPane.add(consultaCategoriaUI);
			
			}
		});
		mnCategorias.add(mntmListaDeCategoria);
		
		JMenuItem mntmAdicionarNovaCategoria = new JMenuItem("Adicionar Nova Categoria");
		mntmAdicionarNovaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarCategoriaUI cadastrarCategoriaUI = new CadastrarCategoriaUI(usuario);
				contentPane.add(cadastrarCategoriaUI);
			
			}
			
		});
		mnCategorias.add(mntmAdicionarNovaCategoria);
		
		JMenu mnRequerimentos = new JMenu("Requerimentos");
		menuBar.add(mnRequerimentos);
		
				JMenuItem mntmMeusPatrimonios = new JMenuItem("Requisic\u00F5es");
				mnRequerimentos.add(mntmMeusPatrimonios);
				
				JMenuItem mntmMeusRequerimentos = new JMenuItem("Meus Requerimentos");
				mntmMeusRequerimentos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ConsultaRequisicaoUI consultaRequisicaoUI = new ConsultaRequisicaoUI(false);
						contentPane.add(consultaRequisicaoUI);
						
						
					}
				});
				mnRequerimentos.add(mntmMeusRequerimentos);
				mntmMeusPatrimonios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ConsultaRequisicaoUI consultaRequisicaoUI = new ConsultaRequisicaoUI(true);
						contentPane.add(consultaRequisicaoUI);
					}
				});

		JMenu mnLocais = new JMenu("Locais");
		menuBar.add(mnLocais);

		JMenuItem mntmAdicionarLocal = new JMenuItem("Adicionar Local");
		mntmAdicionarLocal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		mnLocais.add(mntmAdicionarLocal);

		JMenuItem mntmListaDeLocais = new JMenuItem("Lista de Locais");
		mnLocais.add(mntmListaDeLocais);

		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrincipalUI.usuario = new Usuario();
				dispose();
				LoginUsuarioUi login = new LoginUsuarioUi();
				login.setVisible(true);
			}
		});

		JMenuItem mntmConfiguracoes = new JMenuItem("Configura\u00E7\u00F5es");
		mntmConfiguracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaUsuarioUI consultaUsuario = new ConsultaUsuarioUI(usuario);
				contentPane.add(consultaUsuario);
			}
		});
		mnUsuario.add(mntmConfiguracoes);
		mnUsuario.add(mntmSair);

		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);

		JMenuItem mntmSobreODGM = new JMenuItem("Sobre o DGM");
		mntmSobreODGM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		mnSobre.add(mntmSobreODGM);

		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mnSobre.add(mntmAjuda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING).addGap(0, 653, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING).addGap(0, 368, Short.MAX_VALUE));
		contentPane.setLayout(gl_contentPane);
	}

}
