package com.br.logginAutenticate.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.br.logginAutenticate.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{//interface utilizada pelo o JPA para forncer as funcoes CRUD
	
	Usuario findById(long id);
	
	//select para o BD
	@Query(value="select * from logginautenticatea.usuario where email = :email and senha = :senha", nativeQuery = true)
	public Usuario login(String email, String senha);
	
}
