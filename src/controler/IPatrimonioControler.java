package controler;

import java.util.List;

import entity.Patrimonio;

public interface IPatrimonioControler {

	public void salvar(Patrimonio patrimonio);

	public void remover(Patrimonio patrimonio);

	public List<Patrimonio> listarPatrimonios();

}