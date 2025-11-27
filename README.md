# Sistema de Gerenciamento de Produtos

Projeto para a disciplina de Linguagem de Programação II, ministrada pelo professor Alan de Oliveira Santana. 

Utilizando um sistema de gerenciamento de produtos como modelo, são aplicados conceitos avançados de Java, incluindo Herança, Polimorfismo, Genéricos, Interfaces, Anotações, Reflexão, Estruturas de Dados (Árvore AVL) e Algoritmos (MergeSort), além de tratamento de exceções e profiling de desempenho.

## Estrutura dos Pacotes

| Pacote | Conteúdo | Descrição |
| --------------- | :----- | :-------- |
| app | SistemaProdutos.java | Classe principal de execução e demonstração. |
| exception | NegocioException.java | Exceção customizada para regras de negócio (e.g., preço negativo). |
| model | InfoProd.java, Produto.java, ProdutoAlimenticio.java, ProdutoArtesanal.java | Classes de dados e a anotação customizada. |
| repository | Repositorio.java, RepositorioHash.java | Interface e implementação genérica para persistência em memória. |
| algorithm | Arvore.java, Ordenacao.java, AVLTree.java, MergeSort.java | Estruturas de dados e algoritmos de ordenação. |

## Como compilar e Executar

É possível compilar o programa, assumindo que o código está organizado em uma pasta src, seguindo a estrutura de pacotes:

1. Compilar todos os arquivos:

```bash
# A partir do diretório raiz do projeto (acima de 'src')
javac -d bin src/app/*.java src/exception/*.java src/model/*.java src/repository/*.java src/algorithm/*.java
```
2. Executar a Aplicação Principal:

```bash
# A partir do diretório raiz do projeto (acima de 'src')
java -cp bin app.SistemaProdutos
```
