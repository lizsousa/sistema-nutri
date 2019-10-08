package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.NotaFiscal;
import br.edu.ifg.sistemanutri.entity.Fornecedor;
import br.edu.ifg.sistemanutri.entity.Produto;
import br.edu.ifg.sistemanutri.logic.NotaFiscalLogic;
import br.edu.ifg.sistemanutri.logic.FornecedorLogic;
import br.edu.ifg.sistemanutri.logic.ProdutoLogic;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class NotaFiscalBean extends GenericCrud<NotaFiscal, NotaFiscalLogic>{

    @Inject
    private ProdutoLogic produtologic;

    @Inject
    private FornecedorLogic fornecedorlogic;

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
        
    public List<Fornecedor> getFornecedores(){
        try {
            return fornecedorlogic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
    }
        
    public List<Produto> getProdutos(){
        try {
            return produtologic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
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

