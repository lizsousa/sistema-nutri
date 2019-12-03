package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.entity.Fornecedor;
import javax.persistence.NoResultException;

public class FornecedorDAO extends GenericDAO<Fornecedor, Integer>{
    

        public Fornecedor buscar(String cnpj, String cpf) {
        try {
            Fornecedor fornecedor = (Fornecedor) getEntityManager().createQuery(
                    "from " + getEntityClass().getName()
                    + " where cnpj = :cnpj"
                    + " or cpf = :cpf")
                    .setParameter("cpf", cpf).setParameter("cnpj", cnpj).
                    getSingleResult();
            return fornecedor;
        } catch (NoResultException ex) {
            return null;
        }
    }

    

    
    
}
