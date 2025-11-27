package model;

import exception.NegocioException;

/**
 * Representa um produto alimentício, com data de validade.
 */
public class ProdutoAlimenticio extends Produto {

    // Data de validade do produto no formato "AAAA-MM-DD"
    private final String dataValidade;

    /**
     * Construtor do produto alimentício.
     * @param dataValidade Data de validade no formato "AAAA-MM-DD"
     */
    public ProdutoAlimenticio(
            String id,
            String nome,
            double preco,
            String categoria,
            int estoque,
            String produtorLocal,
            String dataValidade
    ) throws NegocioException {
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
