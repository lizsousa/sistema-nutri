package br.edu.ifg.sistemanutri.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import br.edu.ifg.sistemanutri.service.EstoqueUserDetailsService;

/**
 *
 * @author iara
 */
@EnableWebSecurity
/*habilita recursos de segurança*/
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EstoqueUserDetailsService estoqueUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Para qualquer requisição (anyRequest) é preciso estar 
                // autenticado (authenticated).
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // Aqui dizemos que temos uma página customizada.
                .loginPage("/login.xhtml")
                // Mesmo sendo a página de login, precisamos avisar
                // ao Spring Security para liberar o acesso a ela.
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(estoqueUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}
