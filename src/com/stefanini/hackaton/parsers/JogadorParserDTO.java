package com.stefanini.hackaton.parsers;

import javax.inject.Inject;

import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.entities.Jogador;

public class JogadorParserDTO extends AbstractParser<JogadorDTO, Jogador> {
	
	@Inject
	PersonagemParserDTO parser;

	@Override
	public JogadorDTO toDTO(Jogador entity) {
		System.out.println("Chamando o JogadorParserDTO");
		JogadorDTO dto = new JogadorDTO();
		dto.setId(entity.getId());
		dto.setNickname(entity.getNickname());
		dto.setSenha(entity.getSenha());
		dto.setPersonagem(parser.toDTO(entity.getPersonagem()));
		return dto;
		
	}

	@Override
	public Jogador toEntity(JogadorDTO dto) {
		Jogador entity = new Jogador();
		entity.setId(dto.getId());
		entity.setNickname(dto.getNickname());
		entity.setSenha(dto.getSenha());
		entity.setPersonagem(parser.toEntity(dto.getPersonagem()));
		return entity;
	}
	

}
