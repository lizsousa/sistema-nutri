package br.edu.ifg.sistemanutri.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CardapioHasProdutoPk implements Serializable {

    @Column(name = "produto_id")
    private Integer produtoId;
    
    @Column(name = "cardapio_id")
    private Integer cardapioId;

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getCardapioId() {
        return cardapioId;
    }

    public void setCardapioId(Integer cardapioId) {
        this.cardapioId = cardapioId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.produtoId);
        hash = 79 * hash + Objects.hashCode(this.cardapioId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CardapioHasProdutoPk other = (CardapioHasProdutoPk) obj;
        if (!Objects.equals(this.produtoId, other.produtoId)) {
            return false;
        }
        if (!Objects.equals(this.cardapioId, other.cardapioId)) {
            return false;
        }
        return true;
    }

    
    
}
