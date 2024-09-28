# poojavaext
Sistema de Cadastro feito para um projeto acadêmico

Este projeto é um sistema de cadastro de clientes, produtos e vendas desenvolvido em Java, utilizando orientação a objetos.

Estrutura do Projeto

Classe Cliente
java

id: Identificador único do cliente.

nome: Nome do cliente.

cpf: Cadastro de Pessoa Física do cliente.

email: Email do cliente.

dataNascimento: Data de nascimento do cliente.

conexaoMySQL: Conexão com o banco de dados MySQL.

Classe Produto

id: Identificador único do produto.

codigo: Código do produto.

descricao: Descrição do produto.

valorVenda: Valor de venda do produto.

quantidadeEstoque: Quantidade em estoque do produto.

conexaoMySQL: Conexão com o banco de dados MySQL.

Classe Venda

id: Identificador único da venda.

produtos: Lista de produtos envolvidos na venda.

cliente: Cliente que realizou a compra.

conexaoMySQL: Conexão com o banco de dados MySQL.

Como Usar
Clone este repositório.

Configure a conexão com o banco de dados MySQL.

Execute as classes conforme necessário.
