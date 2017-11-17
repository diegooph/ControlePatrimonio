package controler;

import java.util.List;

import entity.Categoria;

public interface ICategoriaControler {

	public void salvar(Categoria categoria);

	public void remover(Categoria categoria);

	public List<Categoria> listarCategorias();

}
