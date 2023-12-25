package com.crud.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.demo.dao.IUsuarioDao;
import com.crud.demo.entity.Usuario;


@Service
public class UsuarioServiceImpl implements IUsuarioService{

	
	@Autowired
	private IUsuarioDao UDao;
	
	@Override
	@Transactional (readOnly=true)
	public List<Usuario> listarTodo() {
		return (List<Usuario>) UDao.findAll();
	}

	@Override
	@Transactional
	public Usuario save(Usuario proveedores) {
		return UDao.save(proveedores);
	}

	@Override
	@Transactional (readOnly=true)
	public Usuario buscarPorID(Long id) {
		return UDao.findById(id).orElse(null);
	}

	@Override
	public void Eliminar(Long id) {
		UDao.deleteById(id);		
	}

}
