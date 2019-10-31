package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.context.annotation.ImportResource;

public class GenericDAO<E, ID extends Serializable> implements Serializable {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public E salvar(E entity) throws SistemaException {
        try {
            entity = entityManager.merge(entity);
            return entity;
        } catch (Exception ex) {
            throw new SistemaException("Erro ao salvar " + getEntityClass().getName(), ex);
        }
    }
    
    @Transactional
    public void deletar(E entity) throws SistemaException {
        try {
            entityManager.remove(entity);
        } catch (Exception ex) {
            throw new SistemaException("Erro ao deletar " + getEntityClass().getName(), ex);
        }
    }

    public List<E> listar() throws SistemaException {
        try {
            return entityManager.createQuery("from " + getEntityClass().getName()).getResultList();
        } catch (Exception ex) {
            throw new SistemaException("Erro ao listar " + getEntityClass().getName(), ex);
        }
    }

    public E buscarPorId(ID id) throws SistemaException {
        try {
        return entityManager.find(getEntityClass(), id);
        } catch (Exception ex) {
            throw new SistemaException("Erro ao buscar o " + getEntityClass().getName() + " com ID = "+id, ex);
        }
    }

    public Class<E> getEntityClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;

        return (Class<E>) paramType.getActualTypeArguments()[0];
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
