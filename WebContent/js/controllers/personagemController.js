angular.module("mariokart").controller("PersonagemController",
    PersonagemController);

PersonagemController.$inject = ['$scope', 'PersonagemService', 'CadastroService'];


function PersonagemController($scope, PersonagemService, CadastroService) {
    var vm = this;
    vm.service = PersonagemService;
    vm.cadastro = CadastroService;
    vm.personagens = [];
    vm.dadosCadastrado = {
        nickname: '',
        senha: '',
        personagem: {
            id: 1
        }
    }
    vm.validacaoDeCadastroJogador = '';
    
    
    vm.init = function () {
        console.log("init");
        vm.carregarPersonagens();
    };

    vm.carregarPersonagens = function () {
        vm.service.getPersonagens().success(function (data) {
            vm.personagens = data;
            console.log(vm.personagens);
        }).error(function (data, status) {
            vm.message = "Aconteceu um problema: " + data;
        });
    };

    vm.fazerCadastro = function () {
    	vm.cadastro.cadastrarJogador(vm.dadosCadastrado).success(function (response) {
            console.log("cadastro realizado com sucesso");
            console.log(response);
        }).error(function (response, status) {
            vm.validacaoDeCadastroJogador = response.mensagem;
            console.log(response);
            console.log(status);
        });
    }
    
    
}