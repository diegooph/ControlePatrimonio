package controller.impl;

import java.util.List;

import controler.IUsuarioControler;
import dao.UsuarioDAO;
import entity.Usuario;

public class UsuarioController implements IUsuarioControler {

	private static Usuario usuario = new Usuario();

	@Override
	public void salvar(Usuario usuario) {
		UsuarioDAO udao = new UsuarioDAO();
		udao.salvar(usuario);

	}

	@Override
	public List<Usuario> listarUsuarios() {
		UsuarioDAO udao = new UsuarioDAO();
		return udao.listarTodos();
	}
	public void verificarExclusao(Usuario usuario) throws Exception{
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarexcluir(usuario)){
			System.out.println("deu erro no verifExcluir");
			throw new Exception("Este Usuario J� Possui Registros no Sistema \n Impossivel ser Removido");
			
		}
				
				
				
		
	}
	@Override
	public void remover(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
			usuarioDAO.excluir(usuario);
			
	}

	@Override
	public Usuario login(Usuario usuario) {
		UsuarioDAO udao = new UsuarioDAO();

		usuario = udao.validarUsuario(usuario);
		if (usuario.getIdUsuario() != 0) {
			this.usuario = usuario;
		}
		return usuario;

	}

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		UsuarioController.usuario = usuario;
	}

}
