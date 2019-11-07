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
    
    private Integer tipoPessoa = 0;
    private Boolean tipoFornecedor; 
    private String razaoSocial;
    
   
    @Inject
    private FornecedorLogic logic;
    
    @Override
    public FornecedorLogic getLogic() {
        return logic;
    }

       
    @Override
    public void salvar() {
        if(razaoSocial != null && !"".equals(razaoSocial.trim())){
            getEntity().setRazaoSocial(razaoSocial);
        }
        super.salvar();
    }

    @Override
    public void editar(Fornecedor entity) {
        try {
            entity = getLogic().buscarPorId(entity.getId());
            super.editar(entity);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    public List<Fornecedor> getFornecedors(){
        try {
            return getLogic().buscar(null);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            return null;
        }
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    
    public Integer getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Integer tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Boolean getTipoFornecedor() {
        return tipoFornecedor;
    }

    public void setTipoFornecedor(Boolean tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }

    
 }

    

