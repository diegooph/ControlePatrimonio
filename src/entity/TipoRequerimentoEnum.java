package entity;

public enum TipoRequerimentoEnum {

	RELATARPROBLEMA ("Relatar Problema",0),
	DEVOLUCAO ("Devolução",1),
	REQUERERPATRIMONIO ("Requerer Patrimonio",2);

	private final String descricao;
	private final int codigo;
	
	public String getDescricao() {
		return descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	private TipoRequerimentoEnum(String descricao, int codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}
	public static TipoRequerimentoEnum getTipoRequerimentoEnumByCodigo(int codigo){
		switch (codigo) {
		case 0:
			return TipoRequerimentoEnum.RELATARPROBLEMA;
		case 1:
			return TipoRequerimentoEnum.DEVOLUCAO;
		case 2:
			return TipoRequerimentoEnum.REQUERERPATRIMONIO;
		default:
			return null;
		}
	}


}
