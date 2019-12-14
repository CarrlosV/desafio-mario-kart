angular.module("mariokart").factory("LoginService", function ($http) {
	var baseUrl = 'http://localhost:8080/mario-kart-desafio-final/jogador/login';
	var _validaLogin = function (dadosParaValidar) {
		return $http.post(baseUrl, { nickname: dadosParaValidar.nickname, senha: btoa(dadosParaValidar.senha)});
	};

	return {
		validaLogin: _validaLogin
	};	
});