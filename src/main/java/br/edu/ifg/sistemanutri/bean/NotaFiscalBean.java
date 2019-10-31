package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Fornecedor;
import br.edu.ifg.sistemanutri.entity.NotaFiscal;
import br.edu.ifg.sistemanutri.entity.NotaFiscalHasProduto;
import br.edu.ifg.sistemanutri.entity.Produto;
import br.edu.ifg.sistemanutri.logic.FornecedorLogic;
import br.edu.ifg.sistemanutri.logic.NotaFiscalLogic;
import br.edu.ifg.sistemanutri.logic.ProdutoLogic;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@SessionScoped
public class NotaFiscalBean extends GenericCrud<NotaFiscal, NotaFiscalLogic>{

    @Autowired
    private NotaFiscalLogic logic;
    
    @Autowired
    private FornecedorLogic fornecedorLogic;

    @Autowired
    private ProdutoLogic produtoLogic;
    
    private NotaFiscalHasProduto notaFiscalHasProduto = new NotaFiscalHasProduto();
    
    public void novoNotaFiscalHasProduto(){
        notaFiscalHasProduto = new NotaFiscalHasProduto();
    }
    
    
    public void adicionarProduto(){
        if(getEntity().getNotaFiscalsHasProdutos() == null){
            getEntity().setNotaFiscalsHasProdutos(new ArrayList<NotaFiscalHasProduto>());
        }
        if(!getEntity().getNotaFiscalsHasProdutos().contains(notaFiscalHasProduto)){
            getEntity().getNotaFiscalsHasProdutos().add(notaFiscalHasProduto);
        }
        novoNotaFiscalHasProduto();
    }
    public void removerProduto(NotaFiscalHasProduto produto){
         if(getEntity().getNotaFiscalsHasProdutos().contains(produto)){
            getEntity().getNotaFiscalsHasProdutos().remove(produto);
        }
       
        
    }
    
    
    @Override
    public NotaFiscalLogic getLogic() {
        return logic;
    }
    
    public List<Produto> getProdutos(){
        try {
            return produtoLogic.buscar(null);
        } catch (NegocioException ex){
            addMensagemErro(ex);
        }catch(SistemaException ex) {
            addMensagemFatal(ex);
        }
        return null;
    }
    
    
    public List<Fornecedor> getFornecedor(){
        try {
            return fornecedorLogic.buscar(null);
        } catch (NegocioException ex){
            addMensagemErro(ex);
        }catch(SistemaException ex) {
            addMensagemFatal(ex);
        }
        return null;
    }
    
    public NotaFiscalHasProduto getNotaFiscalHasProduto() {
        return notaFiscalHasProduto;
    }
    
}
