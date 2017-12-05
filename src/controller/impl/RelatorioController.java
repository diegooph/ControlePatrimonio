package controller.impl;

import java.util.Date;
import java.util.List;

import controler.IRelatorioControler;
import dao.RelatorioDAO;
import entity.Relatorio;

public class RelatorioController implements IRelatorioControler {


	@Override
	public List<Relatorio> listarRelatorios(Date datainicio, Date datafim) {
		RelatorioDAO udao = new RelatorioDAO();
		return udao.listarRelatorio(datainicio, datafim);
	}
	
}
