package br.edu.ifg.sistemanutri.security;

import br.edu.ifg.sistemanutri.dao.UsuarioDAO;
import br.edu.ifg.sistemanutri.util.PersistenceFactory;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfiguration.class, UsuarioDAO.class, PersistenceFactory.class);
    }

    
}
