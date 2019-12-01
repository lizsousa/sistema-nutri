package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.entity.Setor;
import javax.persistence.NoResultException;

public class SetorDAO extends GenericDAO<Setor, Integer>{
        public Setor buscar(String nome) {
        try {
            Setor setor = (Setor) getEntityManager().createQuery(
                    "from " + getEntityClass().getName()
                    + " where nome = '" + nome + "'").getSingleResult();
            return setor;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
