package entity;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.impl.LocalController;

public class LocalTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Local> listaDeLocais;
	private String[] colunas = new String[] { "Nome", "Gestor" };

	public LocalTableModel() {

		listarLocais();

	}

	public void listarLocais() {
		LocalController cctoler = new LocalController();
		listaDeLocais = (ArrayList<Local>) cctoler.listarLocais();

	}

	@Override
	public int getColumnCount() {

		return colunas.length;
	}

	@Override
	public int getRowCount() {

		return listaDeLocais.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.listaDeLocais.get(rowIndex).getNomeLocal();

		case 1:
			return this.listaDeLocais.get(rowIndex).getUsuarioGestor().getNomeUsuario();

		default:
			break;
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {

		return colunas[column];
	}

	public Local getLocal(int index) {
		return listaDeLocais.get(index);
	}
}
