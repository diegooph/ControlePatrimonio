package controller.impl;

import java.util.List;

import controler.ILocalControler;
import dao.LocalDAO;
import entity.Local;
import entity.Requisicao;

public class LocalController implements ILocalControler {

	Local local = new Local();

	@Override
	public void salvar(Local local) {
		LocalDAO ldao = new LocalDAO();
		ldao.salvar(local);

	}

	@Override
	public List<Local> listarLocais() {
		LocalDAO ldao = new LocalDAO();
		return ldao.listarTodos();
	}
	@Override
	public Local BuscarLocalPorRequisicao(Requisicao req) {
		LocalDAO ldao = new LocalDAO();
		return ldao.BuscarLocalPorRequisicao( req);
	}

	@Override
	public void remover(Local local) {
		LocalDAO localDAO = new LocalDAO();
		localDAO.excluir(local);

	}

}



