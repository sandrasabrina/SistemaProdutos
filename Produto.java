package model;

import exception.NegocioException;

/**
 * Classe base para produtos.
 */
@InfoProd(versao = "2.0")
public abstract class Produto implements Comparable<Produto> {
    private final String id;
    private final String nome;
    private final double preco;
    private final String categoria;
    private final int estoque;
    private final String produtorLocal;

    public Produto(String id, String nome, double preco, String categoria, int estoque, String produtorLocal) throws NegocioException {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id não pode ser vazio");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome não pode ser vazio");

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

    // getters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getCategoria() { return categoria; }
    public int getEstoque() { return estoque; }
    public String getProdutorLocal() { return produtorLocal; }

    public abstract String getDetalhesEspecificos();

    @Override
    public String toString() {
        return String.format("[ID: %s] %s | R$%.2f | Cat: %s | Estoque: %d | Produtor: %s",
                id, nome, preco, categoria, estoque, produtorLocal);
    }

    @Override
    public int compareTo(Produto outro) {
        if (outro == null) return 1;
        return this.nome.compareTo(outro.nome);
    }
}
