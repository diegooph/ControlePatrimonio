package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Categoria;
import util.ConnectionUtil;

public class CategoriaDAO {

	private static CategoriaDAO instancia;
	public ArrayList<Categoria> listaCategoria = new ArrayList<>();
	private Connection con = ConnectionUtil.getConnection();

	// Criar categoria caso seja nulo
	public static CategoriaDAO obterInstancia() {
		if (instancia ==  null) {
			instancia = new CategoriaDAO();
		}
		//Test git
		return instancia;
	}

	public void salvar(Categoria categoria) {

		if (categoria.getIdCategoria() == 0) {
			insert(categoria);
		} else {
			update(categoria);
		}
	}

	private void insert(Categoria categoria) {
		try {
			String sql = "INSERT INTO `controlepatrimonio`.`categoria` (`descricao`, `modelo`) VALUES (?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, categoria.getDescricao());
			pstmt.setString(2, categoria.getModelo());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void update(Categoria categoria) {
		try {
			String sql = "UPDATE `controlepatrimonio`.`categoria` SET `descricao`=?, `modelo`=? WHERE `idCategoria`=?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, categoria.getDescricao());
			pstmt.setString(2, categoria.getModelo());
			pstmt.setInt(3, categoria.getIdCategoria());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Categoria> listarTodos() {
		try {
			Statement stmt = con.createStatement();
			String sql = "select `idCategoria`, `descricao`, `modelo` from categoria";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setModelo(rs.getString("modelo"));

				listaCategoria.add(categoria);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCategoria;
	}

	public void excluir(Categoria categoria) {
		try {
			String sql = "DELETE FROM `controlepatrimonio`.`categoria` WHERE `idCategoria`=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, categoria.getIdCategoria());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
