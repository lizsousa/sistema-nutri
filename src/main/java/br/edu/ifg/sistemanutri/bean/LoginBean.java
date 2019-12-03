package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Permissao;
import br.edu.ifg.sistemanutri.entity.Usuario;
import br.edu.ifg.sistemanutri.logic.UsuarioLogic;
import br.edu.ifg.sistemanutri.util.JsfUtil;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
@Named
@SessionScoped
public class LoginBean extends JsfUtil implements Serializable{
  
    @Inject
    private UsuarioLogic logic;
    
    private Usuario usuarioLogado;
    
    public Usuario getLoggedUser(){
        if(usuarioLogado == null){
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(user instanceof Usuario){
                try {
                    usuarioLogado = (Usuario) user;
                    usuarioLogado = logic.buscarPorId(usuarioLogado.getId());
                } catch (NegocioException ex) {
                    addMensagemAviso(ex.getMessage());
                    Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SistemaException ex) {
                    addMensagemAviso(ex.getMessage());
                    Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return usuarioLogado;
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

