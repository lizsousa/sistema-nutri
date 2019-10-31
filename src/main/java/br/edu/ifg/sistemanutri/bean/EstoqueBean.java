package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.logic.EstoqueLogic;
import br.edu.ifg.sistemanutri.entity.Estoque;
import br.edu.ifg.sistemanutri.logic.SetorLogic;
import br.edu.ifg.sistemanutri.entity.Setor;
import br.edu.ifg.sistemanutri.logic.FornecedorLogic;
import br.edu.ifg.sistemanutri.entity.Fornecedor;
import br.edu.ifg.sistemanutri.logic.ProdutoLogic;
import br.edu.ifg.sistemanutri.entity.Produto;
import br.edu.ifg.sistemanutri.logic.enuns.TipoEstoque;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@SessionScoped
public class EstoqueBean extends GenericCrud<Estoque, EstoqueLogic>{
    
    @Autowired
    private EstoqueLogic estoquelogic;
    
    @Autowired
    private FornecedorLogic fornecedorLogic;
    
    
    @Autowired
    private SetorLogic setorLogic;
    
    @Autowired
    private ProdutoLogic produtoLogic;
    
    private BigDecimal quantidade;
    private String tipoEstoque;
    
    
    
    @Override
    public void editar(Estoque entity) {
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
            return fornecedorLogic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
    }
        
    public List<Produto> getProdutos(){
        try {
            return produtoLogic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
    }
    
    
    public List<Estoque> geEstoques(){
        try {
            return estoquelogic.buscar(null);
        } catch (NegocioException ex){
            addMensagemErro(ex);
        }catch(SistemaException ex) {
            addMensagemFatal(ex);
        }
        return null;
    }
        
    public List<Setor> getSetors(){
        try {
            return setorLogic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
    }
    
    public TipoEstoque[] getTiposEstoque(){
        return TipoEstoque.values();
    }


    @Override
    public void salvar(){
        super.salvar();
    }
    
    @Override
    public EstoqueLogic getLogic() {
        return estoquelogic;
    }
    

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoEstoque() {
        return tipoEstoque;
    }

    public void setTipoEstoque(String tipoEstoque) {
        this.tipoEstoque = tipoEstoque;
    }
       
}