package entity;

import java.util.List;

public class Local {

	private int idLocal;
	private String NomeLocal;
	private Usuario usuarioGestor;
	private List<Requisicao> requisicoes;

	public Local() {
		super();
		idLocal = 0;
	}

	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}

	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	public String getNomeLocal() {
		return NomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		NomeLocal = nomeLocal;
	}

	public Usuario getUsuarioGestor() {
		return usuarioGestor;
	}

	public void setUsuarioGestor(Usuario usuarioGestor) {
		this.usuarioGestor = usuarioGestor;
	}

	@Override
	public String toString() {
		return NomeLocal ;
	}

}
