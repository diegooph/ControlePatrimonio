package entity;

public enum PermisaoEnum {

	ADMIN 		("Administrador",0),
	USUARIO	 	("Usuario",1),
	VISITANTE	("Visitante",2);

	private final String descricao;
	private final int codigo;
	
	public String getDescricao() {
		return descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	private PermisaoEnum(String descricao, int codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}
	public static PermisaoEnum getPermisaoByCodigo(int codigo){
		switch (codigo) {
		case 0:
			return PermisaoEnum.ADMIN;
		case 1:
			return PermisaoEnum.USUARIO;
		case 2:
			return PermisaoEnum.VISITANTE;
		default:
			return null;
		}
	}
	

}
