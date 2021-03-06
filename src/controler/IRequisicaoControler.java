package controler;

import java.util.List;

import entity.Local;
import entity.Patrimonio;
import entity.Requisicao;
import entity.Usuario;

public interface IRequisicaoControler {

	public void salvar( Patrimonio patrimonio, Requisicao requisicao,Local local) throws Exception;

	public void remover( Patrimonio patrimonio, Requisicao requisicao,Local local);

	public List<Requisicao> listarRequisicoes(boolean inderefido, boolean pendente, boolean deferido, boolean devoluo , boolean requererPatrimonio);

	public List<Requisicao> listarRequisicoesUsuario(boolean inderefido, boolean pendente, boolean deferido, boolean devoluo , boolean requererPatrimonio);
}
