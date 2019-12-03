package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.EstoqueDAO;
import br.edu.ifg.sistemanutri.entity.Estoque;
import br.edu.ifg.sistemanutri.logic.enuns.TipoEstoque;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;

public class EstoqueLogic implements GenericLogic<Estoque, Integer>{

    @Inject
    private EstoqueDAO dao;
    
    @Override
    public Estoque salvar(Estoque entity) throws  NegocioException, SistemaException {
        
        if(entity.getQuantidade() == null || entity.getQuantidade().equals(BigDecimal.ZERO)){
            throw new NegocioException("Por favor informe a quantidade diferente de zero.");
        }
        
        
        if(TipoEstoque.SAIDA.equals(entity.getTipoEstoque()) && entity.getQuantidade().compareTo(BigDecimal.ZERO) > 0){
            entity.setQuantidade(entity.getQuantidade().negate());
        }
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

  
