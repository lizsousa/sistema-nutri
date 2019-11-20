package br.edu.ifg.sistemanutri.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cardapio_has_produto")
public class CardapioHasProduto implements Serializable {

    @EmbeddedId
    private CardapioHasProdutoPk id;
    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false, name = "produto_id")
    private Produto produto;
    @ManyToOne
    @JoinColumn(updatable = false, insertable = false, name = "cardapio_id")
    private Cardapio cardapio;
    
    public CardapioHasProdutoPk getId() {
        return id;
    }

    public void setId(CardapioHasProdutoPk id) {
        this.id = id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        if(produto == null){
            return;
        }
        this.produto = produto;
        if(id == null){
            id =  new CardapioHasProdutoPk();
        }
        id.setProdutoId(produto.getId());
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        if(cardapio == null){
            return;
        }
        this.cardapio = cardapio;
        if(id == null){
            id =  new CardapioHasProdutoPk();
        }
        id.setCardapioId(cardapio.getId());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final CardapioHasProduto other = (CardapioHasProduto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
