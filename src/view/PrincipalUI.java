package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

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

	private JMenu mnRequerimentos;
	public static JMenuItem mntmSair;

	public PrincipalUI() {
		usuario = UsuarioController.getUsuario();

		setTitle("Sistema de Controle de Patrim\u00F4nios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1286, 795);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnPatrimonios = new JMenu("Patrim\u00F4nios");
		menuBar.add(mnPatrimonios);

		JMenuItem mntmListaPatrimonios = new JMenuItem("Lista de Patrim\u00F4nios");
		mntmListaPatrimonios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaPatrimonioUI cadastrarPatrimonioUI = new ConsultaPatrimonioUI();

				contentPane.add(cadastrarPatrimonioUI, 0);
			}
		});
		mnPatrimonios.add(mntmListaPatrimonios);

		JMenuItem mntmMeusPatrimonios = new JMenuItem("Meus Patrim\u00F4nios");
		mntmMeusPatrimonios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaMeusPatrimonioUI consultaMeusPatrimonioUI = new ConsultaMeusPatrimonioUI();
				contentPane.add(consultaMeusPatrimonioUI, 0);
			}
		});
		mnPatrimonios.add(mntmMeusPatrimonios);

		mnCategorias = new JMenu("Categorias");
		menuBar.add(mnCategorias);

		JMenuItem mntmListaDeCategoria = new JMenuItem("Lista de Categorias");
		mntmListaDeCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaCategoriaUI consultaCategoriaUI = new ConsultaCategoriaUI();
				contentPane.add(consultaCategoriaUI, 0);

			}
		});
		mnCategorias.add(mntmListaDeCategoria);

		mnRequerimentos = new JMenu("Requerimentos");
		menuBar.add(mnRequerimentos);

		JMenuItem mntmMeusRequerimentos = new JMenuItem("Meus Requerimentos");
		mntmMeusRequerimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaRequisicaoUI consultaRequisicaoUI = new ConsultaRequisicaoUI(false);
				contentPane.add(consultaRequisicaoUI, 0);

			}
		});
		mnRequerimentos.add(mntmMeusRequerimentos);

		mnLocais = new JMenu("Locais");
		menuBar.add(mnLocais);

		JMenuItem mntmListaDeLocais = new JMenuItem("Lista de Locais");
		mntmListaDeLocais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaLocalUI consultaLocalUI = new ConsultaLocalUI();
				contentPane.add(consultaLocalUI, 0);
			}
		});
		mnLocais.add(mntmListaDeLocais);

		JMenu mnUsuario = new JMenu("Usu\u00E1rio");
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
				contentPane.add(editarUsuarioUi, 0);

				if (UsuarioController.getUsuario() == null) {
					mntmSair.doClick();
				}

			}
		});
		mnUsuario.add(mntmConfiguraes);

		JMenuItem mntmListaDeUsuarios = new JMenuItem("Lista de Usu\u00E1rios");
		mntmListaDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConsultaUsuarioUI consultaUsuarioUI = new ConsultaUsuarioUI();
				contentPane.add(consultaUsuarioUI, 0);
			}
		});
		mnUsuario.add(mntmListaDeUsuarios);
		mnUsuario.add(mntmSair);
		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatorio);
		
		JMenuItem mntmRelatorioDePatrimonios = new JMenuItem("Relatório de Patrimônios");
		mntmRelatorioDePatrimonios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RelatorioUI relatorioUI = null;
				try {
					relatorioUI = new RelatorioUI();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contentPane.add(relatorioUI, 0);
			}
		});
		mnRelatorio.add(mntmRelatorioDePatrimonios);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING).addGap(0, 653, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING).addGap(0, 368, Short.MAX_VALUE));
		contentPane.setLayout(gl_contentPane);
		funcoesAdmin();

	}

	private void funcoesAdmin() {
		if (usuario.getPermisaoUsuario() == PermisaoEnum.ADMIN) {
			JMenuItem mntmRequisiccoes = new JMenuItem("Requisic\u00F5es");
			mnRequerimentos.add(mntmRequisiccoes);
			mntmRequisiccoes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ConsultaRequisicaoUI consultaRequisicaoUI = new ConsultaRequisicaoUI(true);
					contentPane.add(consultaRequisicaoUI, 0);
				}
			});

			JMenuItem mntmAdicionarNovoPatrimonio = new JMenuItem("Adicionar Novo Patrimônio");
			mntmAdicionarNovoPatrimonio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CadastrarPatrimonioUI cadastrarPatrimonioUI = new CadastrarPatrimonioUI(usuario);
					contentPane.add(cadastrarPatrimonioUI, 0);

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
					CadastrarCategoriaUI cadastrarCategoriaUI = new CadastrarCategoriaUI();
					contentPane.add(cadastrarCategoriaUI, 0);

				}

			});
			mnCategorias.add(mntmAdicionarNovaCategoria, 0);

		}
	}

	public JMenuItem getMntmSair() {
		return mntmSair;
	}

}
