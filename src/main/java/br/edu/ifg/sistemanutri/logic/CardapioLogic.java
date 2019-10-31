package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.CardapioDAO;
import br.edu.ifg.sistemanutri.dao.CardapioHasProdutoDAO;
import br.edu.ifg.sistemanutri.entity.Cardapio;
import br.edu.ifg.sistemanutri.entity.CardapioHasProduto;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardapioLogic implements GenericLogic<Cardapio, Integer> {

    @Autowired
    private CardapioDAO dao;
    
    @Autowired
    private CardapioHasProdutoDAO peidHasProdutoDAO;
    
    @Override
    public Cardapio salvar(Cardapio entity) throws  NegocioException, SistemaException {        

        List<CardapioHasProduto> listaCardapioProduto = entity.getCardapiosHasProdutos();
        entity.setCardapiosHasProdutos(null);
        entity = dao.salvar(entity);
        if(listaCardapioProduto != null && !listaCardapioProduto.isEmpty()){
            for (CardapioHasProduto cardapioProduto : listaCardapioProduto) {
                cardapioProduto.setCardapio(entity);
                cardapioProduto = peidHasProdutoDAO.salvar(cardapioProduto);
            }
            entity.setCardapiosHasProdutos(listaCardapioProduto);
        }
        return entity;
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
