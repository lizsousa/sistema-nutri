package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.NotaFiscal;
import br.edu.ifg.sistemanutri.logic.NotaFiscalLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class NotaFiscalBean extends GenericCrud<NotaFiscal, NotaFiscalLogic>{

    @Inject
    private NotaFiscalLogic logic;
    
    private Integer numero;
    @Override
    public void salvar() {
        super.salvar();
    }

    @Override
    public void editar(NotaFiscal entity) {
        try {
            entity = getLogic().buscarPorId(entity.getId());
            super.editar(entity);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public NotaFiscalLogic getLogic() {
        return logic;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

}

