package entity;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.impl.RequisicaoController;

public class RequisicaoTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Requisicao> listaDeRequisicaos;
	private String[] colunas = new String[] { "Titulo", "Patrimonio", "Status" };

	public RequisicaoTableModel(boolean isAll, boolean inderefido , boolean pendente, boolean deferido, boolean devoluo, boolean relatarProblema, boolean requererPatrimonio) {

		if (isAll) {
			listarRequisicoes( inderefido , pendente,  deferido, devoluo,  relatarProblema,  requererPatrimonio);
		} else {
			listarRequisicoesUsuario(inderefido , pendente,  deferido, devoluo,  relatarProblema,  requererPatrimonio);
		}

	}

	public void listarRequisicoes(boolean inderefido, boolean pendente, boolean deferido, boolean devoluo, boolean relatarProblema, boolean requererPatrimonio) {
		RequisicaoController rdao = new RequisicaoController();
		listaDeRequisicaos = (ArrayList<Requisicao>) rdao.listarRequisicoes(inderefido , pendente,  deferido, devoluo,  relatarProblema,  requererPatrimonio);

	}

	public void listarRequisicoesUsuario(boolean inderefido, boolean pendente, boolean deferido, boolean devoluo, boolean relatarProblema, boolean requererPatrimonio) {
		RequisicaoController rdao = new RequisicaoController();
		listaDeRequisicaos = (ArrayList<Requisicao>) rdao.listarRequisicoesUsuario(inderefido , pendente,  deferido, devoluo,  relatarProblema,  requererPatrimonio);

	}

	@Override
	public int getColumnCount() {

		return colunas.length;
	}

	@Override
	public int getRowCount() {

		return listaDeRequisicaos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
		case 0:
			return this.listaDeRequisicaos.get(rowIndex).getTitulo();

		case 1:
			return this.listaDeRequisicaos.get(rowIndex).getPatrimonio().getNomePatrimonio();

		case 2:
			return this.listaDeRequisicaos.get(rowIndex).getStatusRequerimento().getDescricao();

		default:
			break;
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {

		return colunas[column];
	}

	public Requisicao getRequisicao(int index) {
		return listaDeRequisicaos.get(index);
	}
}
