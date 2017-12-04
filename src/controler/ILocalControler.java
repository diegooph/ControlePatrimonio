package controler;

import java.util.List;

import entity.Local;
import entity.Requisicao;

public interface ILocalControler {

	public void salvar(Local categoria);

	public void remover(Local categoria);

	public List<Local> listarLocais();

	Local BuscarLocalPorRequisicao(Requisicao req);

}
