package model;

import exception.NegocioException;

/**
 * Classe abstrata que representa um produto genérico do sistema.
 * Fornece validações básicas e campos comuns para produtos.
 */
@InfoProd(versao = "2.0")
public abstract class Produto implements Comparable<Produto> {

    // Identificador único do produto
    private final String id;
    // Nome do produto
    private final String nome;
    // Preço unitário do produto
    private final double preco;
    // Categoria do produto (ex: "Alimentício", "Artesanal")
    private final String categoria;
    // Quantidade disponível em estoque
    private final int estoque;
    // Nome do produtor ou local de origem
    private final String produtorLocal;

    /**
     * Construtor base para todos os produtos. Realiza validações de regra de negócio.
     */
    public Produto(String id, String nome, double preco, String categoria, int estoque, String produtorLocal) throws NegocioException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (preco <= 0) {
            throw new NegocioException("Preço deve ser positivo. Valor fornecido: " + preco);
        }
        if (estoque < 0) {
            throw new NegocioException("Estoque não pode ser negativo. Valor fornecido: " + estoque);
        }
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.estoque = estoque;
        this.produtorLocal = produtorLocal;
    }

    // Métodos de acesso (getters)
    public String getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getCategoria() { return categoria; }
    public int getEstoque() { return estoque; }
    public String getProdutorLocal() { return produtorLocal; }

    /**
     * Retorna detalhes específicos do tipo de produto.
     * Deve ser implementado pelas subclasses para complementar as informações.
     */
    public abstract String getDetalhesEspecificos();

    /**
     * Retorna uma representação textual completa do produto.
     */
    @Override
    public String toString() {
        return String.format(
                "[ID: %s] %s | R$%.2f | Categoria: %s | Estoque: %d | Produtor: %s",
                id, nome, preco, categoria, estoque, produtorLocal
        );
    }

    /**
     * Compara dois produtos com base no nome para ordenação alfabética.
     */
    @Override
    public int compareTo(Produto outro) {
        if (outro == null) return 1;
        return this.nome.compareTo(outro.nome);
    }
}
