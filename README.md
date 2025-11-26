# Sistema de Gerenciamento de Produtos Naturais (SGPN)

Este projeto demonstra a aplicação de conceitos avançados de Java, incluindo Herança, Polimorfismo, Genéricos, Interfaces, Anotações, Reflexão, Estruturas de Dados (Árvore AVL) e Algoritmos (MergeSort), além de tratamento de exceções e profiling de desempenho.

## Estrutura de Pacotes

| Pacote | Conteúdo | Descrição |
| --------------- | :-----| :-------- |
| app | SistemaProdutos.java | Classe principal de execução e demonstração. |
| exception | NegocioException.java | Exceção customizada para regras de negócio (e.g., preço negativo). |
| model | InfoProd.java, Produto.java, ProdutoAlimenticio.java, ProdutoArtesanal.java | Classes de dados e a anotação customizada. |
| repository | Repositorio.java, RepositorioHash.java | Interface e implementação genérica para persistência em memória. |
| algorithm | Arvore.java, Ordenacao.java, AVLTree.java, MergeSort.java | Estruturas de dados e algoritmos de ordenação. |

## Compilação e Execução

Assumindo que o código está organizado em uma pasta src seguindo a estrutura de pacotes:

1. Compilar todos os arquivos:

```bash
# A partir do diretório raiz do projeto (acima de 'src')
javac -d bin src/**/*.java
```
2. Executar a Aplicação Principal:

```bash
# A partir do diretório raiz do projeto (acima de 'src')
java -cp bin app.SistemaProdutos
```
