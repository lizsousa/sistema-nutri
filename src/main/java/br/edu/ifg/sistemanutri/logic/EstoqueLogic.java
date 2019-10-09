package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.EstoqueDAO;
import br.edu.ifg.sistemanutri.entity.Estoque;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import javax.inject.Inject;

public class EstoqueLogic implements GenericLogic<Estoque, Integer>{

    @Inject
    private EstoqueDAO dao;
    
    @Override
    public Estoque salvar(Estoque entity) throws  NegocioException, SistemaException {
        
        entity = dao.salvar(entity);
        return entity;
    }

    @Override
    public void deletar(Estoque entity) throws  NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public Estoque buscarPorId(Integer id) throws  NegocioException, SistemaException {
        Estoque estoques = dao.buscarPorId(id);
        return estoques;
    }

    @Override
    public List<Estoque> buscar(Estoque entity) throws  NegocioException, SistemaException {
        List<Estoque> estoques = dao.listar();
        return estoques;
    }
    
}

  
