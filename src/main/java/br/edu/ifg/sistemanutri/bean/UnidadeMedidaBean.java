package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.UnidadeMedida;
import br.edu.ifg.sistemanutri.logic.UnidadeMedidaLogic;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@SessionScoped
public class UnidadeMedidaBean extends GenericCrud<UnidadeMedida, UnidadeMedidaLogic>{

    private String sigla;
    private String nome;
    
    @Autowired
    private UnidadeMedidaLogic logic;
    
    @Override
    public UnidadeMedidaLogic getLogic() {
        return logic;
    }
    
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
    
    public List<UnidadeMedida> getUnidadeMedidas(){
        try {
            return getLogic().buscar(null);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            return null;
        }
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
