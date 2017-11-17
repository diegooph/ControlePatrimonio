package controller.impl;


import java.util.List;

import controler.IPatrimonioControler;
import dao.PatrimonioDAO;
import entity.Patrimonio;

public class PatrimonioController implements IPatrimonioControler {

	Patrimonio patrimonio = new Patrimonio();

	@Override
	public void salvar(Patrimonio patrimonio) {
	PatrimonioDAO udao = new PatrimonioDAO();
	udao.salvar(patrimonio);
		
	}

	
	
	@Override
	public List<Patrimonio> listarPatrimonios() {
	PatrimonioDAO udao = new PatrimonioDAO();
		return udao.listarTodos();
	}



	@Override
	public void remover(Patrimonio patrimonio) {
		PatrimonioDAO patrimonioDAO = new PatrimonioDAO();
		patrimonioDAO.excluir(patrimonio);

	}

}