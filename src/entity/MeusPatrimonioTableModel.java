package entity;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.impl.PatrimonioController;

public class MeusPatrimonioTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Patrimonio> listaDePatrimonios;
	private String[] colunas = new String[]{"nome" , "Modelo" ,"Código", "Disponibilidade"};

	public MeusPatrimonioTableModel() {
		
		listarPatrimonios();
		
	}

	public void listarPatrimonios() {
		PatrimonioController pdao = new PatrimonioController();
		listaDePatrimonios = (ArrayList<Patrimonio>) pdao.listarPatrimonios();

	}

	@Override
	public int getColumnCount() {

		return colunas.length;
	}

	@Override
	public int getRowCount() {

		return listaDePatrimonios.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.listaDePatrimonios.get(rowIndex).getNomePatrimonio();

		case 1:
			return this.listaDePatrimonios.get(rowIndex).getCategoria().getModelo();

		case 2:
			return this.listaDePatrimonios.get(rowIndex).getCodigo();

		case 3:
		if (listaDePatrimonios.get(rowIndex).isOcupado()) {
			return "Ocupado";
		} else {
			return "Disponivel";
		}
			

		default:
			break;
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {

		return colunas[column];
	}
	
	public Patrimonio getPatrimonio(int index){
		return listaDePatrimonios.get(index);
	}
}
