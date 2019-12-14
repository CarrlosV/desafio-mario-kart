angular.module("mariokart").controller("LoginController",
		LoginController);

LoginController.$inject = ['$scope', 'LoginService', '$location', '$window' ];


function LoginController($scope, LoginService, $location, $window) {
    var vm = this;
    vm.service = LoginService;
    vm.dadosRecebidos = {
        nickname: '',
        senha: ''
    }
    vm.validacaoDeLoginJogador = '';
    
    vm.validarJogador = function () {
    	vm.service.validaLogin(vm.dadosRecebidos).success(function (response) {
            console.log("Login realizado com sucesso!");
            console.log(response);
            console.log($window.location.href = '/mario-kart-desafio-final/parametros-corrida.html');
            $window.location.href = '/mario-kart-desafio-final/parametros-corrida.html';
        }).error(function (response, status) {
            vm.validacaoDeLoginJogador = response.mensagem;
            console.log("Login não realizado com sucesso!");
            console.log(response);
            console.log(status);
        });
    }   
}