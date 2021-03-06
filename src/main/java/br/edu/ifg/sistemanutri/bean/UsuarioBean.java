package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Usuario;
import br.edu.ifg.sistemanutri.entity.Permissao;
import br.edu.ifg.sistemanutri.logic.UsuarioLogic;
import br.edu.ifg.sistemanutri.logic.PermissaoLogic;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Named
@SessionScoped
public class UsuarioBean extends GenericCrud<Usuario, UsuarioLogic> {

    @Inject
    private UsuarioLogic logic;

    @Inject
    private PermissaoLogic permissaoLogic;

    private Permissao Permissao = new Permissao();

    private String senha;

    public void novaPermissao() {
        Permissao = new Permissao();
    }

    public void verificarLogin() throws NegocioException {
        if (Status.INSERINDO.equals(getStatus())) {
            Boolean usuarioExiste = logic.verificarLoginExistente(getEntity().getLogin());
            if (usuarioExiste) {
                addMensagemAviso("Digite outro usuário, pois o digitado já existe.");

            }
            
        }
    }

    @Override
    public void salvar() {
        if (Status.INSERINDO.equals(getStatus()) || (senha != null && !"".equals(senha.trim()))) {
            senha = criptografar(senha);
            getEntity().setSenha(senha);
        }
    
        super.salvar();
    }

    public void adicionarPermissao() {
        if (getEntity().getPermissoes() == null) {
            getEntity().setPermissoes(new ArrayList<Permissao>());
        }
        if (!getEntity().getPermissoes().contains(Permissao)) {
            getEntity().getPermissoes().add(Permissao);
        }
        novaPermissao();
    }

    public void removerPermissao(Permissao permissao) {
        if (getEntity().getPermissoes().contains(permissao)) {
            getEntity().getPermissoes().remove(permissao);
        }

    }
    
    public void ativarDesativar(Usuario usuario) {
        try {
            if (usuario.getDataDesativacao() == null) {
                usuario.setDataDesativacao(new Date());
            } else {
                usuario.setDataDesativacao(null);
            }
       
            usuario = getLogic().salvar(usuario);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
        }
    }

    public String criptografar(String senha) {
        return new BCryptPasswordEncoder().encode(senha);

    }

    @Override
    public UsuarioLogic getLogic() {
        return logic;
    }

    public List<Permissao> getPermissaos() {
        try {
            return permissaoLogic.buscar(null);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        }
        return null;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Permissao getPermissao() {
        return Permissao;
    }
    

}
