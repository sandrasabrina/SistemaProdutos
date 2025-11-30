package model;

import exception.ExcecaoNegocio;

/**
 * Representa um produto artesanal, destacando seu material principal.
 */
public class ProdutoArtesanal extends Produto {

    // Material principal utilizado no produto artesanal
    private final String material;

    /**
     * Construtor do produto artesanal.
     * @param material Material predominante do produto
     */
    public ProdutoArtesanal(
            String id,
            String nome,
            double preco,
            String categoria,
            int estoque,
            String produtorLocal,
            String material
    ) throws ExcecaoNegocio {
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
