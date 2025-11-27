package app;

import algoritmo.ArvoreAVL;
import algoritmo.ArvoreBusca;
import algoritmo.MergeSort;
import algoritmo.Ordenador;
import excecao.ExcecaoNegocio;
import modelo.InfoProd;
import modelo.Produto;
import modelo.ProdutoAlimenticio;
import modelo.ProdutoArtesanal;
import repositorio.RepositorioHash;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Classe principal do Sistema de Gerenciamento de Produtos Naturais.
 * Demonstra as diversas funcionalidades do sistema, integrando cadastro,
 * busca, listagem, ordenação e análise de desempenho com diversos componentes.
 */
public class SistemaProdutos {

    // Repositório de produtos, utiliza HashMap para indexação por ID.
    private final RepositorioHash<Produto> repositorioProdutos = new RepositorioHash<>(Produto::getId);

    // Índice adicional utilizando Árvore AVL (binária balanceada) para indexação rápida e ordenada por nome.
    private final ArvoreBusca<String, Produto> indicePorNome = new ArvoreAVL<>();

    // Instância do algoritmo de ordenação. Aqui usamos o MergeSort.
    private final Ordenador<Produto> ordenador = new MergeSort<>();

    /**
     * Método main: ponto de entrada do sistema.
     * Executa diversas demonstrações: anotação, regras de negócio, cadastro, CRUD,
     * uso de árvore AVL e análise de desempenho da ordenação.
     */
    public static void main(String[] args) {
        SistemaProdutos sistema = new SistemaProdutos();

        // 1. Exibe informações de anotações (@InfoProd) e regras de negócio (validação).
        sistema.exibirAnotacoes();
        sistema.exibirRegrasDeNegocio();

        // 2. Cadastra produtos fictícios para uso nos exemplos.
        sistema.cadastrarProdutosTeste();

        // 3. Demonstra operações CRUD (criar, ler, remover) no repositório hash.
        sistema.demonstrarCrud();

        // 4. Demonstra buscas e listagem ordenada usando a estrutura AVL.
        sistema.demonstrarArvoreAvl();

        // 5. Demonstra as ordenações usando MergeSort, com análise de desempenho.
        sistema.demonstrarOrdenacaoEProfiling();
    }

    /**
     * Cadastra um produto no repositório principal e também o insere no índice por nome (AVL).
     * @param produto Produto a ser cadastrado
     */
    private void cadastrarProduto(Produto produto) {
        repositorioProdutos.cadastrar(produto);
        indicePorNome.inserir(produto.getNome(), produto);
    }

