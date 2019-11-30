package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.entity.NotaFiscal;
import javax.persistence.NoResultException;

public class NotaFiscalDAO extends GenericDAO<NotaFiscal, Integer>{
        public NotaFiscal buscar(int numero) {
        try {
            NotaFiscal notaFiscal = (NotaFiscal) getEntityManager().createQuery(
                    "from " + getEntityClass().getName()
                    + " where numero = '" + numero + "'").getSingleResult();
            return notaFiscal;
        } catch (NoResultException ex) {
            return null;
        }
    }

    
}
