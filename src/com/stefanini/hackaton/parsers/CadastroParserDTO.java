package com.stefanini.hackaton.parsers;

import javax.inject.Inject;

import com.stefanini.hackaton.dto.CadastroDTO;
import com.stefanini.hackaton.entities.Jogador;

public class CadastroParserDTO extends AbstractParser<CadastroDTO, Jogador> {
	
	@Inject
	PersonagemParserDTO parser;

	@Override
	public CadastroDTO toDTO(Jogador entity) {
		System.out.println("Chamando o JogadorParserDTO");
		CadastroDTO dto = new CadastroDTO();
		dto.setNickname(entity.getNickname());
		dto.setSenha(entity.getSenha());
		dto.setPersonagem(parser.toDTO(entity.getPersonagem()));
		return dto;
		
	}

	@Override
	public Jogador toEntity(CadastroDTO dto) {
		Jogador entity = new Jogador();
		entity.setNickname(dto.getNickname());
		entity.setSenha(dto.getSenha());
		entity.setPersonagem(parser.toEntity(dto.getPersonagem()));
		return entity;
	}
	

}
