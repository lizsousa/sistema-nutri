package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Fornecedor;
import br.edu.ifg.sistemanutri.logic.FornecedorLogic;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class FornecedorBean extends GenericCrud<Fornecedor, FornecedorLogic>{
    
    @Inject
    private FornecedorLogic logic;
    
    private int tipoFornecedor;

    public int getTipoFornecedor() {
        return tipoFornecedor;
    }

    public void setTipoFornecedor(int tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }
    
    @Override
    public FornecedorLogic getLogic() {
        return logic;
    }
    
    public List<Fornecedor> getFornecedores(){
        try {
            return getLogic().buscar(null);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            return null;
        }
    }
}
