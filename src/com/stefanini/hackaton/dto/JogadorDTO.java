package com.stefanini.hackaton.dto;

import java.io.Serializable;

import com.stefanini.hackaton.entities.Personagem;

public class JogadorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nickname;
	private String senha;
	private PersonagemDto personagem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	
	public void setAtributosPersonagem(Personagem personagem) {
		this.personagem.setId(personagem.getId());
		this.personagem.setAceleracao(personagem.getAceleracao());
		this.personagem.setManobra(personagem.getManobra());
		this.personagem.setNome(personagem.getNome());
		this.personagem.setPeso(personagem.getPeso());
		this.personagem.setTracao(personagem.getTracao());
		this.personagem.setTurbo(personagem.getTurbo());
		this.personagem.setVelocidade(personagem.getVelocidade());
	}
	
}
