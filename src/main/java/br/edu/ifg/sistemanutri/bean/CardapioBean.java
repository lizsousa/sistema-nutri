package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Cardapio;
import br.edu.ifg.sistemanutri.logic.CardapioLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CardapioBean extends GenericCrud<Cardapio, CardapioLogic>{

    @Inject
    private CardapioLogic logic;
    
    private String nome;
    private Integer quantidade;

    
    @Override
    public void salvar() {
        if(nome != null && !"".equals(nome.trim())){
            getEntity().setNome(nome);
        }
        super.salvar();
    }

    @Override
    public void editar(Cardapio entity) {
        try {
            entity = getLogic().buscarPorId(entity.getId());
            super.editar(entity);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public CardapioLogic getLogic() {
        return logic;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}

