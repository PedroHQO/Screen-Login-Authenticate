package com.br.logginAutenticate.repository;

import org.springframework.data.repository.CrudRepository;

import com.br.logginAutenticate.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{//interface utilizada pelo o JPA para forncer as funcoes CRUD
	
	Usuario findById(long id);
	
}
