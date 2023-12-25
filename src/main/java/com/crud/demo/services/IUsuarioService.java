package com.crud.demo.services;

import java.util.List;

import com.crud.demo.entity.Usuario;


public interface IUsuarioService {


public List<Usuario> listarTodo();
public Usuario  save(Usuario proveedores);
public  Usuario buscarPorID(Long id);
public void Eliminar(Long id);
	
}
