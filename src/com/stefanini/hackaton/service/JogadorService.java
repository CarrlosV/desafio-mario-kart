package com.stefanini.hackaton.service;

import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.stefanini.hackaton.dto.AutenticarDTO;
import com.stefanini.hackaton.dto.CadastroDTO;
import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.entities.Jogador;
import com.stefanini.hackaton.parsers.CadastroParserDTO;
import com.stefanini.hackaton.parsers.JogadorParserDTO;
import com.stefanini.hackaton.parsers.PersonagemParserDTO;
import com.stefanini.hackaton.persistence.JogadorDAO;
import com.stefanini.hackaton.rest.exceptions.NegocioException;

@Transactional
public class JogadorService {

	@Inject
	JogadorParserDTO parser;
	@Inject
	PersonagemParserDTO parserPersonagem;

	@Inject
	CadastroParserDTO parserCadastro;

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

	public CadastroDTO inserir(CadastroDTO dto) throws NegocioException {
		List<Jogador> lista = jogadorDao.list();
		for (Jogador jogador : lista) {
			if (jogador.getNickname().equals(dto.getNickname())) {
				throw new NegocioException("O nickname ja existe!");
			}
		}

		validadorDeSenha(dto.getSenha());

		Jogador jogador = parserCadastro.toEntity(dto);
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

	public Boolean validadorDeSenha(String senha) throws NegocioException {

		String decriptador = new String(Base64.getDecoder().decode(senha));
		Boolean senhaValida = ((decriptador.length() > 8) || (decriptador.length() < 6));

		if (senhaValida) {
			throw new NegocioException("Senha invalida");
		} else {
			return senhaValida;
		}
	}

	public JogadorDTO autenticar(AutenticarDTO dto) throws NegocioException {
		for (Jogador jogador : jogadorDao.list()) {
			if (jogador.getNickname().equals(dto.getNickname())) {
				if (dto.getSenha().equals(jogador.getSenha())) {
					return parser.toDTO(jogador);
				}
			}
		}
		throw new NegocioException("Ops! Seu nickname ou senha estao incorretos!");

	}
}
