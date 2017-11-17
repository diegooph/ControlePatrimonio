package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Categoria;
import entity.Local;
import entity.Patrimonio;
import entity.PermisaoEnum;
import entity.Requisicao;
import entity.StatusRequerimentoEnum;
import entity.Usuario;
import util.ConnectionUtil;

public class RequisicaoDAO {

	private static RequisicaoDAO instancia;
	public ArrayList<Requisicao> listaRequisicaos = new ArrayList<>();
	private Connection con = ConnectionUtil.getConnection();

	// Criar requisicao caso seja nulo
	public static RequisicaoDAO obterInstancia() {
		if (instancia == null) {
			instancia = new RequisicaoDAO();
		}
		return instancia;
	}

	public void salvar(Usuario usuario, Patrimonio patrimonio, Requisicao requisicao, Local local) {

		if (requisicao.getIdRequisicao() == 0) {
			insert(usuario, patrimonio, requisicao, local);
		} else {
			update(usuario, patrimonio, requisicao, local);
		}
	}

	private void insert(Usuario usuario, Patrimonio patrimonio, Requisicao requisicao, Local local) {
		try {

			String sql = "INSERT INTO `controlepatrimonio`.`requisicao` (`titulo`, `mensagem`, `statusrequerimento`, `tipoRequerimento`) VALUES (?, ? , ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, requisicao.getTitulo());
			pstmt.setString(2, requisicao.getMensagem());
			pstmt.setInt(3, requisicao.getStatusRequerimento().getCodigo());
			pstmt.setInt(4, requisicao.getTipo().getCodigo());

			pstmt.execute();
			insertTabelaPatrimonio_has(usuario, patrimonio, requisicao, local);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Requisicao> listarTodos() {
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT `idRequisicao` ,`idcategoria` ,`descricao` ,`modelo` , `titulo` , `mensagem` ,`statusrequerimento`,`idUsuario`,`nomeUsuario`, `permisaoUsuario`, `senhaUsuario`, `username`,`idPatrimonio`,`nomePatrimonio`, `codigo`, `detalhamentoTecnico` FROM controlepatrimonio.requisicao"
				+" join patrimonio_has_usuario on Patrimonio_has_Usuario.Requisicao_idRequisicao = requisicao.idRequisicao"
				+" join usuario on usuario.idUsuario = Patrimonio_has_Usuario.Usuario_idUsuario "
				+" join patrimonio on patrimonio_has_usuario.Patrimonio_idPatrimonio = patrimonio.idPatrimonio"
                +" join categoria on categoria.idCategoria = patrimonio.Categoria_idCategoria";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Requisicao requisicao = new Requisicao();
				requisicao.setIdRequisicao(rs.getInt("idRequisicao"));
				requisicao.setStatusRequerimento(
				StatusRequerimentoEnum.getStatusRequerimentoEnumByCodigo(rs.getInt("statusrequerimento")));
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
				categoria.setIdCategoria(rs.getInt("idcategoria"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setModelo(rs.getString("modelo"));
				// Concatenar patrimonio com categoria
				patrimonio.setCategoria(categoria);

				// concatenar requisicao com patrimonio
				requisicao.setPatrimonio(patrimonio);

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
				usuario.setPermisaoUsuario(PermisaoEnum.getPermisaoByCodigo(rs.getInt("permisaoUsuario")));
				usuario.setSenha(rs.getString("senhaUsuario"));
				usuario.setUsername(rs.getString("username"));

				requisicao.setUsuarioRequerente(usuario);
				
				listaRequisicaos.add(requisicao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaRequisicaos;
	}

	private void update(Usuario usuario, Patrimonio patrimonio, Requisicao requisicao, Local local) {
		try {
			String sql = "UPDATE `controlepatrimonio`.`requisicao` SET `titulo`=?, `mensagem`=?, `statusrequerimento`=?, `tipoRequerimento`=? WHERE `idRequisicao`=?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, requisicao.getTitulo());
			pstmt.setString(2, requisicao.getMensagem());
			pstmt.setInt(3, requisicao.getTipo().getCodigo());
			pstmt.setInt(4, requisicao.getStatusRequerimento().getCodigo());
			pstmt.setInt(5, requisicao.getIdRequisicao());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(Requisicao requisicao) {
		try {
			String sql = "DELETE FROM `controlepatrimonio`.`requisicao` WHERE `idRequisicao`='?'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, requisicao.getIdRequisicao());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertTabelaPatrimonio_has(Usuario usuario, Patrimonio patrimonio, Requisicao requisicao,
			Local local) {
		if (local == null) {
			try {
				String sql = "INSERT INTO `controlepatrimonio`.`patrimonio_has_usuario` (`Patrimonio_idPatrimonio`, "
						+ "`Requisicao_idRequisicao`, `Usuario_idUsuario`) VALUES (?, ?, ?);";
				PreparedStatement pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, patrimonio.getIdPatrimonio());
				pstmt.setInt(2, buscarIdincertRequisicao());
				pstmt.setInt(3, usuario.getIdUsuario());
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {

			try {
				String sql = "INSERT INTO `controlepatrimonio`.`patrimonio_has_local` (`Usuario_idUsuario`, `Local_idLocal`,"
						+ " `Patrimonio_idPatrimonio`, `Requisicao_idRequisicao`) VALUES (? , ?  ,? , ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, usuario.getIdUsuario());
				pstmt.setInt(2, local.getIdLocal());
				pstmt.setInt(3, patrimonio.getIdPatrimonio());
				pstmt.setInt(4, buscarIdincertRequisicao());
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private int buscarIdincertRequisicao() {
		int id = 0;
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT idRequisicao FROM controlepatrimonio.requisicao order by idRequisicao desc LIMIT 1 ; ";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			id = rs.getInt("idRequisicao");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}
}
