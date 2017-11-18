package controller.impl;


import java.util.List;

import controler.IRequisicaoControler;
import dao.RequisicaoDAO;
import entity.Local;
import entity.Patrimonio;
import entity.Requisicao;
import entity.Usuario;

public class RequisicaoController implements IRequisicaoControler {

	Requisicao requisicao = new Requisicao();

	@Override
	public void salvar(Usuario usuario, Patrimonio patrimonio, Requisicao requisicao,Local local) {
	RequisicaoDAO udao = new RequisicaoDAO();
	udao.salvar( usuario,  patrimonio, requisicao, local);
		
	}

	
	
	@Override
	public List<Requisicao> listarRequisicoes() {
	RequisicaoDAO udao = new RequisicaoDAO();
		return udao.listarTodos();
	}



	@Override
	public void remover(Usuario usuario, Patrimonio patrimonio, Requisicao requisicao,Local local) {
		RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
		requisicaoDAO.excluir(requisicao);

	}



	@Override
	public List<Requisicao> listarRequisicoesUsuario() {
		RequisicaoDAO udao = new RequisicaoDAO();
		return udao.listarTodos();
	}

}
