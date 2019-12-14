angular.module("mariokart").factory("CadastroService", function ($http) {
	var baseUrl = 'http://localhost:8080/mario-kart-desafio-final/jogador';
	var _cadastrarJogador = function (dadosParaCadastro) {
		return $http.post(baseUrl, { nickname: dadosParaCadastro.nickname, senha: btoa(dadosParaCadastro.senha), personagem: dadosParaCadastro.personagem });
	};

	return {
		cadastrarJogador: _cadastrarJogador
	};	
});