package entity;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import controller.impl.LocalController;

public class LocalComboBox extends AbstractListModel<Local> implements ComboBoxModel<Local> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LocalController localControler;
	Local local;
	List<Local> lista;

	public LocalComboBox() {
		super();
		this.localControler = new LocalController();
		this.lista = localControler.listarLocais();
		Local localdeff = new Local();
		localdeff.setNomeLocal("Uso particular ");
		lista.add(0, localdeff);
	}

	@Override
	public Local getElementAt(int index) {

		return lista.get(index);
	}

	@Override
	public int getSize() {
		return lista.size();
	}

	@Override
	public Local getSelectedItem() {
	
		return this.local;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.local = (Local) anItem;
		fireContentsChanged(this.lista, 0, lista.size());
	}

	public List<Local> listaCategoria() {
		return lista;

	}
}