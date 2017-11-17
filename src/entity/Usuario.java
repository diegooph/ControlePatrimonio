package entity;

import java.util.List;

public class Usuario {
	private int idUsuario;
	private String nomeUsuario;
	private PermisaoEnum permisaoUsuario;
	private String username;
	private List<Requisicao>requisicoes;
	private String senha;
	
	public Usuario() {
		super();
		this.idUsuario = 0 ;
		this.permisaoUsuario = permisaoUsuario.USUARIO;
	}

	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public PermisaoEnum getPermisaoUsuario() {
		return permisaoUsuario;
	}

	public void setPermisaoUsuario(PermisaoEnum permisaoUsuario) {
		this.permisaoUsuario = permisaoUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

		
	

}
