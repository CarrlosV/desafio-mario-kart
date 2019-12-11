package com.stefanini.hackaton.service;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackaton.dto.JogadorDto;
import com.stefanini.hackaton.entities.Jogador;
import com.stefanini.hackaton.parsers.JogadorParserDTO;
import com.stefanini.hackaton.persistence.JogadorDAO;

public class JogadorService {
	
	@Inject
	JogadorParserDTO parser;
	
	@Inject
	JogadorDAO jogadorDao;


	public List<JogadorDto> listar() {
		return parser.toDTO(jogadorDao.list());
	}

	public JogadorDto obter(Integer id) {
		return parser.toDTO(jogadorDao.findById(id));
	}
	
	public JogadorDto inserir(JogadorDto dto) {
		Jogador jogador = parser.toEntity(dto);
		jogadorDao.insert(jogador);
		return dto;
	}
	
	public JogadorDto alterar(JogadorDto dto) {
		Jogador jogador = jogadorDao.findById(dto.getId());
		jogador.setId(dto.getId());
		jogador.setNickname(dto.getNickname());
		jogador.setPersonagem(dto.getPersonagem());
		jogador.setSenha(dto.getSenha());
		jogadorDao.update(jogador);
		return dto;
	}

	public void excluir(Integer id) {
		jogadorDao.removeById(id);
	}

}
