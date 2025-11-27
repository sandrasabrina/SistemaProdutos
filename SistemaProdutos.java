package app;

import algorithm.AVLTree;
import algorithm.Arvore;
import algorithm.MergeSort;
import algorithm.Ordenacao;
import exception.NegocioException;
import model.InfoProd;
import model.Produto;
import model.ProdutoAlimenticio;
import model.ProdutoArtesanal;
import repository.RepositorioHash;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Ponto de entrada do Sistema de Gerenciamento de Produtos Naturais.
 * Demonstra todas as funcionalidades e a integração dos componentes.
 */
public class SistemaProdutos {
    
    // Repositório de produtos indexado por ID (HashMap)
    private RepositorioHash<Produto> repositorio = new RepositorioHash<>(Produto::getId);
    
    // Árvore AVL indexada por Nome (para busca rápida e ordenação em-ordem)
    private Arvore<String, Produto> indiceNome = new AVLTree<>();
    
    // Algoritmo de Ordenação
    private Ordenacao<Produto> sortAlgorithm = new MergeSort<>();

    public static void main(String[] args) {
        SistemaProdutos sistema = new SistemaProdutos();
        
        // 1. Configuração e demonstração de anotações e regras de negócio
        sistema.demonstrarAnotacoes();
        sistema.demonstrarRegraNegocio();
        
        // 2. Cadastro de Produtos Válidos
        sistema.cadastrarProdutosTeste();
        
        // 3. Demonstração de CRUD (Hash)
        sistema.demonstrarCrud();

        // 4. Demonstração da Árvore AVL (Busca e Listagem Em-Ordem)
        sistema.demonstrarArvoreAVL();
        
        // 5. Demonstração e Profiling do MergeSort
        sistema.demonstrarOrdenacaoEProfiling();
    }

    /**
     * Cadastra um produto no repositório Hash e na árvore AVL.
     */
    private void cadastrar(Produto p) {
        repositorio.cadastrar(p);
        indiceNome.inserir(p.getNome(), p);
    }
    
