# Desafio Desenvolvedor

Para rodar este projeto, faça o download, acesse a pasta raiz e execute:
```sh
mvn clean
mvn test
mvn spring-boot:run
```

## Acesso aos serviços
### Resgatar os dados para uma rota considerando evitar trafego
`http://localhost:8080/route/[{"streetName":"Rua Marquês de valença","number":"369","city":"Sao Paulo","state":"Sao Paulo"},{"streetName":"Rua Antonio Rodrigues Azenha","number":"120","city":"Sumare","state":"Sao Paulo"}]?avoidTraffic=true`

### Resgatar os dados para uma rota não considerando evitar trafego
`http://localhost:8080/route/[{"streetName":"Rua Marquês de valença","number":"369","city":"Sao Paulo","state":"Sao Paulo"},{"streetName":"Rua Antonio Rodrigues Azenha","number":"120","city":"Sumare","state":"Sao Paulo"}]?avoidTraffic=false`

### Resgatar os dados para uma rota forçando um custo por km rodado e evitando trafego, o valor definido como default para o custo km foi de 1.09
`http://localhost:8080/route/[{"streetName":"Rua Marquês de valença","number":"369","city":"Sao Paulo","state":"Sao Paulo"},{"streetName":"Rua Antonio Rodrigues Azenha","number":"120","city":"Sumare","state":"Sao Paulo"}]?kmCost=0.45&avoidTraffic=true`

### Para realizar os testes foi utilizado Postman
