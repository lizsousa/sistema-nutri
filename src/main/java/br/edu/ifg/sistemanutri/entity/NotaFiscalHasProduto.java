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
@Table(name="nota_fiscal_has_produto")
public class NotaFiscalHasProduto implements Serializable {

    @EmbeddedId
    private NotaFiscalHasProdutoPk id;
    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false, name = "produto_id")
    private Produto produto;
    @ManyToOne
    @JoinColumn(updatable = false, insertable = false, name = "nota_fiscal_id")
    private NotaFiscal notaFiscal;
    
    public NotaFiscalHasProdutoPk getId() {
        return id;
    }

    public void setId(NotaFiscalHasProdutoPk id) {
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
        this.produto = produto;
        if(id == null){
            id =  new NotaFiscalHasProdutoPk();
        }
        id.setProdutoId(produto.getId());
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
        if(id == null){
            id =  new NotaFiscalHasProdutoPk();
        }
        id.setNotaFiscalId(notaFiscal.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final NotaFiscalHasProduto other = (NotaFiscalHasProduto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
