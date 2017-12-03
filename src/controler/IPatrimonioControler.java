package controler;

import java.util.List;

import entity.Local;
import entity.Patrimonio;

public interface IPatrimonioControler {

	public void salvar(Patrimonio patrimonio);

	public void remover(Patrimonio patrimonio);

	public List<Patrimonio> listarPatrimonios();

	List<Patrimonio> listarMeusPatrimonios();

	void verificarDisponibilidade(Patrimonio patrimonio) throws Exception;

	List<Patrimonio> listarPatrimoniosLocal(Local local);

}