package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Produto;
import br.edu.ifg.sistemanutri.entity.UnidadeMedida;
import br.edu.ifg.sistemanutri.logic.ProdutoLogic;
import br.edu.ifg.sistemanutri.logic.UnidadeMedidaLogic;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@SessionScoped
public class ProdutoBean extends GenericCrud<Produto, ProdutoLogic>{

    @Autowired
    private ProdutoLogic logic;
    @Autowired
    private UnidadeMedidaLogic unidadeMedidalogic;
    
    
    private String descricao;
    private Date dataValidade;

    
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
    
    
    public List<UnidadeMedida> getUnidadeMedidas(){
        try {
            return unidadeMedidalogic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
    }
    
        public List<Produto> getProdutos(){
        try {
            return logic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
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

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }


}

