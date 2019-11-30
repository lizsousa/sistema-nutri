package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.entity.Fornecedor;
import javax.persistence.NoResultException;

public class FornecedorDAO extends GenericDAO<Fornecedor, Integer>{
    
    public Fornecedor buscar(String cnpjCpf) {
        try {
            Fornecedor fornecedor = (Fornecedor) getEntityManager().createQuery(
                    "from " + getEntityClass().getName()+
                    " where cnpj_cpf = '" + cnpjCpf + "'").getSingleResult();
            return fornecedor;
        } catch (NoResultException ex) {
            return null;
        }
    }


    
    
}
