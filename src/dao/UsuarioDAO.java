package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Categoria;
import entity.Patrimonio;
import entity.PermisaoEnum;
import entity.Requisicao;
import entity.StatusRequerimentoEnum;
import entity.TipoRequerimentoEnum;
import entity.Usuario;
import util.ConnectionUtil;

public class UsuarioDAO {

	private static UsuarioDAO instancia;
	public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private Connection con = ConnectionUtil.getConnection();

	// Criar usuario caso seja nulo
	public static UsuarioDAO obterInstancia() {
		if (instancia == null) {
			instancia = new UsuarioDAO();
		}
		return instancia;
	}

	public void salvar(Usuario usuario) {

		if (usuario.getIdUsuario() == 0) {
			insert(usuario);
		} else {
			update(usuario);
		}
	}

	private void insert(Usuario usuario) {
		try {
			String sql = "INSERT INTO `controlepatrimonio`.`usuario` (`nomeUsuario`, `permisaoUsuario`, `senhaUsuario`, `username`) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usuario.getNomeUsuario());
			pstmt.setInt(2, usuario.getPermisaoUsuario().getCodigo());
			pstmt.setString(3, (usuario.getSenha()));
			pstmt.setString(4, usuario.getUsername());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> listarTodos() {
		try {
			Statement stmt = con.createStatement();
			String sql = "select `idUsuario`, `nomeUsuario`, `permisaoUsuario`, `senhaUsuario`, `username` from usuario ";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setPermisaoUsuario(PermisaoEnum.getPermisaoByCodigo(rs.getInt("permisaoUsuario")));
				usuario.setSenha(rs.getString("senhaUsuario"));
				usuario.setUsername(rs.getString("username"));

				listaUsuarios.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaUsuarios;
	}

	private void update(Usuario usuario) {
		try {
			String sql = "UPDATE `controlepatrimonio`.`usuario` SET `nomeUsuario`=?, `permisaoUsuario`=?, `senhaUsuario`=?, `username`=? WHERE `idUsuario`=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usuario.getNomeUsuario());
			pstmt.setInt(2, usuario.getPermisaoUsuario().getCodigo());
			pstmt.setString(3, (usuario.getSenha()));
			pstmt.setString(4, usuario.getUsername());
			pstmt.setInt(5, usuario.getIdUsuario());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(Usuario usuario) {
		try {
			String sql = "DELETE FROM `controlepatrimonio`.`usuario` WHERE `idUsuario`=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, usuario.getIdUsuario());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Requisicao> BuscarRequisicoesUsuario(Usuario usuario) {

		List<Requisicao> listaRequisicaos = new ArrayList<>();

		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT `idRequisicao` , `titulo` , `mensagem`,`tipoRequerimento` ,`statusrequerimento`,`idUsuario`,`nomeUsuario`, `permisaoUsuario`, `senhaUsuario`, `username`,`idPatrimonio`,`nomePatrimonio`, `codigo`, `detalhamentoTecnico` FROM controlepatrimonio.requisicao"
					+ "join patrimonio_has_usuario on Patrimonio_has_Usuario.Requisicao_idRequisicao = requisicao.idRequisicao"
					+ "join usuario on usuario.idUsuario = Patrimonio_has_Usuario.Usuario_idUsuario "
					+ "join patrimonio on patrimonio_has_usuario.Patrimonio_idPatrimonio = patrimonio.idPatrimonio where idUsuario= ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, usuario.getIdUsuario());
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Requisicao requisicao = new Requisicao();
				requisicao.setIdRequisicao(rs.getInt("idRequisicao"));
				requisicao.setStatusRequerimento(
						StatusRequerimentoEnum.getStatusRequerimentoEnumByCodigo(rs.getInt("statusrequerimento")));
				requisicao.setTipoRequerimento(
						TipoRequerimentoEnum.getTipoRequerimentoEnumByCodigo(rs.getInt("tipoRequerimento")));
				requisicao.setTitulo(rs.getString("titulo"));
				requisicao.setMensagem(rs.getString("mensagem"));

				// selecionar patrimonio
				Patrimonio patrimonio = new Patrimonio();
				patrimonio.setIdPatrimonio(rs.getInt("idPatrimonio"));
				patrimonio.setNomePatrimonio(rs.getString("nomePatrimonio"));
				patrimonio.setCodigo(rs.getString("codigo"));
				patrimonio.setDetalhamentoTecnico(rs.getString("detalhamentoTecnico"));

				// selecionar Categoria

				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setModelo(rs.getString("modelo"));
				// Concatenar patrimonio com categoria
				patrimonio.setCategoria(categoria);

				// concatenar requisicao com patrimonio
				requisicao.setPatrimonio(patrimonio);

				requisicao.setUsuarioRequerente(usuario);

				listaRequisicaos.add(requisicao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaRequisicaos;

	}

	public Usuario validarUsuario(Usuario usuario) {

		try {
	
			String sql = "select `idUsuario`, `nomeUsuario`, `permisaoUsuario`, `senhaUsuario`, `username` from usuario where username = ? and senhaUsuario = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usuario.getUsername());
			pstmt.setString(2, usuario.getSenha());
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setPermisaoUsuario(PermisaoEnum.getPermisaoByCodigo(rs.getInt("permisaoUsuario")));
				usuario.setSenha(rs.getString("senhaUsuario"));
				usuario.setUsername(rs.getString("username"));
				System.out.println(usuario.getIdUsuario());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}
}
