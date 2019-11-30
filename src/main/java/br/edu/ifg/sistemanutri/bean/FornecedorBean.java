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
    private Boolean tipoFornecedor; 
    private Integer tipoPessoa = 0; /*no prof é boolean e n recebe zero*/
    private String razaoSocial;
    
    public Boolean getTipoFornecedor() {
        return tipoFornecedor;
    }

    public void setTipoFornecedor(Boolean tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }
    
    @Override
    public FornecedorLogic getLogic() {
        return logic;
    }
    
    public List<Fornecedor> getFornecedores() {
        try {
            return getLogic().buscar(null);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            return null;
       }
    }

    
 
//    public String getRazaoSocial() {
//        return razaoSocial;
//    }
//
//    public void setRazaoSocial(String razaoSocial) {
//        this.razaoSocial = razaoSocial;
//    }
//    
    public Integer getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Integer tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

}
//
//   
//    public void verificarCnpjCpf() { /*nao tem no do prof*/
//        if (Status.INSERINDO.equals(getStatus())) {
//            Boolean fornecedorExiste = logic.verificarFornecedorExistente(getEntity().getCnpj());
//            if (fornecedorExiste) {
//                addMensagemAviso("O CPF/CNPJ já existe.");
//
//            }
//        }
//    }
//   
//    @Override
//    public void salvar() {
//        if(razaoSocial != null && !"".equals(razaoSocial.trim())){
//            getEntity().setRazaoSocial(razaoSocial);
//        }
//        super.salvar();
//    }
//
//    @Override
//    public void editar(Fornecedor entity) { /*nao tem no do prof*/
//        try {
//            entity = getLogic().buscarPorId(entity.getId());
//            super.editar(entity);
//        } catch (Exception ex) {
//            addMensagemErro(ex.getMessage());
//            ex.printStackTrace();
//        }
//    }
    
    
    


    

