package com.crud.demo.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.crud.demo.entity.Usuario;
import com.crud.demo.services.IUsuarioService;
import com.crud.demo.services.S3Service;


@RestController  //http://localhost:8080/apis/listado -  http://localhost:8080/swagger-ui/index.html#/
@RequestMapping("/apis")
public class UsuarioController {
	@Autowired 
	private IUsuarioService Servi;
	
	@Autowired
	private S3Service s3Service;
	
	
	
	@GetMapping
	List<Usuario> getAll(){
		return Servi.listarTodo()
				.stream()
				.peek(usuario -> usuario.setFotoURL(s3Service.getObjectURL(usuario.getFotoPATH())))
				.collect(Collectors.toList());
	}
	
	// buscar por id
		@GetMapping("/listadoID/{id}")
		public Usuario show(@PathVariable Long id) {
			return Servi.buscarPorID(id);
		}

		//https://tipsparatuviaje.com/wp-content/uploads/2018/08/monte-fuji.jpg
		// guardar 
		@PostMapping
		Usuario create(@RequestBody Usuario usus) {
			Servi.save(usus);
			usus.setFotoURL(s3Service.getObjectURL(usus.getFotoPATH()));
			usus.setCedulaURL(s3Service.getObjectURL(usus.getCedulaPATH()));

			return usus;
			
		}

		// editar 

		@PutMapping("/editarUsuario/{id}")
		@ResponseStatus(HttpStatus.CREATED)
		public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id) {
	        Usuario uActual = Servi.buscarPorID(id);
	        uActual.setNombre(usuario.getNombre());
	        uActual.setEmail(usuario.getEmail());
	        uActual.setClave(usuario.getClave());
	        uActual.setEstado(usuario.getEstado());
	        return Servi.save(uActual);
	    }


		//eliminar
		@DeleteMapping("/eliminarID/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void Eliminar(@PathVariable("id") Long id) {
			Servi.Eliminar(id);
		}
		
		
		
	
}
