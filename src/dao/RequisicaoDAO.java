package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import controller.impl.UsuarioController;
import entity.Categoria;
import entity.Local;
import entity.Patrimonio;
import entity.PermisaoEnum;
import entity.Requisicao;
import entity.StatusRequerimentoEnum;
import entity.TipoRequerimentoEnum;
import entity.Usuario;
import util.ConnectionUtil;

public class RequisicaoDAO {

	UsuarioController userControler;
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

	public void salvar(Patrimonio patrimonio, Requisicao requisicao, Local local) {

		if (requisicao.getIdRequisicao() == 0) {
			insert(patrimonio, requisicao, local);
		} else {
			update(patrimonio, requisicao, local);
		}
	}

	private void update(Patrimonio patrimonio, Requisicao requisicao, Local local) {
		try {
			if (local!=null) {
				String sqlLocal = "INSERT INTO `controlepatrimonio`.`local_has_patrimonio`"
						+ "(`local_idLocal`,`patrimonio_idPatrimonio`)VALUES(?,?)";
						PreparedStatement pstmtLocal = con.prepareStatement(sqlLocal);
				pstmtLocal.setInt(2, local.getIdLocal());
				pstmtLocal.setInt(1, requisicao.getPatrimonio().getIdPatrimonio());
				pstmtLocal.execute();
			}
			
			String sql = "UPDATE `controlepatrimonio`.`requisicao` SET `titulo`=?, `mensagem`=?, `statusrequerimento`=?, `tipoRequerimento`=? ,`dataParecer`=?,`dataFinalizacao`=? WHERE `idRequisicao`=?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, requisicao.getTitulo());
			pstmt.setString(2, requisicao.getMensagem());
			pstmt.setInt(3, requisicao.getStatusRequerimento().getCodigo());
			pstmt.setInt(4, requisicao.getTipoRequerimento().getCodigo());

			if (requisicao.getStatusRequerimento() != StatusRequerimentoEnum.PENDENTE) {
				pstmt.setTimestamp(5, new Timestamp(requisicao.getDataParecer().getTime()));
				if (requisicao.getStatusRequerimento() == StatusRequerimentoEnum.INDEFERIDO) {
					pstmt.setTimestamp(6, new Timestamp(requisicao.getDataFinalizacao().getTime()));
				} else if (requisicao.getDataFinalizacao() == null) {
					pstmt.setDate(6, null);
				} else {
					pstmt.setTimestamp(6, new Timestamp(requisicao.getDataFinalizacao().getTime()));
					updateDatafinalizacaoRequisicao(patrimonio, requisicao, local);
				}
			} else {
				pstmt.setDate(5, null);
				pstmt.setDate(6, null);

			}

			pstmt.setInt(7, requisicao.getIdRequisicao());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateDatafinalizacaoRequisicao(Patrimonio patrimonio, Requisicao requisicao, Local local) {
		try {
System.out.println("entro aki sapora");
			String sql = "call upDataFinalizadoRequisisao(?)";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, requisicao.getIdRequisicao());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insert(Patrimonio patrimonio, Requisicao requisicao, Local local) {
		try {

			String sql = "INSERT INTO `controlepatrimonio`.`requisicao` (`titulo`, `mensagem`, `statusrequerimento`, `tipoRequerimento` ) VALUES (?, ? ,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, requisicao.getTitulo());
			pstmt.setString(2, requisicao.getMensagem());
			pstmt.setInt(3, requisicao.getStatusRequerimento().getCodigo());

			pstmt.setInt(4, requisicao.getTipoRequerimento().getCodigo());

			pstmt.execute();
			insertTabelaPatrimonio_has(patrimonio, requisicao, local);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String contatenarOr(boolean isOrenable) {
		if (isOrenable) {
			return " OR ";
		} else {
			return "";
		}

	}

	private String contatenarAnd(boolean isAndEnable) {
		if (isAndEnable) {
			return " ) And (";
		} else {
			return "";
		}

	}

	public List<Requisicao> listarTodos(boolean indeferido, boolean pendente, boolean deferido, boolean devoluo,
			boolean requererPatrimonio) {
		try {
			String filtros = "";
			boolean isOrEnable = false;
			boolean isAndEnable = false;
			if (indeferido || pendente || deferido || devoluo || requererPatrimonio) {

				filtros = "Where ((";
				if (indeferido) {
					filtros += contatenarOr(isOrEnable);
					filtros += "requisicao.statusrequerimento = " + StatusRequerimentoEnum.INDEFERIDO.getCodigo();
					isOrEnable = true;
					isAndEnable = true;
				}
				if (deferido) {
					filtros += contatenarOr(isOrEnable);
					filtros += "requisicao.statusrequerimento = " + StatusRequerimentoEnum.DEFERIDO.getCodigo();
					isOrEnable = true;
					isAndEnable = true;
				}
				if (pendente) {
					filtros += contatenarOr(isOrEnable);
					filtros += "requisicao.statusrequerimento = " + StatusRequerimentoEnum.PENDENTE.getCodigo();
					isOrEnable = true;
					isAndEnable = true;
				}
				isOrEnable = false;
				if (devoluo) {
					filtros += contatenarAnd(isAndEnable);
					filtros += "requisicao.tipoRequerimento = " + TipoRequerimentoEnum.DEVOLUCAO.getCodigo();
					isOrEnable = true;
					isAndEnable = false;
				}

				if (requererPatrimonio) {
					filtros += contatenarAnd(isAndEnable);
					filtros += contatenarOr(isOrEnable);
					filtros += "requisicao.tipoRequerimento = " + TipoRequerimentoEnum.REQUERERPATRIMONIO.getCodigo();
					isOrEnable = true;
					isAndEnable = false;
				}
				filtros += "))";
			}
			Statement stmt = con.createStatement();

			String sql = "SELECT `idRequisicao`,statusrequerimento, `titulo`, `mensagem` ,`idcategoria`,`dataParecer`,`dataFinalizacao`,`dataRequisicao` ,`descricao` ,`modelo` ,`tiporequerimento`,"
					+ "`idUsuario`,`nomeUsuario`, `permisaoUsuario`, `senhaUsuario`, `username`,`idPatrimonio`,`nomePatrimonio`, `codigo`, `detalhamentoTecnico` FROM controlepatrimonio.requisicao"
					+ " join patrimonio_has_usuario on Patrimonio_has_Usuario.Requisicao_idRequisicao = requisicao.idRequisicao"
					+ " join usuario on usuario.idUsuario = Patrimonio_has_Usuario.Usuario_idUsuario "
					+ " join patrimonio on patrimonio_has_usuario.Patrimonio_idPatrimonio = patrimonio.idPatrimonio"
					+ " join categoria on categoria.idCategoria = patrimonio.Categoria_idCategoria " + filtros;

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Requisicao requisicao = new Requisicao();
				requisicao.setIdRequisicao(rs.getInt("idRequisicao"));
				requisicao.setStatusRequerimento(
						StatusRequerimentoEnum.getStatusRequerimentoEnumByCodigo(rs.getInt("statusrequerimento")));
				requisicao.setDataRequisicao(new java.util.Date(rs.getTimestamp("dataRequisicao").getTime()));

				System.out.println(requisicao.getDataRequisicao());
				if (requisicao.getStatusRequerimento() != StatusRequerimentoEnum.PENDENTE) {
					requisicao.setDataParecer(new java.util.Date(rs.getTimestamp("dataParecer").getTime()));
					if (rs.getDate("dataFinalizacao") != null) {
						requisicao.setDataFinalizacao(new java.util.Date(rs.getTimestamp("dataFinalizacao").getTime()));
					}

				} else {
					requisicao.setDataParecer(null);
					requisicao.setDataFinalizacao(null);
				}
				requisicao.setTitulo(rs.getString("titulo"));
				requisicao.setMensagem(rs.getString("mensagem"));
				requisicao.setTipoRequerimento(
						TipoRequerimentoEnum.getTipoRequerimentoEnumByCodigo(rs.getInt("tiporequerimento")));
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

	public List<Requisicao> listarRequisicoesUsuarios(boolean indeferido, boolean pendente, boolean deferido,
			boolean devoluo, boolean requererPatrimonio) {
		try {
			String filtros = "";
			boolean isOrEnable = false;
			boolean isAndEnable = false;
			if (indeferido || pendente || deferido || devoluo || requererPatrimonio) {

				filtros = "And ((";
				if (indeferido) {
					filtros += contatenarOr(isOrEnable);
					filtros += "requisicao.statusrequerimento = " + StatusRequerimentoEnum.INDEFERIDO.getCodigo();
					isOrEnable = true;
					isAndEnable = true;
				}
				if (deferido) {
					filtros += contatenarOr(isOrEnable);
					filtros += "requisicao.statusrequerimento = " + StatusRequerimentoEnum.DEFERIDO.getCodigo();
					isOrEnable = true;
					isAndEnable = true;
				}
				if (pendente) {
					filtros += contatenarOr(isOrEnable);
					filtros += "requisicao.statusrequerimento = " + StatusRequerimentoEnum.PENDENTE.getCodigo();
					isOrEnable = true;
					isAndEnable = true;
				}
				isOrEnable = false;
				if (devoluo) {
					filtros += contatenarAnd(isAndEnable);
					filtros += "requisicao.tipoRequerimento = " + TipoRequerimentoEnum.DEVOLUCAO.getCodigo();
					isOrEnable = true;
					isAndEnable = false;
				}

				if (requererPatrimonio) {
					filtros += contatenarAnd(isAndEnable);
					filtros += contatenarOr(isOrEnable);
					filtros += "requisicao.tipoRequerimento = " + TipoRequerimentoEnum.REQUERERPATRIMONIO.getCodigo();
					isOrEnable = true;
					isAndEnable = false;
				}
				filtros += "))";
			}
			String sql = "SELECT `idRequisicao` ,`idcategoria`,`dataParecer`,`dataFinalizacao`,`dataRequisicao` ,`descricao` ,`modelo` , `titulo` , `mensagem` ,`tiporequerimento`,`statusrequerimento`,`idUsuario`,`nomeUsuario`, `permisaoUsuario`, `senhaUsuario`, `username`,`idPatrimonio`,`nomePatrimonio`, `codigo`, `detalhamentoTecnico` FROM controlepatrimonio.requisicao"
					+ " join patrimonio_has_usuario on Patrimonio_has_Usuario.Requisicao_idRequisicao = requisicao.idRequisicao"
					+ " join usuario on usuario.idUsuario = Patrimonio_has_Usuario.Usuario_idUsuario "
					+ " join patrimonio on patrimonio_has_usuario.Patrimonio_idPatrimonio = patrimonio.idPatrimonio"
					+ " join categoria on categoria.idCategoria = patrimonio.Categoria_idCategoria where usuario.idUsuario= ? "
					+ filtros;
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, UsuarioController.getUsuario().getIdUsuario());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Requisicao requisicao = new Requisicao();
				requisicao.setIdRequisicao(rs.getInt("idRequisicao"));
				requisicao.setStatusRequerimento(
						StatusRequerimentoEnum.getStatusRequerimentoEnumByCodigo(rs.getInt("statusrequerimento")));
				requisicao.setDataRequisicao(new java.util.Date(rs.getTimestamp("dataRequisicao").getTime()));
				System.out.println(requisicao.getDataRequisicao());
				if (requisicao.getStatusRequerimento() != StatusRequerimentoEnum.PENDENTE) {
					requisicao.setDataParecer(new java.util.Date(rs.getTimestamp("dataParecer").getTime()));
					if (rs.getDate("dataFinalizacao") != null) {
						requisicao.setDataFinalizacao(new java.util.Date(rs.getTimestamp("dataFinalizacao").getTime()));
					}

				} else {
					requisicao.setDataParecer(null);
					requisicao.setDataFinalizacao(null);
				}
				requisicao.setTipoRequerimento(
						TipoRequerimentoEnum.getTipoRequerimentoEnumByCodigo(rs.getInt("tiporequerimento")));
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

	private void insertTabelaPatrimonio_has(Patrimonio patrimonio, Requisicao requisicao, Local local) {
		Usuario usuario = UsuarioController.getUsuario();
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
