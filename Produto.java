package model;

import exception.NegocioException;

// Classe Base para todos os produtos
@InfoProd(versao = "2.0")
public abstract class Produto implements Comparable<Produto> {
    private String id;
    private String nome;
    private double preco;
    private String categoria;
    private int estoque;
    private String produtorLocal;

    public Produto(String id, String nome, double preco, String categoria, int estoque, String produtorLocal) throws NegocioException {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.produtorLocal = produtorLocal;
        
        // Aplicação da Regra de Negócio: preço deve ser positivo
        if (preco <= 0) {
            throw new NegocioException("Preço deve ser positivo. Valor fornecido: " + preco);
        }
        this.preco = preco;
        
        // Aplicação da Regra de Negócio: estoque deve ser >= 0
        if (estoque < 0) {
            throw new NegocioException("Estoque não pode ser negativo. Valor fornecido: " + estoque);
        }
        this.estoque = estoque;
    }

    // Getters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getCategoria() { return categoria; }
    public int getEstoque() { return estoque; }
    public String getProdutorLocal() { return produtorLocal; }

    // Método abstrato para detalhes específicos
    public abstract String getDetalhesEspecificos();

    // Sobrescrita do toString para exibição
    @Override
    public String toString() {
        return String.format("[ID: %s] %s | R$%.2f | Cat: %s | Estoque: %d | Produtor: %s",
                             id, nome, preco, categoria, estoque, produtorLocal);
    }

    // Implementação de Comparable para ordenação padrão por Nome (Alfabetica)
    @Override
    public int compareTo(Produto outro) {
        return this.nome.compareTo(outro.nome);
    }
}
