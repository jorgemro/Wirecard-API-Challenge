# CODE CHALLENGE - WIRECARD

## OBJETIVO

Neste projeto foi implementado uma API REST processamento de pagamentos nas opções boleto e cartão de credito, 
conforme descrito no [code-challenge wirecard](https://github.com/wirecardBrasil/challenge/tree/master/backend).

## Tecnologias utilizadas

- [Java 8](https://www.java.com/pt_BR/download/faq/java8.xml)- Linguagem de programação
- [spring-boot](https://projects.spring.io/spring-boot/) (v2.1.8.RELEASE) - Framework para desenvolvimento de aplicações web
- [Maven](https://maven.apache.org/) (Apache Maven v3.6.1) - Ferramente de gerenciamento de build
- [H2](https://www.h2database.com/html/main.html) - Banco de dados em memória.

## Como executar localmente

A API requer instalação do Jdk8+ e Maven 3+ para execução.
Em seguida é necessário clonar ou baixar o  repositório deste projeto.

Para executar o projeto, utilize o comando do Maven no terminal ou pronpt de comando na raiz do seu projeto baixado do github:
```
$ mvn clean spring-boot:run
```
Quando o serviço estiver iniciado, será exibida uma mensagem parecida com essa:
```
Started PaymentApiChallengeApplication in 8.819 seconds (JVM running for 17.878)
```
Para parar a aplicação basta digitar ```CTRL + C```

## Testando os serviços
Para testar, utilize a collection do Postman,  ou os seguintes endpoints

[![Run in Postman](https://run.pstmn.io/button.svg)](https://www.getpostman.com/collections/2738af0382787e59b1ba)


Para processar transação: 
``` POST http://localhost:8080/restapi/payments```

Com boleto:

``` JSON 
{
	"client": {
		"clientId": 1
	},
	"buyer": {
		"name": "João da Silva",
		"email": "joao_silva@contoso.com",
		"cpf": "582.531.450-47"
	},
	"payment": {
		"amount": 100.00,
		"type": "Boleto"
	}
}
 ```

Com cartão de crédito:

``` JSON 
{
	"client": {
		"clientId": 1
	},
	"buyer": {
		"name": "Ana da Silva",
		"email": "ana_silva@contoso.com",
		"cpf": "718.999.420-33"
	},
	"payment": {
		"amount": 450.00,
		"type": "CreditCard",
		"card": {
			"holderName": "ANA SILVA",
			"cardNumber": "5362 3465 3953 4899",
			"expirationDate": "30/06/2020",
			"cardCVV": 569
		}
	}
}
```

Para consultar transação por ID:

``` GET http://localhost:8080/restapi/payments/1```
 
Para consultar todas as transações:

``` GET http://localhost:8080/restapi/payments```

### To-do
- Implementar testes unitários
- Incluir Logs 
- Disponibilizar via container(Docker)
