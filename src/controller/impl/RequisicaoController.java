package controller.impl;

import java.util.List;
import controler.IRequisicaoControler;
import dao.RequisicaoDAO;
import entity.Local;
import entity.Patrimonio;
import entity.Requisicao;
import entity.StatusRequerimentoEnum;
import entity.TipoRequerimentoEnum;
import entity.Usuario;

public class RequisicaoController implements IRequisicaoControler {



	public void verificarEdicao(Requisicao requisicao) throws Exception {
		if (requisicao.getStatusRequerimento() == StatusRequerimentoEnum.INDEFERIDO || requisicao.getStatusRequerimento() == StatusRequerimentoEnum.DEFERIDO) {
			throw new Exception("Apenas requisições pendentes podem ser alteradas!");

		}

	}

	@Override
	public void salvar( Patrimonio patrimonio, Requisicao requisicao, Local local) {

		RequisicaoDAO udao = new RequisicaoDAO();
		udao.salvar( patrimonio, requisicao, local);

	}

	@Override
	public List<Requisicao> listarRequisicoes(boolean inderefido, boolean pendente, boolean deferido, boolean devoluo,boolean requererPatrimonio) {
		RequisicaoDAO udao = new RequisicaoDAO();
		return udao.listarTodos(inderefido, pendente, deferido, devoluo,  requererPatrimonio);
	}

	@Override
	public void remover( Patrimonio patrimonio, Requisicao requisicao, Local local) {
		RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
		requisicaoDAO.excluir(requisicao);

	}

	@Override
	public List<Requisicao> listarRequisicoesUsuario(boolean inderefido, boolean pendente, boolean deferido,
			boolean devoluo,  boolean requererPatrimonio) {
		RequisicaoDAO udao = new RequisicaoDAO();
		return udao.listarRequisicoesUsuarios(inderefido, pendente, deferido, devoluo,
				requererPatrimonio);
	}

}