    private void cadastrarProdutosTeste() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 2. CADASTRO DE PRODUTOS VÁLIDOS (Hash e AVL) ");
        System.out.println("##########################################################");
        try {
            // Produtos cadastrados para demonstração
            Produto p1 = new ProdutoAlimenticio("A001", "Mel Orgânico", 25.50, "Alimentício", 50, "Fazenda Pura", "2026-01-01");
            Produto p2 = new ProdutoArtesanal("B002", "Sabonete Natural Lavanda", 12.00, "Higiene", 120, "Atelie da Terra", "Óleos Essenciais");
            Produto p3 = new ProdutoAlimenticio("C003", "Café Gourmet Moído", 45.90, "Alimentício", 80, "Sítio do Vovô", "2025-12-31");
            Produto p4 = new ProdutoAlimenticio("A004", "Geleia de Morango", 18.75, "Alimentício", 60, "Fazenda Pura", "2026-03-01");
            Produto p5 = new ProdutoArtesanal("B005", "Vaso de Cerâmica", 60.00, "Decoração", 15, "Barro Arte", "Argila");
            
            cadastrar(p1);
            cadastrar(p2);
            cadastrar(p3);
            cadastrar(p4);
            cadastrar(p5);
            
            System.out.println("\n5 Produtos cadastrados com sucesso no Repositório (Hash) e no Índice (AVL).");

        } catch (NegocioException e) {
            System.err.println("Erro irrecuperável ao cadastrar produtos de teste: " + e.getMessage());
        }
    }

    private void demonstrarRegraNegocio() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 1. DEMONSTRAÇÃO DE REGRA DE NEGÓCIO (NegocioException) ");
        System.out.println("##########################################################");
        
        // Teste de Preço Inválido (Preço deve ser > 0)
        try {
            new ProdutoAlimenticio("X001", "Teste Preço", -5.00, "Teste", 10, "Local", "2025-01-01");
        } catch (NegocioException e) {
            System.err.println("ERRO ESPERADO (Preço Negativo): " + e.getMessage());
        }
        
        // Teste de Estoque Inválido (Estoque deve ser >= 0)
        try {
            new ProdutoAlimenticio("X002", "Teste Estoque", 10.00, "Teste", -1, "Local", "2025-01-01");
        } catch (NegocioException e) {
            System.err.println("ERRO ESPERADO (Estoque Negativo): " + e.getMessage());
        }
    }

    private void demonstrarAnotacoes() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 1. LEITURA DE ANOTAÇÃO @InfoProd (Reflexão) ");
        System.out.println("##########################################################");
        
        // Leitura da anotação na classe Produto
        InfoProd infoProd = Produto.class.getAnnotation(InfoProd.class);
        if (infoProd != null) {
            System.out.println("Anotação na classe Produto:");
            System.out.println("  Versão: " + infoProd.versao());
            System.out.println("  Categoria Padrão: " + infoProd.categoria());
        }
        
        // Leitura da anotação na classe RepositorioHash
        InfoProd infoRepo = RepositorioHash.class.getAnnotation(InfoProd.class);
        if (infoRepo != null) {
            System.out.println("\nAnotação na classe RepositorioHash:");
            System.out.println("  Versão: " + infoRepo.versao());
            System.out.println("  Categoria Padrão: " + infoRepo.categoria());
        }

        // Exemplo de busca de um método existente (listarTodos) via Reflection para demonstrar Type Introspection
        try {
            Method metodoListarTodos = RepositorioHash.class.getMethod("listarTodos");
            System.out.println("\nMétodo 'listarTodos' encontrado com sucesso em RepositorioHash via Reflection.");
        } catch (NoSuchMethodException e) {
            System.err.println("Erro: O método 'listarTodos' esperado não foi encontrado em RepositorioHash.");
        }
    }

    private void demonstrarCrud() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 3. DEMONSTRAÇÃO DE CRUD (REPOSITÓRIO HASH) ");
        System.out.println("##########################################################");
        
        // 3a. Buscar
        Produto buscado = repositorio.buscar("C003");
        System.out.println("\n[BUSCA] ID C003: " + (buscado != null ? buscado.getNome() : "Não encontrado"));
        
        // 3b. Listar Todos
        System.out.println("\n[LISTAR TODOS (Desordenado - HashMap)]: ");
        repositorio.listarTodos().forEach(System.out::println);
        
        // 3c. Remover
        repositorio.remover("A004");
        System.out.println("\n[REMOVER] Produto com ID A004 removido.");
        
        // 3d. Listar Todos Após Remoção
        System.out.println("\n[LISTAR APÓS REMOÇÃO]:");
        repositorio.listarTodos().forEach(System.out::println);
    }
    
    private void demonstrarArvoreAVL() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 4. DEMONSTRAÇÃO DA ÁRVORE AVL (Indexação por Nome) ");
        System.out.println("##########################################################");
        
        // 4a. Busca na Árvore
        Produto buscadoArvore = indiceNome.buscar("Mel Orgânico");
        System.out.println("\n[BUSCA AVL] Nome 'Mel Orgânico': " + (buscadoArvore != null ? buscadoArvore.getId() : "Não encontrado"));
        
        // 4b. Listagem Em-Ordem (Travessia In-Order, que retorna os itens ordenados pela chave (Nome))
        System.out.println("\n[IMPRESSÃO EM-ORDEM (Ordenado por Nome - AVL)]: ");
        List<Produto> listaEmOrdem = indiceNome.listarEmOrdem();
        listaEmOrdem.forEach(p -> System.out.println(" - " + p.getNome() + " (ID: " + p.getId() + ")"));
    }

    private void demonstrarOrdenacaoEProfiling() {
        System.out.println("\n\n##########################################################");
        System.out.println("### 5. DEMONSTRAÇÃO DE ORDENAÇÃO E PROFILING (MergeSort) ");
        System.out.println("##########################################################");

        // Obter lista atual do repositório (itens restantes após remoção)
        List<Produto> listaProdutos = repositorio.listarTodos();
        
        // --- 5a. Ordenação por Nome (Comparator) ---
        Comparator<Produto> porNome = Comparator.comparing(Produto::getNome);
        long tempoNome = sortAlgorithm.ordenar(listaProdutos, porNome);

        System.out.println("\n[ORDENAÇÃO POR NOME (MergeSort)]: ");
        listaProdutos.forEach(p -> System.out.println(" - " + p.getNome()));
        
        // Log em Milissegundos (ms)
        long tempoMsNome = TimeUnit.NANOSECONDS.toMillis(tempoNome);
        System.out.println("\n[LOG PROFILING] Tempo de Ordenação por Nome: " + tempoMsNome + " ms.");
        
        // --- 5b. Ordenação por Preço (Comparator) ---
        listaProdutos = repositorio.listarTodos(); // Recarrega a lista desordenada
        Comparator<Produto> porPreco = Comparator.comparingDouble(Produto::getPreco);
        long tempoPreco = sortAlgorithm.ordenar(listaProdutos, porPreco);

        System.out.println("\n[ORDENAÇÃO POR PREÇO (MergeSort)]: ");
        listaProdutos.forEach(p -> System.out.println(" - R$" + String.format("%.2f", p.getPreco()) + " - " + p.getNome()));
        
        long tempoMsPreco = TimeUnit.NANOSECONDS.toMillis(tempoPreco);
        System.out.println("\n[LOG PROFILING] Tempo de Ordenação por Preço: " + tempoMsPreco + " ms.");
        
        // --- 5c. Ordenação por Categoria (Comparator) ---
        listaProdutos = repositorio.listarTodos(); // Recarrega a lista desordenada
        Comparator<Produto> porCategoria = Comparator.comparing(Produto::getCategoria);
        long tempoCategoria = sortAlgorithm.ordenar(listaProdutos, porCategoria);
        
        System.out.println("\n[ORDENAÇÃO POR CATEGORIA (MergeSort)]: ");
        listaProdutos.forEach(p -> System.out.println(" - Categoria: " + p.getCategoria() + " - " + p.getNome()));
        
        long tempoMsCategoria = TimeUnit.NANOSECONDS.toMillis(tempoCategoria);
        System.out.println("\n[LOG PROFILING] Tempo de Ordenação por Categoria: " + tempoMsCategoria + " ms.");
        
        // --- 5d. Comparação com Em-Ordem (Integração de Algoritmos) ---
        System.out.println("\n[COMPARAÇÃO DE ORDENAÇÃO - ORDEM ALFABÉTICA]:");
        System.out.println("1. Ordenação Externa (MergeSort) por Nome levou " + tempoMsNome + " ms.");
        System.out.println("2. Ordenação Interna da AVL (Listagem Em-Ordem por Nome) é intrinsecamente rápida (O(n)), pois a ordem é mantida durante as inserções.");
    }
}
