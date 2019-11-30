package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.entity.Cardapio;
import javax.persistence.NoResultException;


public class CardapioDAO extends GenericDAO<Cardapio, Integer>{

    public Cardapio buscar(String nomeCardapio) {
        try {
            Cardapio cardapio = (Cardapio) getEntityManager().createQuery(
                    "from " + getEntityClass().getName()
                    + " where nome_cardapio = '" + nomeCardapio + "'").getSingleResult();
            return cardapio;
        } catch (NoResultException ex) {
            return null;
        }
    }

    
}
