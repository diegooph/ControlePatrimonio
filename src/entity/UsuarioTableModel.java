package entity;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.impl.UsuarioController;

public class UsuarioTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Usuario> listaDeUsuarios;
	private String[] colunas = new String[] { "Nome", "Permição","Nome de Usuario" };

	public UsuarioTableModel() {

		listarUsuarios();

	}

	public void listarUsuarios() {
		UsuarioController cctoler = new UsuarioController();
		listaDeUsuarios = (ArrayList<Usuario>) cctoler.listarUsuarios();

	}

	@Override
	public int getColumnCount() {

		return colunas.length;
	}

	@Override
	public int getRowCount() {

		return listaDeUsuarios.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.listaDeUsuarios.get(rowIndex).getNomeUsuario();

		case 1:
			return this.listaDeUsuarios.get(rowIndex).getPermisaoUsuario();

		case 2:
			return this.listaDeUsuarios.get(rowIndex).getUsername();

		default:
			break;
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {

		return colunas[column];
	}

	public Usuario getUsuario(int index) {
		return listaDeUsuarios.get(index);
	}
}
