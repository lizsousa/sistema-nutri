package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Produto;
import br.edu.ifg.sistemanutri.logic.ProdutoLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ProdutoBean extends GenericCrud<Produto, ProdutoLogic>{

    @Inject
    private ProdutoLogic logic;
    
    private String descricao;

    
    @Override
    public void salvar() {
        if(descricao != null && !"".equals(descricao.trim())){
            getEntity().setDescricao(descricao);
        }
        super.salvar();
    }

    @Override
    public void editar(Produto entity) {
        try {
            entity = getLogic().buscarPorId(entity.getId());
            super.editar(entity);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public ProdutoLogic getLogic() {
        return logic;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}

