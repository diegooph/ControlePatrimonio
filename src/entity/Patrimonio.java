package entity;

public class Patrimonio {
	private int idPatrimonio;
	private String nomePatrimonio;
	private String detalhamentoTecnico;
	private String codigo;
	private Categoria categoria;
	private boolean ocupado;

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Patrimonio() {
		super();
		this.idPatrimonio = 0;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getIdPatrimonio() {
		return idPatrimonio;
	}

	public void setIdPatrimonio(int idPatrimonio) {
		this.idPatrimonio = idPatrimonio;
	}

	public String getNomePatrimonio() {
		return nomePatrimonio;
	}

	public void setNomePatrimonio(String nomePatrimonio) {
		this.nomePatrimonio = nomePatrimonio;
	}

	public String getDetalhamentoTecnico() {
		return detalhamentoTecnico;
	}

	public void setDetalhamentoTecnico(String detalhamentoTecnico) {
		this.detalhamentoTecnico = detalhamentoTecnico;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
