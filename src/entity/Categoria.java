package entity;

public class Categoria {
	private int idCategoria;
	private String modelo;
	private String descricao;

	
	
	@Override
	public String toString() {
		return modelo;
	}

	public Categoria() {
		super();
		idCategoria=0;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
