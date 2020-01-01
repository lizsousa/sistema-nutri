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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Named
@SessionScoped
public class LoginBean extends JsfUtil implements Serializable {

    @Inject
    private UsuarioLogic logic;

    private String antiga;
    private String nova;
    private String confirma;

    private Usuario usuarioLogado;

    public Usuario getLoggedUser() {
        if (usuarioLogado == null) {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user instanceof Usuario) {
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

    public void trocarSenha() {
        try {
            logic.trocarSenha(antiga, nova, confirma, getLoggedUser());
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
    }

    public UsuarioLogic getLogic() {
        return logic;
    }

    public void setLogic(UsuarioLogic logic) {
        this.logic = logic;
    }

    public String getAntiga() {
        return antiga;
    }

    public void setAntiga(String antiga) {
        this.antiga = antiga;
    }

    public String getNova() {
        return nova;
    }

    public void setNova(String nova) {
        this.nova = nova;
    }

    public String getConfirma() {
        return confirma;
    }

    public void setConfirma(String confirma) {
        this.confirma = confirma;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

}

// $2a$10$AIUuO6CSZjpXM2KKEaIgxukUlUxeQ/J9S2lSrxwJDS2XQprjzS9SS
// $2a$10$LXPdRLV2e4hbf/0tC5C/SO..t4i7z6YU6uvQvrOKEiJOF.5jGA2a.
