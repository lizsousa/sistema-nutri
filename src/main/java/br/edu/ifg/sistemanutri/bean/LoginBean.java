package br.edu.ifg.sistemanutri.bean;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@SessionScoped
public class LoginBean implements Serializable{
    
    @NotEmpty
    @Size(min = 8, message = "Password deve ter no mínimo 8 caracteres")
    private String password;
 
    @NotEmpty
    @Email(message = "E-mail inválido")
    private String email;
 
    @Autowired
    private SecurityContext securityContext;
 
    @Autowired
    private ExternalContext externalContext;
 
    @Autowired
    private FacesContext facesContext;
 
    public void submit() throws IOException {
        
        switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
                break;
            case SUCCESS:
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
                externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
                break;
            case NOT_DONE:
        }
    }
 
    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams()
                  .credential(new UsernamePasswordCredential(email, password))
        );
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}

