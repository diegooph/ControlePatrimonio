package controler;

import java.util.List;

import entity.Local;

public interface ILocalControler {

	public void salvar(Local categoria);

	public void remover(Local categoria);

	public List<Local> listarLocais();

}
