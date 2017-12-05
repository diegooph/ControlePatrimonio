package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entity.Relatorio;
import entity.Requisicao;
import util.ConnectionUtil;

public class RelatorioDAO {

	public ArrayList<Relatorio> listaRelatorios = new ArrayList<>();
	private Connection con = ConnectionUtil.getConnection();

	public List<Relatorio> listarRelatorio(Date datainicio, Date datafim) {

		try {
			String sql = "call controlepatrimonio.RelatorioCategorias(?, ?);";
			PreparedStatement pmtmt = con.prepareStatement(sql);
			pmtmt.setDate(1, new java.sql.Date(datainicio.getTime()));
			pmtmt.setDate(2, new java.sql.Date(datafim.getTime()));
			ResultSet rs = pmtmt.executeQuery();
			
			while (rs.next()) {
				
				Relatorio relatorio = new Relatorio();
				relatorio.setNomeCategoria(rs.getString("modelo"));
				relatorio.setQuantidadeDeferidas(rs.getInt("QuantidadeDeferida"));
				relatorio.setQuantidadeIndeferidas(rs.getInt("quantidadeIndeferida"));
				relatorio.setQuantidadeSolicitada(rs.getInt("QuantidadeSolicitada"));
				relatorio.setQuantidadeParaLocais(rs.getInt("quantidadeParaLocais"));
				listaRelatorios.add(relatorio);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		buscarStatics();
		return listaRelatorios;
	}

	public void buscarStatics() {

		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM controlepatrimonio.listarrelatoriostatico;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			Relatorio.setQuantidadePatrimonios(rs.getInt("QuantidadePatrimonios"));
			Relatorio.setQuantidadeUsados(rs.getInt("QuantosUsados"));
			Relatorio.setQuantidadeLivres(Relatorio.getQuantidadeUsados() - Relatorio.getQuantidadePatrimonios());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
