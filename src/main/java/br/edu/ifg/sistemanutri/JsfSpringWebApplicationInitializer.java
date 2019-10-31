package br.edu.ifg.sistemanutri;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 *
 * @author iara
 */
public class JsfSpringWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();

        applicationContext.scan(JsfSpringWebApplicationInitializer.class.getPackage().getName()); //pacote raiz no qual o Spring irá procurar por suas classes. Como temos somente um pacote, então já foi colocado diretamente o pacote em que está classe aqui está inserida.
//        applicationContext.scan("br.edu.ifg.sistemanutri.util", "br.edu.ifg.sistemanutri.service", "br.edu.ifg.sistemanutri.dao"); //pacote raiz no qual o Spring irá procurar por suas classes. Como temos somente um pacote, então já foi colocado diretamente o pacote em que está classe aqui está inserida.
        
        servletContext.addListener(new ContextLoaderListener(applicationContext));
        servletContext.addListener(new RequestContextListener());
    }

}
