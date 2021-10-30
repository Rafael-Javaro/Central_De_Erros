
<!-- PROJECT LOGO -->
<br />
<p align="center">
<!--
  <a href="https://github.com/Rafael-Javaro/Central_De_Erros">
    <img src="src/assets/logo.gif" alt="Logo" >
  </a>
-->
  <h1 align="center">Central de Erros :: Backend</h1>

  <p align="center">
    Repositório de Backend para o desafio final realizado para a aceleração Java Spring Boot, oferecida pela Trybe em parceria com a empresa CI&T.
    <br />
  </p>
  <p align="center">
    <a href="https://www.betrybe.com/" target="_blank" rel="noreferrer">
      <img src="readme-img/trybe.jpg" alt="Trybe" width="50px">
    </a>
    <img src="readme-img/spacer.gif" aria-hidden="true" alt="" height="50px" width="60px">
    <a href="https://ciandt.com/br/pt-br" target="_blank" rel="noreferrer">
      <img src="readme-img/ciandt.png" alt="Trybe" width="110px">
    </a>
  </p>
  
  <p align="center">
    <a href="https://ancient-ridge-40479.herokuapp.com/user" target="_blank">Ver Online</a>
    ·
    <a href="https://github.com/Rafael-Javaro/Central_De_Erros/issues">Reportar Bug</a>
    ·
    <a href="https://github.com/Rafael-Javaro/Central_De_Erros/issues">Sugerir Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Sumário</summary>
  <ol>
    <li>
      <a href="#sobre-o-projeto">Sobre o Projeto</a>
    </li>
    <li>
      <a href="#tecnologias-utilizadas">Tecnologias utilizadas</a></li>
    </li>
    <li>
      <a href="#rodando-o-projeto">Rodando o projeto</a>
    </li>
    <li><a href="#desenvolvedores">Desenvolvedores</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## Sobre o Projeto

Central de Erros foi um projeto desenvolvido durante as três últimas semanas (Out11 - Nov01  2021) da Aceleração Java Spring Boot, oferecida pela Trybe em parceria com a empresa CI&T.

# Central de Erros

## Objetivo
Em projetos modernos é cada vez mais comum o uso de arquiteturas baseadas em serviços ou microsserviços. Nestes ambientes complexos, erros podem surgir em diferentes camadas da aplicação (backend, frontend, mobile, desktop) e mesmo em serviços distintos. Desta forma, é muito importante que os desenvolvedores possam centralizar todos os registros de erros em um local, de onde podem monitorar e tomar decisões mais acertadas. Neste projeto vamos implementar uma API Rest para centralizar registros de erros de aplicações.

Abaixo estão os requisitos desta API, o time terá total liberdade para tomar as decisões técnicas e de arquitetura da API, desde que atendam os requisitos abaixo.

## API

### Tecnologia
- Utilizar a tecnologia base da aceleração para o desenvolvimento (Exemplo: Java, Node.js)

### Premissas
- A API deve ser pensada para atender diretamente um front-end
- Deve ser capaz de gravar os logs de erro em um banco de dados relacional
- O acesso a ela deve ser permitido apenas por requisições que utilizem um token de acesso válido

### Funcionalidades
- Deve permitir a autenticação do sistema que deseja utilizar a API gerando o Token de Acesso
- Pode ser acessado por multiplos sistemas
- Deve permitir gravar registros de eventos de log salvando informações de **Level(error, warning, info), Descrição do Evento, LOG do Evento, ORIGEM(Sistema ou Serviço que originou o evento), DATA(Data do evento), QUANTIDADE(Quantidade de Eventos de mesmo tipo)**
- Deve permitir a listagem dos eventos juntamente com a filtragem de eventos por qualquer parâmetro especificado acima
- Deve suportar Paginação
- Deve suportar Ordenação por diferentes tipos de atributos
- A consulta de listagem não deve retornar os LOGs dos Eventos
- Deve permitir a busca de um evento por um ID, dessa maneira exibindo o LOG desse evento em específico

Nesse repositório está o Backend da aplicação. Veja também o repositório do [Frontend](https://github.com/henriquebelias/Central-De-Erros-Front) da Central de Erros.

# Tecnologias utilizadas

Esse projeto foi desenvolvido com:

* Linguagem base: [Java 8](https://www.java.com/en/)
* Framework backend: [Spring Boot](https://spring.io/projects/spring-boot)
* Autenticação: [Spring Security](https://spring.io/projects/spring-security) e [Oauth2](https://oauth.net/2/)
* Banco de dados: [PostgreSQL](https://www.postgresql.org/)
* Documentação: [Swagger](https://swagger.io/)
* Deploy: [Heroku](https://www.heroku.com/) e [Docker](https://www.docker.com)


<!-- GETTING STARTED -->
# Rodando o projeto

Para rodar o Backend do projeto na sua máquina, você deverá clonar o repositório e executar o aqruivo jar:

1. Clone o Frontend
   ```sh
   git clone git@github.com:Rafael-Javaro/Central_De_Erros.git
   ```
2. Entre na pasta e instale as dependências
   ```sh
   cd Central_De_Erros
   ```
3. Empacote a aplicação:
   ```sh
   mvn clean
   mvn package
   ```
4. Entre na pasta target:
   ```sh
   cd target
   ```
5. Rode a aplicação:
   ```sh
   java -jar Central-de-Erros-0.0.1-SNAPSHOT.jar 

   ```
### Instalando pelo Docker
1. Baixe a imagem
```sh
  docker pull ricardorosadev/central-de-erros
```
2. Rode a imagem pelo Docker
```sh
  docker run -d -p 8080:8080 ricardorosadev/central-de-erros:latest
```

O Frontend desse projeto pode ser acessado no github: [https://github.com/henriquebelias/Central-De-Erros-Front](https://github.com/henriquebelias/Central-De-Erros-Front)

## Endpoints

- **/user**: Acessa os usuários do sistema de central de erros
- **/error-log**: Acessa os Logs de erro

O projeto foi documentado através do Swagger.
Todas as rotas, métodos e modelos das entidades podem ser vistos com detalhes na [documentação](https://ancient-ridge-40479.herokuapp.com/swagger-ui.html).

<!-- CONTACT -->
## Desenvolvedores

- [Edison Pires](https://www.linkedin.com/in/edisonps/)
- [Felipe Cardoso](https://www.linkedin.com/in/felipe-de-carvalho-lopes-cardoso/)
- [Henrique Elias](https://www.linkedin.com/in/henrique-brito-elias/)
- [Rafael Javaro](https://www.linkedin.com/in/rafael-javaro/)
- [Ricardo Rosa](https://www.linkedin.com/in/ricardorosa-dev/)
- [Thais Quintela](https://www.linkedin.com/in/thais-quintela/)
- [Vagner Pereira](https://www.linkedin.com/in/vagnerpsousa/)
- [renÁtomo pataquÁ](https://www.linkedin.com/in/renato-pereira-feitosa/)




