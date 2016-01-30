# BankSys03

Sistema bancário desenvolvido para a disciplina **Técnicas de Programação II** da **Universidade Federal do Ceará**.

BankSys simula as principais funções de um banco, tais como crédito, débito, transferência e checagem de saldo.

Esta versão do software está sendo desenvolvido por [Ismália Santiago](https://github.com/ismalia), do BetterAlone Team, com auxílio do Eclipse Mars 4.5.1.



##Features

###1. Testes unitários

Usando JUnit, todas as classes do BankSys foram testadas. O código se encontra na pasta test.

###2. Integração com o Travis CI ([![Build Status](https://travis-ci.org/TPII20152/BankSys03.svg?branch=master)](https://travis-ci.org/TPII20152/BankSys03))

O BankSys é testado para Oracle Java 7 e Oracle Java 8, usando Ant para fazer a build.

###3. Persistência em arquivo

Feita com a framework [XStream](http://x-stream.github.io) 1.4.8, convertendo e desconvertendo objetos em XML. `data.xml` é o arquivo com os dados.

###4. Arquivo de log

O sistema conta com um log persistente das operações bancárias, com os registros gravados em `log.txt`. A classe responsável pelo registro se encontra em `/src/banksys/logging/Logger.java`

###5. Interface de usuário

Feita em Java Swing e AWT com auxílio da ferramenta Eclipse WindowBuilder 1.8.0. A interface está em inglês.

![Login Interface](http://i.imgur.com/CX8lkCV.png)

![New Account Interface](http://i.imgur.com/VlDKp0i.png)

![Transactions Interface](http://i.imgur.com/cHUcbwb.png)



##Como executar?

Execute ATM24H.java
