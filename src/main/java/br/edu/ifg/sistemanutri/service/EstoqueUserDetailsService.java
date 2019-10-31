package br.edu.ifg.sistemanutri.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.ifg.sistemanutri.dao.UsuarioDAO;
import br.edu.ifg.sistemanutri.entity.Permissao;
import br.edu.ifg.sistemanutri.entity.Usuario;

import org.springframework.stereotype.Component;

/**
 *
 * @author iara
 */
@Component
public class EstoqueUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuariodao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //buscar o usuário pelo username
        //Usuario usuario = usuarios.findByLogin(username);

        Usuario usuario = usuariodao.buscar(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        return new UsuarioSistema(usuario, authorities(usuario.getPermissoes()));
    }

    public Collection<? extends GrantedAuthority> authorities(List<Permissao> permissoes) {
        Collection<GrantedAuthority> auths = new ArrayList<>();

        for (Permissao permissao : permissoes) {
            auths.add(new SimpleGrantedAuthority("ROLE_" + permissao.getNome()));
        }

        return auths;
    }

}