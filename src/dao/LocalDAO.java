package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.impl.UsuarioController;
import entity.Local;
import entity.PermisaoEnum;
import entity.Requisicao;
import entity.Usuario;
import util.ConnectionUtil;

public class LocalDAO {

	private static LocalDAO instancia;
	public ArrayList<Local> listaLocals = new ArrayList<>();
	private Connection con = ConnectionUtil.getConnection();

	// Criar local caso seja nulo
	public static LocalDAO obterInstancia() {
		if (instancia == null) {
			instancia = new LocalDAO();
		}
		return instancia;
	}

	public void salvar(Local local) {

		if (local.getIdLocal() == 0) {
			insert(local);
		} else {
			update(local);
		}
	}

	private void insert(Local local) {
		try {
			String sql = "INSERT INTO `controlepatrimonio`.`local` (`nomeLocal`, `Usuario_idUsuario`) VALUES (?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, local.getNomeLocal());
			pstmt.setInt(2, local.getUsuarioGestor().getIdUsuario());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void update(Local local) {
		try {
			String sql = "UPDATE `controlepatrimonio`.`local` SET `nomeLocal`=?, `Usuario_idUsuario`=? WHERE `idLocal`=?";
			;
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, local.getNomeLocal());
			pstmt.setInt(2, local.getUsuarioGestor().getIdUsuario());
			pstmt.setInt(3, local.getIdLocal());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Local> listarTodos() {
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from local join usuario on usuario.idusuario = local.usuario_idusuario;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Local local = new Local();
				local.setIdLocal(rs.getInt("idLocal"));
				local.setNomeLocal(rs.getString("nomeLocal"));
				// Setar usuario

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("Usuario_idUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setPermisaoUsuario(PermisaoEnum.getPermisaoByCodigo(rs.getInt("permisaoUsuario")));
				usuario.setSenha(rs.getString("senhaUsuario"));
				usuario.setUsername(rs.getString("username"));
				// concatenar usuario
				local.setUsuarioGestor(usuario);

				listaLocals.add(local);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaLocals;
	}

	public void excluir(Local local) {
		try {
			String sql = "DELETE FROM `controlepatrimonio`.`local` WHERE `idLocal`=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, local.getIdLocal());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Local BuscarLocalPorRequisicao(Requisicao req) {
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from local join usuario on usuario.idusuario = local.usuario_idusuario join local_has_patrimonio on local_idLocal = idlocal;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Local local = new Local();
				local.setIdLocal(rs.getInt("idLocal"));
				local.setNomeLocal(rs.getString("nomeLocal"));
				// Setar usuario

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("Usuario_idUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setPermisaoUsuario(PermisaoEnum.getPermisaoByCodigo(rs.getInt("permisaoUsuario")));
				usuario.setSenha(rs.getString("senhaUsuario"));
				usuario.setUsername(rs.getString("username"));
				// concatenar usuario
				local.setUsuarioGestor(usuario);

				return local;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Local> listarLocaisPorUsuario() {
		try {
			
			String sql = "select * from local join usuario on usuario.idusuario = local.usuario_idusuario where idusuario= ? ";
			PreparedStatement pmtmt = con.prepareStatement(sql);
			pmtmt.setInt(1, UsuarioController.getUsuario().getIdUsuario());
			ResultSet rs = pmtmt.executeQuery();

			while (rs.next()) {
				Local local = new Local();
				local.setIdLocal(rs.getInt("idLocal"));
				local.setNomeLocal(rs.getString("nomeLocal"));
				// Setar usuario

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("Usuario_idUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setPermisaoUsuario(PermisaoEnum.getPermisaoByCodigo(rs.getInt("permisaoUsuario")));
				usuario.setSenha(rs.getString("senhaUsuario"));
				usuario.setUsername(rs.getString("username"));
				// concatenar usuario
				local.setUsuarioGestor(usuario);

				listaLocals.add(local);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaLocals;
	}

}
