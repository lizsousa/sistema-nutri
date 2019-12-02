package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.entity.Produto;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.NoResultException;

public class ProdutoDAO extends GenericDAO<Produto, Integer> {

    public Produto buscar(String descricao) {
        try {
            Produto produto = (Produto) getEntityManager().createQuery(
                    "from " + getEntityClass().getName()
                    + " where descricao = '" + descricao 
                    + "'").getSingleResult();
            return produto;
        } catch (NoResultException ex) {
            return null;
        }

    }
    
    
//
//    public Produto notificar(BigDecimal estoqueAtual, BigDecimal estoqueMinimo) {
//        try {
//            Produto notifica = (Produto) getEntityManager().createQuery(
//                    "from " + getEntityClass().getName()
//                    + " where estoque_atual = '" + estoqueAtual
//                    + "' and estoque_minimo = '" + estoqueMinimo
//                    + "'").getSingleResult();
//            return notifica;
//        } catch (NoResultException ex) {
//            return null;
//        }
//    }

    @Override
    public List<Produto> buscar(Produto entity) throws SistemaException {
        
        return super.buscar(entity);
    }

}
