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

import controller.impl.UsuarioController;
import entity.PermisaoEnum;
import entity.Usuario;

public class PrincipalUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private JPanel contentPane;

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
	private Usuario usuario;
	private JMenu mnPatrimonios;
	private JMenu mnLocais;

	private JMenu mnCategorias;
	public static JMenuItem mntmSair;

	public PrincipalUI() {
		usuario = UsuarioController.getUsuario();

		setTitle("Sistema de Controle de Patrimonios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1286, 795);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnPatrimonios = new JMenu("Patrimonios");
		menuBar.add(mnPatrimonios);

		JMenuItem mntmListaPatrimonios = new JMenuItem("Lista de Patrimonios");
		mntmListaPatrimonios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaPatrimonioUI cadastrarPatrimonioUI = new ConsultaPatrimonioUI(usuario);
				contentPane.add(cadastrarPatrimonioUI,0);
			}
		});
		mnPatrimonios.add(mntmListaPatrimonios);

		mnCategorias = new JMenu("Categorias");
		menuBar.add(mnCategorias);

		JMenuItem mntmListaDeCategoria = new JMenuItem("Lista de Categorias");
		mntmListaDeCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaCategoriaUI consultaCategoriaUI = new ConsultaCategoriaUI();
				contentPane.add(consultaCategoriaUI,0);

			}
		});
		mnCategorias.add(mntmListaDeCategoria);

		JMenu mnRequerimentos = new JMenu("Requerimentos");
		menuBar.add(mnRequerimentos);

		JMenuItem mntmRequisiccoes = new JMenuItem("Requisic\u00F5es");
		mnRequerimentos.add(mntmRequisiccoes);

		JMenuItem mntmMeusRequerimentos = new JMenuItem("Meus Requerimentos");
		mntmMeusRequerimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaRequisicaoUI consultaRequisicaoUI = new ConsultaRequisicaoUI(false);
				contentPane.add(consultaRequisicaoUI,0);

			}
		});
		mnRequerimentos.add(mntmMeusRequerimentos);
		mntmRequisiccoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaRequisicaoUI consultaRequisicaoUI = new ConsultaRequisicaoUI(true);
				contentPane.add(consultaRequisicaoUI,0);
			}
		});

		mnLocais = new JMenu("Locais");
		menuBar.add(mnLocais);

		JMenuItem mntmListaDeLocais = new JMenuItem("Lista de Locais");
		mntmListaDeLocais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelecionarUsuarioLocalUI consulta = new SelecionarUsuarioLocalUI(usuario);
				contentPane.add(consulta, 0);
			}
		});
		mnLocais.add(mntmListaDeLocais);

		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);

		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuario = new Usuario();
				dispose();
				LoginUsuarioUi login = new LoginUsuarioUi();
				login.setVisible(true);
			}
		});

		JMenuItem mntmConfiguraes = new JMenuItem("Configura\u00E7\u00F5es");
		mntmConfiguraes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarUsuarioUi editarUsuarioUi = new EditarUsuarioUi();
				contentPane.add(editarUsuarioUi,0);
				
				if (UsuarioController.getUsuario()==null) {
					mntmSair.doClick();
				}

			}
		});
		mnUsuario.add(mntmConfiguraes);

		JMenuItem mntmListaDeUsuarios = new JMenuItem("Lista de Usuarios");
		mntmListaDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConsultaUsuarioUI consultaUsuarioUI = new ConsultaUsuarioUI();
				contentPane.add(consultaUsuarioUI,0);
			}
		});
		mnUsuario.add(mntmListaDeUsuarios);
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

		if (usuario.getPermisaoUsuario() == PermisaoEnum.ADMIN) {
			funcoesAdmin();
		}
	}

	private void funcoesAdmin() {
		JMenuItem mntmAdicionarNovoPatrimonio = new JMenuItem("Adicionar Novo Patrimonio");
		mntmAdicionarNovoPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarPatrimonioUI cadastrarPatrimonioUI
				= new CadastrarPatrimonioUI(usuario);
				contentPane.add(cadastrarPatrimonioUI,0);

			}
		});
		mnPatrimonios.add(mntmAdicionarNovoPatrimonio);

		JMenuItem mntmAdicionarLocal = new JMenuItem("Adicionar Local");
		mntmAdicionarLocal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarLocalUI cll = new CadastrarLocalUI(usuario);
				contentPane.add(cll, 0);
			}
		});
		mnLocais.add(mntmAdicionarLocal);
		JMenuItem mntmAdicionarNovaCategoria = new JMenuItem("Adicionar Nova Categoria");
		mntmAdicionarNovaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarCategoriaUI cadastrarCategoriaUI = new CadastrarCategoriaUI(usuario);
				contentPane.add(cadastrarCategoriaUI,0);

			}

		});
		mnCategorias.add(mntmAdicionarNovaCategoria,0);

	}

	public JMenuItem getMntmSair() {
		return mntmSair;
	}



}
