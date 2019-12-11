package com.stefanini.hackaton.parsers;

import com.stefanini.hackaton.dto.JogadorDto;
import com.stefanini.hackaton.entities.Jogador;

public class JogadorParserDTO extends AbstractParser<JogadorDto, Jogador> {

	@Override
	public JogadorDto toDTO(Jogador entity) {
		JogadorDto dto = new JogadorDto();
		dto.setId(entity.getId());
		dto.setNickname(entity.getNickname());
		dto.setSenha(entity.getSenha());
		dto.setPersonagem(entity.getPersonagem());
		return dto;
	}

	@Override
	public Jogador toEntity(JogadorDto dto) {
		Jogador entity = new Jogador();
//		entity.setId(dto.getId());
		entity.setNickname(dto.getNickname());
		entity.setSenha(dto.getSenha());
		entity.setPersonagem(dto.getPersonagem());
		return entity;
	}
	

}
