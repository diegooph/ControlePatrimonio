package entity;

public enum TipoRequerimentoEnum {

	
	DEVOLUCAO ("Devolução",0),
	REQUERERPATRIMONIO ("Requerer Patrimonio",1);

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
			return TipoRequerimentoEnum.DEVOLUCAO;			
		case 1:
			return TipoRequerimentoEnum.REQUERERPATRIMONIO;

		default:
			return null;
		}
	}


}
