package br.edu.ifg.sistemanutri.service;

import br.edu.ifg.sistemanutri.entity.Usuario;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author iara
 */
public class UsuarioSistema extends User {

    private static final long serialVersionUID = 1L;

    private String nome;

    public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authority) {
        super(usuario.getLogin(), usuario.getSenha(), authority);

        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    
}