    /**
     * Cria e cadastra produtos de teste de diferentes tipos (alimentício, artesanal).
     * Método usado para povoar o sistema para demonstrações.
     */
    private void cadastrarProdutosTeste() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 2. CADASTRO DE PRODUTOS PARA TESTE (Hash e AVL)");
        System.out.println("##########################################################");
        try {
            // Instancia produtos diversos
            Produto produto1 = new ProdutoAlimenticio("A001", "Mel Orgânico", 25.50, "Alimentício", 50, "Fazenda Pura", "2026-01-01");
            Produto produto2 = new ProdutoArtesanal("B002", "Sabonete Natural Lavanda", 12.00, "Higiene", 120, "Ateliê da Terra", "Óleos Essenciais");
            Produto produto3 = new ProdutoAlimenticio("C003", "Café Gourmet Moído", 45.90, "Alimentício", 80, "Sítio do Vovô", "2025-12-31");
            Produto produto4 = new ProdutoAlimenticio("A004", "Geleia de Morango", 18.75, "Alimentício", 60, "Fazenda Pura", "2026-03-01");
            Produto produto5 = new ProdutoArtesanal("B005", "Vaso de Cerâmica", 60.00, "Decoração", 15, "Barro Arte", "Argila");

            // Cadastra cada produto nas estruturas do sistema
            cadastrarProduto(produto1);
            cadastrarProduto(produto2);
            cadastrarProduto(produto3);
            cadastrarProduto(produto4);
            cadastrarProduto(produto5);

            System.out.println("\n5 produtos cadastrados com sucesso no repositório (Hash) e índice (AVL).");
        } catch (ExcecaoNegocio e) {
            // Caso regras de negócio não sejam satisfeitas
            System.err.println("Erro ao cadastrar produtos de teste: " + e.getMessage());
        }
    }

    /**
     * Demonstra as regras de negócio para cadastro de produtos,
     * forçando erros previsíveis para validar as exceções.
     */
    private void exibirRegrasDeNegocio() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 1. DEMONSTRAÇÃO DE REGRAS DE NEGÓCIO (ExcecaoNegocio)");
        System.out.println("##########################################################");

        // Produto com preço inválido (negativo)
        try {
            new ProdutoAlimenticio("X001", "Produto Teste Preço", -5.00, "Teste", 10, "Local", "2025-01-01");
        } catch (ExcecaoNegocio e) {
            System.err.println("ERRO ESPERADO (Preço inválido): " + e.getMessage());
        }

        // Produto com estoque inválido (negativo)
        try {
            new ProdutoAlimenticio("X002", "Produto Teste Estoque", 10.00, "Teste", -1, "Local", "2025-01-01");
        } catch (ExcecaoNegocio e) {
            System.err.println("ERRO ESPERADO (Estoque inválido): " + e.getMessage());
        }
    }

    /**
     * Demonstra como ler informações da anotação @InfoProd nas classes,
     * além de buscar um método via reflexão.
     */
    private void exibirAnotacoes() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 1. LEITURA DA ANOTAÇÃO @InfoProd (Reflexão)");
        System.out.println("##########################################################");

        // Leitura da anotação presente na classe Produto
        InfoProd infoProduto = Produto.class.getAnnotation(InfoProd.class);
        if (infoProduto != null) {
            System.out.println("Anotação na classe Produto:");
            System.out.println("  Versão: " + infoProduto.versao());
            System.out.println("  Categoria padrão: " + infoProduto.categoria());
        }

        // Leitura da anotação presente na classe RepositorioHash
        InfoProd infoRepositorio = RepositorioHash.class.getAnnotation(InfoProd.class);
        if (infoRepositorio != null) {
            System.out.println("\nAnotação na classe RepositorioHash:");
            System.out.println("  Versão: " + infoRepositorio.versao());
            System.out.println("  Categoria padrão: " + infoRepositorio.categoria());
        }

        // Busca do método 'listarTodos' via reflexão para demonstrar introspecção de tipo
        try {
            Method metodoListarTodos = RepositorioHash.class.getMethod("listarTodos");
            System.out.println("\nMétodo 'listarTodos' encontrado com sucesso em RepositorioHash via reflexão.");
        } catch (NoSuchMethodException e) {
            System.err.println("Erro: Método 'listarTodos' não encontrado em RepositorioHash.");
        }
    }

    /**
     * Realiza e demonstra operações básicas de CRUD:
     * - Consulta de um produto pelo ID
     * - Listagem de todos os produtos
     * - Remoção de produto
     * - Nova listagem para conferir a alteração
     */
    private void demonstrarCrud() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 3. DEMONSTRAÇÃO DE CRUD (REPOSITÓRIO HASH)");
        System.out.println("##########################################################");

        // Busca de produto pelo ID
        Produto produtoBuscado = repositorioProdutos.buscar("C003");
        System.out.println("\n[CONSULTA] ID C003: " + (produtoBuscado != null ? produtoBuscado.getNome() : "Não encontrado"));

        // Listagem de todos os produtos (desordenados)
        System.out.println("\n[LISTAGEM DE TODOS (HashMap)]: ");
        repositorioProdutos.listarTodos().forEach(System.out::println);

        // Remoção de um produto específico
        repositorioProdutos.remover("A004");
        System.out.println("\n[REMOÇÃO] Produto de ID A004 removido.");

        // Listagem após remoção para conferência
        System.out.println("\n[LISTAGEM APÓS REMOÇÃO]:");
        repositorioProdutos.listarTodos().forEach(System.out::println);
    }

    /**
     * Demonstra as operações ligadas à árvore AVL:
     * - Busca de produto pelo nome
     * - Listagem ordenada (em ordem) dos produtos
     */
    private void demonstrarArvoreAvl() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 4. DEMONSTRAÇÃO DA ÁRVORE AVL (ÍNDICE POR NOME)");
        System.out.println("##########################################################");

        // Busca do produto pelo nome na árvore AVL (ordenada)
        Produto produtoEncontrado = indicePorNome.buscar("Mel Orgânico");
        System.out.println("\n[BUSCA AVL] Nome 'Mel Orgânico': " + (produtoEncontrado != null ? produtoEncontrado.getId() : "Não encontrado"));

        // Listagem em ordem dos produtos (in-order traversal)
        System.out.println("\n[IMPRESSÃO EM ORDEM (Ordenado por nome - AVL)]: ");
        List<Produto> produtosOrdenados = indicePorNome.listarEmOrdem();
        produtosOrdenados.forEach(produto -> System.out.println(" - " + produto.getNome() + " (ID: " + produto.getId() + ")"));
    }

    /**
     * Demonstra ordenações usando MergeSort e imprime o tempo gasto em cada estratégia de comparação:
     * - Por nome do produto
     * - Por preço do produto
     * - Por categoria do produto
     * Também compara os tempos e destaca a eficiência da travessia in-order da AVL.
     */
    private void demonstrarOrdenacaoEProfiling() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 5. DEMONSTRAÇÃO DE ORDENAÇÃO E PROFILING (MergeSort)");
        System.out.println("##########################################################");

        List<Produto> listaProdutos = repositorioProdutos.listarTodos();

        // Ordenação por nome (alfabética)
        Comparator<Produto> comparadorPorNome = Comparator.comparing(Produto::getNome);
        long tempoNome = ordenador.ordenar(listaProdutos, comparadorPorNome);

        System.out.println("\n[ORDENAÇÃO POR NOME (MergeSort)]: ");
        listaProdutos.forEach(produto -> System.out.println(" - " + produto.getNome()));

        long tempoMsNome = TimeUnit.NANOSECONDS.toMillis(tempoNome);
        System.out.println("\n[TEMPO DE ORDENAÇÃO POR NOME] " + tempoMsNome + " ms.");

        // Ordenação por preço (crescente)
        listaProdutos = repositorioProdutos.listarTodos();
        Comparator<Produto> comparadorPorPreco = Comparator.comparingDouble(Produto::getPreco);
        long tempoPreco = ordenador.ordenar(listaProdutos, comparadorPorPreco);

        System.out.println("\n[ORDENAÇÃO POR PREÇO (MergeSort)]: ");
        listaProdutos.forEach(produto -> System.out.println(" - R$" + String.format("%.2f", produto.getPreco()) + " - " + produto.getNome()));

        long tempoMsPreco = TimeUnit.NANOSECONDS.toMillis(tempoPreco);
        System.out.println("\n[TEMPO DE ORDENAÇÃO POR PREÇO] " + tempoMsPreco + " ms.");

        // Ordenação por categoria (alfabética)
        listaProdutos = repositorioProdutos.listarTodos();
        Comparator<Produto> comparadorPorCategoria = Comparator.comparing(Produto::getCategoria);
        long tempoCategoria = ordenador.ordenar(listaProdutos, comparadorPorCategoria);

        System.out.println("\n[ORDENAÇÃO POR CATEGORIA (MergeSort)]: ");
        listaProdutos.forEach(produto -> System.out.println(" - Categoria: " + produto.getCategoria() + " - " + produto.getNome()));

        long tempoMsCategoria = TimeUnit.NANOSECONDS.toMillis(tempoCategoria);
        System.out.println("\n[TEMPO DE ORDENAÇÃO POR CATEGORIA] " + tempoMsCategoria + " ms.");

        // Comparação entre ordenação externa e a ordenação da própria árvore AVL
        System.out.println("\n[COMPARAÇÃO ENTRE ORDENAÇÕES NOMINAIS]:");
        System.out.println("1. Ordenação externa (MergeSort) por nome levou " + tempoMsNome + " ms.");
        System.out.println("2. Listagem ordenada da AVL (em ordem) é O(n), pois mantém ordenação nas inserções.");
    }
}
