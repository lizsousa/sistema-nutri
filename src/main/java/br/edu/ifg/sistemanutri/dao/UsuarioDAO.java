package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.entity.Usuario;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDAO extends GenericDAO<Usuario, Integer>{

    public Usuario buscar(String login) {
        try{
            Usuario usuario = (Usuario) getEntityManager().createQuery(
                        "from " + getEntityClass().getName()
                        +" where login = '"+login
                        +"' and data_desativacao is null").getSingleResult();
            return usuario;
        } catch (NoResultException ex){
            return null;
        }
    }
}
