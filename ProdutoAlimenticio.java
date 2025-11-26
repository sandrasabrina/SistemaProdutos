package model;

import exception.NegocioException;

public class ProdutoAlimenticio extends Produto {
    private String dataValidade;

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
