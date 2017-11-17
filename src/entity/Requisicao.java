package entity;

public class Requisicao {
	private int idRequisicao;
	private String titulo;
	private StatusRequerimentoEnum statusRequerimento;
	private Usuario usuarioRequerente;
	private Patrimonio patrimonio;
	private String mensagem;
	private TipoRequerimentoEnum tipoRequerimento;

	public Requisicao() {
		super();
		this.idRequisicao = 0;
	}

	public TipoRequerimentoEnum getTipoRequerimento() {
		return tipoRequerimento;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Patrimonio getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(Patrimonio patrimonio) {
		this.patrimonio = patrimonio;
	}

	public Usuario getUsuarioRequerente() {
		return usuarioRequerente;
	}

	public void setUsuarioRequerente(Usuario usuarioRequerente) {
		this.usuarioRequerente = usuarioRequerente;
	}

	public int getIdRequisicao() {
		return idRequisicao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public StatusRequerimentoEnum getStatusRequerimento() {
		return statusRequerimento;
	}

	public void setStatusRequerimento(StatusRequerimentoEnum statusRequerimento) {
		this.statusRequerimento = statusRequerimento;
	}

	public void setIdRequisicao(int idRequisicao) {
		this.idRequisicao = idRequisicao;
	}

	public StatusRequerimentoEnum getTipo() {
		return statusRequerimento;
	}

	public void setTipoRequerimento(TipoRequerimentoEnum tipoRequerimentoEnumByCodigo) {
		this.tipoRequerimento = tipoRequerimentoEnumByCodigo;

	}

}
