package controller.impl;


import java.util.List;

import controler.IUsuarioControler;
import dao.UsuarioDAO;
import entity.Usuario;

public class UsuarioController implements IUsuarioControler {

	Usuario usuario = new Usuario();

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



	@Override
	public void remover(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);

	}



	@Override
	public Usuario login(Usuario usuario) {
		UsuarioDAO udao = new  UsuarioDAO();
		return udao.validarUsuario(usuario);
		
	}
	

}
