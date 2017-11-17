package entity;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import controller.impl.CategoriaController;

public class CategoriaComboBox extends AbstractListModel<Categoria> implements ComboBoxModel<Categoria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CategoriaController categoriaController;
	Categoria categoria;
	List<Categoria> lista;

	public CategoriaComboBox() {
		super();
		this.categoriaController = new CategoriaController();
		this.lista = categoriaController.listarCategorias();
	}

	@Override
	public Categoria getElementAt(int index) {
		return lista.get(index);
	}

	@Override
	public int getSize() {
		return lista.size();
	}

	@Override
	public Object getSelectedItem() {

		return this.categoria;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.categoria = (Categoria) anItem;
		fireContentsChanged(this.lista, 0, lista.size());
	}

	public List<Categoria> listaCategoria() {
		return lista;

	}
}