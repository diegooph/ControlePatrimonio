package controller.impl;

import java.util.List;

import controler.ICategoriaControler;
import dao.CategoriaDAO;
import entity.Categoria;

public class CategoriaController implements ICategoriaControler {

	Categoria categoria = new Categoria();

	@Override
	public void salvar(Categoria categoria) {
		CategoriaDAO udao = new CategoriaDAO();
		udao.salvar(categoria);

	}

	@Override
	public List<Categoria> listarCategorias() {
		CategoriaDAO udao = new CategoriaDAO();
		return udao.listarTodos();
	}

	@Override
	public void remover(Categoria categoria) {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		categoriaDAO.excluir(categoria);

	}

}
