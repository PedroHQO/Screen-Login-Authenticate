package com.br.logginAutenticate.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.logginAutenticate.model.Usuario;
import com.br.logginAutenticate.repository.UsuarioRepository;
import com.br.logginAutenticate.service.CookieService;
import com.br.logginAutenticate.service.EmailService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@Controller
public class LoginController {
	
	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private EmailService emailService; 
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String dashboard(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
		return "index";
	}
	
	
	
	@PostMapping("/logar")
	public String loginUsuario(@ModelAttribute Usuario usuario, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("Email: " + usuario.getEmail());//log email
		System.out.println("Senha: " + usuario.getSenha());//log senha
		
		
		Usuario userLogado = this.ur.login(usuario.getEmail(), usuario.getSenha());
		
		if(userLogado != null) {
			CookieService.setCookie(response, "usuarioId", String.valueOf(userLogado.getId()), 120000);
			CookieService.setCookie(response, "nomeUsuario", String.valueOf(userLogado.getNome()), 120000);

			return "redirect:/";
			
		}
		
		model.addAttribute("Erro", "Usuário ou senha incorretos!");
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
		
		
		//Enviar e-mail de confirmação de cadastro com mensagem
		String assunto = "CONFIRMAÇÃO DE CADASTRO";
		String corpo = "Olá " + usuario.getNome() + ", \n\nSeu cadastro foi realizado com sucesso no email: " + usuario.getEmail() + "!";
		emailService.enviarEmailConfirmacao(usuario.getEmail(), assunto, corpo);
		
		return "redirect:/login";// Redireciona para a página de login após o cadastro
	}

}
