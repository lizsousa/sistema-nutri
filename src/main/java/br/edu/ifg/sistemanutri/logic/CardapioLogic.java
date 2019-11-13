package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.CardapioDAO;
import br.edu.ifg.sistemanutri.dao.CardapioHasProdutoDAO;
import br.edu.ifg.sistemanutri.entity.Estoque;
import br.edu.ifg.sistemanutri.entity.Cardapio;
import br.edu.ifg.sistemanutri.entity.CardapioHasProduto;
import br.edu.ifg.sistemanutri.logic.enuns.TipoEstoque;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class CardapioLogic implements GenericLogic<Cardapio, Integer> {

    @Inject
    private CardapioDAO dao;
  
    @Inject
    private CardapioHasProdutoDAO cardHasProdutoDAO;
    
    @Inject
    private EstoqueLogic estoquelogic;
    
    @Override
    public Cardapio salvar(Cardapio entity) throws  NegocioException, SistemaException {        

        List<CardapioHasProduto> listaCardapioProduto = entity.getCardapiosHasProdutos();
        entity.setCardapiosHasProdutos(null);
        entity = dao.salvar(entity);
        
        if(listaCardapioProduto != null && !listaCardapioProduto.isEmpty()){
            for (CardapioHasProduto cardapioProduto : listaCardapioProduto) {
                cardapioProduto.setCardapio(entity);
                cardapioProduto = cardHasProdutoDAO.salvar(cardapioProduto);
            }
            entity.setCardapiosHasProdutos(listaCardapioProduto);
        }
        return entity;
    }
    public Cardapio baixarEstoque(Cardapio cardapio) throws  NegocioException, SistemaException {        
       
            for(CardapioHasProduto chp : cardapio.getCardapiosHasProdutos()){
                Estoque estoque = new Estoque();
                Estoque estoqueAtual = new Estoque();
                estoque.setTipoEstoque(TipoEstoque.SAIDA);
                estoque.setProduto(chp.getProduto());
                estoque.setQuantidade(chp.getQuantidade());
                estoque.setDataMovimento(new Date());
                estoquelogic.salvar(estoque);
            }
     
        return cardapio;
    }
    
  
    
    @Override
    public void deletar(Cardapio entity) throws  NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public Cardapio buscarPorId(Integer id) throws  NegocioException, SistemaException {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Cardapio> buscar(Cardapio entity) throws  NegocioException, SistemaException {
        return dao.listar();
    }
    
}
