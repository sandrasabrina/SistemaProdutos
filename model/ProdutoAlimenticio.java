package model;

import exception.NegocioException;

/**
 * Produto alimentício com data de validade.
 */
public class ProdutoAlimenticio extends Produto {
    private final String dataValidade;

    public ProdutoAlimenticio(String id, String nome, double preco, String categoria, int estoque, String produtorLocal, String dataValidade) throws NegocioException {
        super(id, nome, preco, categoria, estoque, produtorLocal);
        this.dataValidade = dataValidade;
    }

    @Override
    public String getDetalhesEspecificos() {
        return "Validade: " + dataValidade;
    }

    @Override
    public String toString() {
        return super.toString() + " | Detalhes: " + getDetalhesEspecificos();
    }
}
