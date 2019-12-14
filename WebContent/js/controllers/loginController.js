angular.module("mariokart").controller("LoginController",
		LoginController);

LoginController.$inject = ['$scope', 'LoginService'];


function LoginController($scope, LoginService) {
    var vm = this;
    vm.service = LoginService;
    vm.dadosRecebidos = {
        nickname: '',
        senha: ''
    }
    vm.validacaoDeLoginJogador = '';
    
    vm.validarJogador = function () {
    	vm.service.validaLogin(vm.dadosRecebidos).success(function (response) {
            console.log("Login realizado com sucesso");
            console.log(response);
        }).error(function (response, status) {
            vm.validacaoDeLoginJogador = response.mensagem;
            console.log(response);
            console.log(status);
        });
    }   
}