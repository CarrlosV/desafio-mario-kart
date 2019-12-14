angular.module("mariokart").controller("RealizaCorridaController",
    RealizaCorridaController);

RealizaCorridaController.$inject = ['$scope', 'PersonagemService', '$window'];

function RealizaCorridaController($scope, PersonagemService, $window) {
    var vm = this;
    vm.service = PersonagemService;
    vm.personagens = [];
    vm.parametrosCorrida = {
        distancia: vm.distancia,
        curvas: vm.curvas,
        voltas: vm.voltas
    };
    
    vm.auxiliar = [];

    // vm.calculaGanhador = function(){
    //   vm.auxiliar.forEach(elemento => {
    //         //implementacao que não tive tempo de fazer
    //   })
    // };

    vm.calculaCorrida = function () {
        vm.service.getPersonagens().success(function (response) {
            vm.personagens = response;
            console.log(vm.personagens);

            //adiciona as voltas na corrida
            if ((vm.parametrosCorrida.voltas != null) && (vm.parametrosCorrida.voltas != undefined)){
                vm.parametrosCorrida.distancia = vm.parametrosCorrida.distancia * vm.parametrosCorrida.voltas;
                vm.parametrosCorrida.curvas = vm.parametrosCorrida.curvas * vm.parametrosCorrida.voltas;
            }

            let distanciaAntesDaCurva = vm.parametrosCorrida.distancia / vm.parametrosCorrida.curvas;

            //Calcula o desempenho de cada personagem
            for (let personagem of vm.personagens) {
                let distanciaAntesLinhaDeChegada = vm.parametrosCorrida.distancia;
                let velocidadeAtual = 0;
                let distanciaPercorridaSemRealizarCurvas = 0;
                let numeroDeIteracoesAteCompletarACorrida= 0;

                do {
                    //Aumenta a velocidade, Diminui a distanciaAntesLinhaChegada
                    if (velocidadeAtual <= personagem.velocidade){               
                        distanciaAntesLinhaDeChegada -= velocidadeAtual;
                        velocidadeAtual = velocidadeAtual + (personagem.aceleracao / 10);
                        distanciaPercorridaSemRealizarCurvas += velocidadeAtual;
                    }else {
                        distanciaAntesLinhaDeChegada -= velocidadeAtual;
                        velocidadeAtual = personagem.velocidade;
                        distanciaPercorridaSemRealizarCurvas += velocidadeAtual;
                    }

                    //Realiza a curva
                    if (distanciaPercorridaSemRealizarCurvas >= distanciaAntesDaCurva ){
                        //Calcula habilidade do personagem na curva
                        if (personagem.manobra > personagem.peso){
                            velocidadeAtual *= 0.7;
                        }else{
                            velocidadeAtual *= 0.5;
                        }
                        distanciaPercorridaSemRealizarCurvas=0;
                    }
                    numeroDeIteracoesAteCompletarACorrida++;
                }while (distanciaAntesLinhaDeChegada >= 0);
                vm.auxiliar.push({
                    nomeDoPersonagem: personagem.nome,
                    numeroDeIteracoesAteCompletarACorrida: numeroDeIteracoesAteCompletarACorrida
                })
            }
            console.log(vm.auxiliar[0]);
            //$window.location.href = '/mario-kart-desafio-final/corrida.html';
        }).error(function (response, status) {
            console.log(response);
            console.log(status);
        });
    }

}