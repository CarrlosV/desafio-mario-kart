package com.stefanini.hackaton.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.entities.Jogador;
import com.stefanini.hackaton.parsers.JogadorParserDTO;
import com.stefanini.hackaton.parsers.PersonagemParserDTO;
import com.stefanini.hackaton.persistence.JogadorDAO;

public class JogadorService {
	
	@Inject
	JogadorParserDTO parser;
	@Inject
	PersonagemParserDTO parserPersonagem;
	
	@Inject
	JogadorDAO jogadorDao;

	@Transactional
	public List<JogadorDTO> listar() {
		List<Jogador> jogadores = jogadorDao.list();
		return parser.toDTO(jogadores);
	}

	public JogadorDTO obter(Integer id) {
		return parser.toDTO(jogadorDao.findById(id));
	}
	
	public JogadorDTO inserir(JogadorDTO dto) {
		Jogador jogador = parser.toEntity(dto);
		jogadorDao.insert(jogador);
		return dto;
	}
	
	public JogadorDTO alterar(JogadorDTO dto) {
		Jogador jogador = jogadorDao.findById(dto.getId());
		jogador.setId(dto.getId());
		jogador.setNickname(dto.getNickname());
		jogador.setPersonagem(parserPersonagem.toEntity(dto.getPersonagem()));
		jogador.setSenha(dto.getSenha());
		jogadorDao.update(jogador);
		return dto;
	}

	public void excluir(Integer id) {
		jogadorDao.removeById(id);
	}

}
