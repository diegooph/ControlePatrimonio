package entity;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import controller.impl.RelatorioController;

public class RelatorioTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date datainicio;
	private Date datafim;
	private ArrayList<Relatorio> listaDeRelatorios;
	private String[] colunas = new String[] { "Modelo Categoria", "Quantidade Solicitada", "Quantidade Deferidas",
			"QuantidadeIndeferidas", "Quantidade destinadas para Locais" };

	public RelatorioTableModel(Date datainicio, Date datafim) {
		super();
		this.datainicio = datainicio;
		this.datafim = datafim;
		listarRelatorios();
	}

	public Date getDatainicio() {
		return datainicio;
	}

	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}

	public Date getDatafim() {
		return datafim;
	}

	public void setDatafim(Date datafim) {
		this.datafim = datafim;
	}

	public void listarRelatorios() {
		RelatorioController cctoler = new RelatorioController();
		listaDeRelatorios = (ArrayList<Relatorio>) cctoler.listarRelatorios(datainicio, datafim);

	}

	@Override
	public int getColumnCount() {

		return colunas.length;
	}

	@Override
	public int getRowCount() {

		return listaDeRelatorios.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.listaDeRelatorios.get(rowIndex).getNomeCategoria();
		case 1:
			return this.listaDeRelatorios.get(rowIndex).getQuantidadeSolicitada();
		case 2:
			return this.listaDeRelatorios.get(rowIndex).getQuantidadeDeferidas();
		case 3:
			return this.listaDeRelatorios.get(rowIndex).getQuantidadeIndeferidas();
		case 4:
			return this.listaDeRelatorios.get(rowIndex).getQuantidadeParaLocais();

		default:
			break;
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {

		return colunas[column];
	}

	public Relatorio getRelatorio(int index) {
		return listaDeRelatorios.get(index);
	}
}
