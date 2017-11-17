package controler;

import java.util.List;

import entity.Local;
import entity.Patrimonio;
import entity.Requisicao;
import entity.Usuario;

public interface IRequisicaoControler {

	public void salvar(Usuario usuario, Patrimonio patrimonio, Requisicao requisicao,Local local);

	public void remover(Usuario usuario, Patrimonio patrimonio, Requisicao requisicao,Local local);

	public List<Requisicao> listarRequisicoes();

}
