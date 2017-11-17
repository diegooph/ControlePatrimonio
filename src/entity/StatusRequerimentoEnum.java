package entity;

public enum StatusRequerimentoEnum {

	DEFERIDO ("Deferido",0),
	INDEFERIDO ("Indeferido",1),
	PENDENTE ("Pendente",2);

	private final String descricao;
	private final int codigo;
	
	public String getDescricao() {
		return descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	private StatusRequerimentoEnum(String descricao, int codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}
	public static StatusRequerimentoEnum getStatusRequerimentoEnumByCodigo(int codigo){
		switch (codigo) {
		case 0:
			return StatusRequerimentoEnum.DEFERIDO;
		case 1:
			return StatusRequerimentoEnum.INDEFERIDO;
		case 2:
			return StatusRequerimentoEnum.PENDENTE;
		default:
			return null;
		}
	}

}
