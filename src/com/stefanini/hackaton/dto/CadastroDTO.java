package com.stefanini.hackaton.dto;

import java.io.Serializable;

public class CadastroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nickname;
	private String senha;
	private PersonagemDto personagem;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PersonagemDto getPersonagem() {
		return personagem;
	}

	public void setPersonagem(PersonagemDto personagem) {
		this.personagem = personagem;
	}

}
