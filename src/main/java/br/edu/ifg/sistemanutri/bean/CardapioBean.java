package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Cardapio;
import br.edu.ifg.sistemanutri.entity.CardapioHasProduto;
import br.edu.ifg.sistemanutri.entity.Produto;
import br.edu.ifg.sistemanutri.logic.CardapioLogic;
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
public class CardapioBean extends GenericCrud<Cardapio, CardapioLogic>{

    @Autowired
    private CardapioLogic logic;

    @Autowired
    private ProdutoLogic produtoLogic;
    
    private CardapioHasProduto cardapioHasProduto = new CardapioHasProduto();
    
    public void novoCardapioHasProduto(){
        cardapioHasProduto = new CardapioHasProduto();
    }
    
    
    public void adicionarProduto(){
        if(getEntity().getCardapiosHasProdutos() == null){
            getEntity().setCardapiosHasProdutos(new ArrayList<CardapioHasProduto>());
        }
        if(!getEntity().getCardapiosHasProdutos().contains(cardapioHasProduto)){
            getEntity().getCardapiosHasProdutos().add(cardapioHasProduto);
        }
        novoCardapioHasProduto();
    }
    public void removerProduto(CardapioHasProduto produto){
         if(getEntity().getCardapiosHasProdutos().contains(produto)){
            getEntity().getCardapiosHasProdutos().remove(produto);
        }
       
        
    }
    
    
    @Override
    public CardapioLogic getLogic() {
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
    
    public CardapioHasProduto getCardapioHasProduto() {
        return cardapioHasProduto;
    }
    
}
