package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.CardapioDAO;
import br.edu.ifg.sistemanutri.entity.Cardapio;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import javax.inject.Inject;

public class CardapioLogic implements GenericLogic<Cardapio, Integer> {

    @Inject
    private CardapioDAO dao;
    
    @Override
    public Cardapio salvar(Cardapio entity) throws  NegocioException, SistemaException {
        if("".equals(entity.getNome().trim())){
            throw new NegocioException("Nome da receita é obrigatório.");
        }
        entity = dao.salvar(entity);
        return entity;
    }

    @Override
    public void deletar(Cardapio entity) throws  NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public Cardapio buscarPorId(Integer id) throws  NegocioException, SistemaException {
        Cardapio cardapio = dao.buscarPorId(id);
        return cardapio;
    }

    @Override
    public List<Cardapio> buscar(Cardapio entity) throws  NegocioException, SistemaException {
        List<Cardapio> cardapios = dao.listar();
        return cardapios;
    }
    
}
