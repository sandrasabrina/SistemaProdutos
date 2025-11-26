package model;

import exception.NegocioException;

public class ProdutoArtesanal extends Produto {
    private String material;

    public ProdutoArtesanal(String id, String nome, double preco, String categoria, int estoque, String produtorLocal, String material) throws NegocioException {
        super(id, nome, preco, categoria, estoque, produtorLocal);
        this.material = material;
    }

    @Override
    public String getDetalhesEspecificos() {
        return "Material: " + material;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Detalhes: " + getDetalhesEspecificos();
    }
}
