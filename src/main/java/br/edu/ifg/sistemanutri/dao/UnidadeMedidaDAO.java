package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.entity.UnidadeMedida;
import javax.persistence.NoResultException;

public class UnidadeMedidaDAO extends GenericDAO<UnidadeMedida, Integer> {

    public UnidadeMedida buscar(String sigla) {
        try {
            UnidadeMedida usuario = (UnidadeMedida) getEntityManager().createQuery(
                    "from " + getEntityClass().getName()
                    + " where sigla = '" + sigla + "'").getSingleResult();
            return usuario;
        } catch (NoResultException ex) {
            return null;
        }
    }
}
