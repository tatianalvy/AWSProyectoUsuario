package com.crud.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.crud.demo.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

}
