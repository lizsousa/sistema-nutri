package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.entity.Produto;
import javax.persistence.NoResultException;

public class ProdutoDAO extends GenericDAO<Produto, Integer>{
    
    public Produto buscar(String descricao) {
        try {
            Produto produto = (Produto) getEntityManager().createQuery(
                    "from " + getEntityClass().getName()
                    + " where descricao = '" + descricao + "'").getSingleResult();
            return produto;
        } catch (NoResultException ex) {
            return null;
        }
    }

    
}
