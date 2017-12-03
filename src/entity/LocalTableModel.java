package entity;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.impl.CategoriaController;

public class LocalTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Categoria> listaDeCategorias;
	private String[] colunas = new String[]{"Modelo" , "Descrição"};

	public LocalTableModel() {
		
		listarCategorias();
		
	}

	public void listarCategorias() {
		CategoriaController cctoler = new CategoriaController();
		listaDeCategorias = (ArrayList<Categoria>) cctoler.listarCategorias();

	}

	@Override
	public int getColumnCount() {

		return colunas.length;
	}

	@Override
	public int getRowCount() {

		return listaDeCategorias.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.listaDeCategorias.get(rowIndex).getModelo();

		case 1:
			return this.listaDeCategorias.get(rowIndex).getDescricao();

	

		default:
			break;
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {

		return colunas[column];
	}
	
	public Categoria getCategoria(int index){
		return listaDeCategorias.get(index);
	}
}
