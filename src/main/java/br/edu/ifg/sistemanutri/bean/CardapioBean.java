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

@Named
@SessionScoped
public class CardapioBean extends GenericCrud<Cardapio, CardapioLogic> {
    private Cardapio cardapioTemp;

    @Inject
    private CardapioLogic logic;

    @Inject
    private ProdutoLogic produtoLogic;

    private CardapioHasProduto cardapioHasProduto = new CardapioHasProduto();

    public void novoCardapioHasProduto() {
        cardapioHasProduto = new CardapioHasProduto();
    }
    
    public void verificarNomeCardapio() {
        if (Status.INSERINDO.equals(getStatus())) {
            Boolean cardapioExiste = logic.verificarCardapioExistente(getEntity().getNomeCardapio());
            if (cardapioExiste) {
                addMensagemAviso("Digite outro cardapio, pois o digitado já existe.");

            }
        }
    }
    public void adicionarProduto() {
        if (getEntity().getCardapiosHasProdutos() == null) {
            getEntity().setCardapiosHasProdutos(new ArrayList<CardapioHasProduto>());
        }
        cardapioHasProduto.setCardapio(getEntity());
        if (!getEntity().getCardapiosHasProdutos().contains(cardapioHasProduto)) {
            getEntity().getCardapiosHasProdutos().add(cardapioHasProduto);
        }
        novoCardapioHasProduto();
    }

    public void removerProduto(CardapioHasProduto produto) {
        if (getEntity().getCardapiosHasProdutos().contains(produto)) {
            getEntity().getCardapiosHasProdutos().remove(produto);
        }

    }

    public void usarReceita(Cardapio receita) { 
        cardapioTemp = receita;
      
    }

    public void baixarCardapio(){
        try {
            logic.baixarEstoque(cardapioTemp);
            cardapioTemp = null;
      } catch (NegocioException ex) {
            addMensagemErro(ex);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        }
    }

    @Override
    public CardapioLogic getLogic() {
        return logic;
    }

    public List<Produto> getProdutos() {
        try {
            return produtoLogic.buscar(null);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        }
        return null;
    }

    public CardapioHasProduto getCardapioHasProduto() {
        return cardapioHasProduto;
    }

    public Cardapio getCardapioTemp() {
        return cardapioTemp;
    }

    public void setCardapioTemp(Cardapio cardapioTemp) {
        this.cardapioTemp = cardapioTemp;
    }

    public ProdutoLogic getProdutoLogic() {
        return produtoLogic;
    }

    public void setProdutoLogic(ProdutoLogic produtoLogic) {
        this.produtoLogic = produtoLogic;
    }

    
}
