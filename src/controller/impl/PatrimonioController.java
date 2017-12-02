package controller.impl;

import java.util.List;

import controler.IPatrimonioControler;
import dao.PatrimonioDAO;
import entity.Patrimonio;
import entity.Requisicao;
import entity.StatusRequerimentoEnum;

public class PatrimonioController implements IPatrimonioControler {

	Patrimonio patrimonio = new Patrimonio();

	@Override
	public void salvar(Patrimonio patrimonio) {
		PatrimonioDAO udao = new PatrimonioDAO();
		udao.salvar(patrimonio);

	}
	@Override
	public void verificarDisponibilidade(Patrimonio patrimonio) throws Exception {
		PatrimonioDAO pdao = new PatrimonioDAO();
		patrimonio = pdao.buscarPatrimonioPorId(patrimonio);
		if (patrimonio.isOcupado()) {
			throw new Exception("Este patrimonio estã indisponivel no momento !");

		}

	}
	
	
	@Override
	public List<Patrimonio> listarMeusPatrimonios() {
		PatrimonioDAO udao = new PatrimonioDAO();
		return udao.listarMeusPatrimonios();
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