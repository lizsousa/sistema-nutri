package br.edu.ifg.sistemanutri.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NotaFiscalHasProdutoPk implements Serializable {

    @Column(name = "produto_id")
    private Integer produtoId;
    
    @Column(name = "nota_fiscal_id")
    private Integer notaFiscalId;

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getNotaFiscalId() {
        return notaFiscalId;
    }

    public void setNotaFiscalId(Integer notaFiscalId) {
        this.notaFiscalId = notaFiscalId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.produtoId);
        hash = 79 * hash + Objects.hashCode(this.notaFiscalId);
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
        final NotaFiscalHasProdutoPk other = (NotaFiscalHasProdutoPk) obj;
        if (!Objects.equals(this.produtoId, other.produtoId)) {
            return false;
        }
        if (!Objects.equals(this.notaFiscalId, other.notaFiscalId)) {
            return false;
        }
        return true;
    }

    
    
}
