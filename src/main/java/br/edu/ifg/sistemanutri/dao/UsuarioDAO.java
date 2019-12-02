package br.edu.ifg.sistemanutri.dao;

import br.edu.ifg.sistemanutri.entity.Usuario;
import javax.persistence.NoResultException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> implements UserDetailsService {

    public Usuario buscar(String login) {
        try {
            Usuario usuario = (Usuario) getEntityManager().createQuery(
                    "from " + getEntityClass().getName()
                    + " where login = '" + login + "'").getSingleResult();
            return usuario;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        String jpql = "from Usuario u where u.login = :login";
        Usuario usuario = getEntityManager().createQuery(jpql, Usuario.class)
                .setParameter("login", login)
                .getSingleResult(); /*result*/
        if (usuario == null) {
            throw new UsernameNotFoundException("O login " + login + " não existe");
        }
        usuario.getPermissoes().size();
        return usuario;
    }
}
