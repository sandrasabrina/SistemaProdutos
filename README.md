# Sistema de Gerenciamento de Produtos

Projeto para a disciplina de Linguagem de Programação II, ministrada pelo professor Alan de Oliveira Santana. 

Utilizando um sistema de gerenciamento de produtos como modelo, são aplicados conceitos avançados de Java, incluindo Herança, Polimorfismo, Genéricos, Interfaces, Anotações, Reflexão, Estruturas de Dados (Árvore AVL) e Algoritmos (MergeSort), além de tratamento de exceções e profiling de desempenho.

## Estrutura dos Pacotes

| Pacote | Conteúdo | Descrição |
| --------------- | :----- | :-------- |
| app | SistemaProdutos.java | Classe principal de execução e demonstração. |
| exception | ExcecaoNegocio.java | Exceção customizada para regras de negócio (e.g., preço negativo). |
| model | InfoProd.java, Produto.java, ProdutoAlimenticio.java, ProdutoArtesanal.java | Classes de dados e a anotação customizada. |
| repository | Repositorio.java, RepositorioHash.java | Interface e implementação genérica para persistência em memória. |
| algorithm | ArvoreBusca.java, Ordenacao.java, ArvoreAVL.java, MergeSort.java | Estruturas de dados e algoritmos de ordenação. |

## Como compilar e executar

É possível compilar o programa seguindo a estrutura de pacotes:

1. Compilar todos os arquivos:

```bash
# A partir do diretório raiz do projeto
javac -d bin app/*.java exception/*.java model/*.java repository/*.java algorithm/*.java
```
2. Executar a Aplicação Principal:

```bash
# A partir do diretório raiz do projeto
java -cp bin app.SistemaProdutos
```
