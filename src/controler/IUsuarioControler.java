package controler;

import java.util.List;

import entity.Usuario;

public interface IUsuarioControler {

	public void salvar(Usuario usuario);

	public void remover(Usuario usuario) ;

	public List<Usuario> listarUsuarios();

	public Usuario login (Usuario usuario);
		
	

}
