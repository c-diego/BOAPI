# API de Gerenciamento de Boletins de Ocorrência - BOAPI
Este projeto é uma API REST para gerenciar o ciclo de vida de informações de boletins de ocorrência policial. A API é capaz de gerenciar dados de furtos de veículos, vítimas e demais informações pertinentes ao registro e ocorrências policiais.

## Funcionalidades

- Cadastro de boletim de ocorrência de furto de veículos
- Exclusão de boletins previamente cadastrados
- Alteração de boletins previamente cadastrados
- Listagem de boletins previamente cadastrados através dos seguintes filtros:
  - identificador do boletim de ocorrência
  - nome da cidade onde ocorreu o furto
  - período da ocorrência (Manhã, Tarde, Noite, Madrugada, etc)
- Listagem de veículos furtados através dos seguintes filtros:
  - placa
  - cor
  - tipo do veículo (Carro, Motocicleta, Caminhão, etc)

## Tecnologias utilizadas

- Java 17
- H2
- Spring Boot
- Spring Web

## Como executar o projeto

Antes de executar o projeto, é necessário ter o Java 17 e o Maven instalados em sua máquina. Em seguida, siga os passos abaixo:

1. Clone este repositório em sua máquina.
2. Navegue até o diretório raiz do projeto.
3. Execute o comando mvn spring-boot:run no terminal.
4. Acesse http://localhost:8080 em seu navegador para verificar se a API está funcionando corretamente.

## Documentação da API

A documentação da API pode ser encontrada em [link para a documentação da API].

## Autores

- [Diego Costa](https://github.com/c-diego)
- [Antonio Cristian Guedes](https://github.com/cris-guedes)

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](https://github.com/c-diego/BOAPI/blob/main/LICENSE) para mais detalhes.
