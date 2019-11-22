package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Permissao;
import br.edu.ifg.sistemanutri.entity.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
@Named
@SessionScoped
public class LoginBean implements Serializable{
  
    public Usuario getLoggedUser(){
        Usuario usuario = null;
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof Usuario){
            usuario = (Usuario) user;
        }
        return usuario;
    }
      public String logOff() {
        SecurityContextHolder.clearContext();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }

      public boolean temPermissao(String nomePermissao) {
        try {
            Usuario usuario = getLoggedUser();
            if (usuario != null && usuario.getPermissoes() != null && !usuario.getPermissoes().isEmpty()) {
                for (Permissao permissao : usuario.getPermissoes()) {
                    if (nomePermissao.equals(permissao.getNome())) {
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
      
}

