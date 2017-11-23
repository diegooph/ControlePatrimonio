package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.impl.UsuarioController;
import entity.Categoria;
import entity.Patrimonio;
import entity.PermisaoEnum;
import entity.Usuario;
import util.ConnectionUtil;

public class PatrimonioDAO {

	private static PatrimonioDAO instancia;
	public ArrayList<Patrimonio> listaPatrimonios = new ArrayList<>();
	private Connection con = ConnectionUtil.getConnection();

	// Criar patrimonio caso seja nulo
	public static PatrimonioDAO obterInstancia() {
		if (instancia == null) {
			instancia = new PatrimonioDAO();
		}
		return instancia;
	}

	public void salvar(Patrimonio patrimonio) {

		if (patrimonio.getIdPatrimonio() == 0) {
			insert(patrimonio);
		} else {
			update(patrimonio);
		}
	}

	private void insert(Patrimonio patrimonio) {
		try {
			String sql = "INSERT INTO `controlepatrimonio`.`patrimonio` (`nomePatrimonio`, `codigo`, `detalhamentoTecnico`, `Categoria_idCategoria`, usuario_idUsuario , ocupado) VALUES (?, ?, ?, ?, ? ,? )";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, patrimonio.getNomePatrimonio());
			pstmt.setString(2, patrimonio.getCodigo());
			pstmt.setString(3, (patrimonio.getDetalhamentoTecnico()));
			pstmt.setInt(4, patrimonio.getCategoria().getIdCategoria());
			pstmt.setInt(5,UsuarioController.getUsuario().getIdUsuario());
			pstmt.setBoolean(6, patrimonio.isOcupado());
			
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Patrimonio> listarTodos() {
		try {
			Statement stmt = con.createStatement();
			String sql = "select `idPatrimonio`, `nomePatrimonio`, `codigo`,ocupado, `detalhamentoTecnico`, `Categoria_idCategoria`, `idUsuario`,`nomeUsuario`, `permisaoUsuario`, `senhaUsuario`, `username`,"
					+ " `idCategoria`, `descricao`, `modelo`"
					+ " from patrimonio"
					+ " join categoria on categoria.idCategoria = patrimonio.Categoria_idCategoria"
					+ " join usuario on usuario.idUsuario = Patrimonio.usuario_idUsuario";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				// selecionar patrimonio
				Patrimonio patrimonio = new Patrimonio();
				patrimonio.setIdPatrimonio(rs.getInt("idPatrimonio"));
				patrimonio.setNomePatrimonio(rs.getString("nomePatrimonio"));
				patrimonio.setCodigo(rs.getString("codigo"));
				patrimonio.setDetalhamentoTecnico(rs.getString("detalhamentoTecnico"));
				patrimonio.setOcupado(rs.getBoolean("ocupado"));

				// selecionar Categoria

				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setModelo(rs.getString("modelo"));
				// Concatenar patrimonio com categoria
				patrimonio.setCategoria(categoria);
				
				
				
				// selecionar e concatenar usuario
				
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setPermisaoUsuario(PermisaoEnum.getPermisaoByCodigo(rs.getInt("permisaoUsuario")));
				usuario.setSenha(rs.getString("senhaUsuario"));
				usuario.setUsername(rs.getString("username"));
				patrimonio.setUsuario(usuario);
				listaPatrimonios.add(patrimonio);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaPatrimonios;
	}

	private void update(Patrimonio patrimonio) {
		try {
			String sql = "UPDATE `controlepatrimonio`.`patrimonio` SET `nomePatrimonio`=?, `codigo`=?, `detalhamentoTecnico`=?, `Categoria_idCategoria`=? , `ocupado`=? , `Usuario_idUsuario`=? WHERE `idPatrimonio`=?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, patrimonio.getNomePatrimonio());
			pstmt.setString(2, patrimonio.getCodigo());
			pstmt.setString(3, (patrimonio.getDetalhamentoTecnico()));
			pstmt.setInt(4, patrimonio.getCategoria().getIdCategoria());
			pstmt.setBoolean(5, patrimonio.isOcupado());
			pstmt.setInt(6,UsuarioController.getUsuario().getIdUsuario());
			
			pstmt.setInt(7, patrimonio.getIdPatrimonio());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(Patrimonio patrimonio) {
		try {
			String sql = "DELETE FROM `controlepatrimonio`.`patrimonio` WHERE `idPatrimonio`=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, patrimonio.getIdPatrimonio());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
