package com.br.logginAutenticate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.logginAutenticate.model.Usuario;
import com.br.logginAutenticate.repository.UsuarioRepository;

import jakarta.validation.Valid;


@Controller
public class LoginController {
	
	@Autowired
	private UsuarioRepository ur;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/cadastro")
	public String cadastro() {
		return "cadastro";
	}
	
	@PostMapping("/cadastro")//Faz uma requisicao pegando as informacoes e gravando no BD
	public String cadastroUsuario(@Valid Usuario usuario, BindingResult result) {//Validandos os campos para garantir que nao serao nulos
		
		if(result.hasErrors()) {
			return "redirect:/cadastro";//retorna para tela cadastro do usuario sem nada ocorrer
		}
		
		ur.save(usuario);
		return "redirect:/login";// Redireciona para a página de login após o cadastro
	}

}
