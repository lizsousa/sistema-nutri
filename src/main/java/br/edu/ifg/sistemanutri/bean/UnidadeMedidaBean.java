package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.UnidadeMedida;
import br.edu.ifg.sistemanutri.logic.UnidadeMedidaLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UnidadeMedidaBean extends GenericCrud<UnidadeMedida, UnidadeMedidaLogic>{

    @Inject
    private UnidadeMedidaLogic logic;
    
    private String sigla;
    
    @Override
    public void salvar() {
        if(sigla != null && !"".equals(sigla.trim())){
            getEntity().setSigla(sigla);
        }
        super.salvar();
    }

    @Override
    public void editar(UnidadeMedida entity) {
        try {
            entity = getLogic().buscarPorId(entity.getId());
            super.editar(entity);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public UnidadeMedidaLogic getLogic() {
        return logic;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    

}

