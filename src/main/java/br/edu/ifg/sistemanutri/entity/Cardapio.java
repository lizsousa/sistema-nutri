package br.edu.ifg.sistemanutri.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cardapio")
public class Cardapio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_cardapio")
    private Date dataCardapio;
    
    @Column(name = "nome_cardapio")
    private String nomeCardapio;
    @Column(name = "modo_preparo")
    private String modoPreparo;
    @Column(name = "quantidade_rendimento")
    private BigDecimal quantidadeRendimento;


    @OneToMany(mappedBy = "cardapio", cascade = CascadeType.ALL)
    private List<CardapioHasProduto> cardapiosHasProdutos;

    public Cardapio() {
        this.dataCardapio = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCardapio() {
        return nomeCardapio;
    }

    public void setNomeCardapio(String nomeCardapio) {
        this.nomeCardapio = nomeCardapio;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public BigDecimal getQuantidadeRendimento() {
        return quantidadeRendimento;
    }
    
    public void setQuantidadeRendimento(BigDecimal quantidadeRendimento) {
        this.quantidadeRendimento = quantidadeRendimento;
    }


    public Date getDataCardapio() {
        return dataCardapio;
    }

    public void setDataCardapio(Date dataCardapio) {
        this.dataCardapio = dataCardapio;
    }

    public List<CardapioHasProduto> getCardapiosHasProdutos() {
        return cardapiosHasProdutos;
    }

    public void setCardapiosHasProdutos(List<CardapioHasProduto> cardapiosHasProdutos) {
        this.cardapiosHasProdutos = cardapiosHasProdutos;
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
        final Cardapio other = (Cardapio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
