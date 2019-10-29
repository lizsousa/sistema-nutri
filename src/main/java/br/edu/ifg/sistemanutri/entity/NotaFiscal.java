package br.edu.ifg.sistemanutri.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;
    

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_emissao")
    private Date dataEmissao;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_entrada")
    private Date dataEntrada;
   
    private int numero;
    

    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
    private List<NotaFiscalHasProduto> notaFiscalsHasProdutos;

    public NotaFiscal() {
        this.dataEmissao = new Date();
        this.dataEntrada = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }



    public List<NotaFiscalHasProduto> getNotaFiscalsHasProdutos() {
        return notaFiscalsHasProdutos;
    }

    public void setNotaFiscalsHasProdutos(List<NotaFiscalHasProduto> notaFiscalsHasProdutos) {
        this.notaFiscalsHasProdutos = notaFiscalsHasProdutos;
    }
 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final NotaFiscal other = (NotaFiscal) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
