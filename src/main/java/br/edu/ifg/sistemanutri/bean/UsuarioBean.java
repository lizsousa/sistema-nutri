package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Usuario;
import br.edu.ifg.sistemanutri.logic.UsuarioLogic;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UsuarioBean extends GenericCrud<Usuario, UsuarioLogic>{

    @Inject
    private UsuarioLogic logic;
    
    private String nome;
    private String login;
    private String senha;

    
    @Override
    public void salvar() {
        if(nome != null && !"".equals(nome.trim())){
            getEntity().setNome(nome);
        }
        if(login != null && !"".equals(login.trim())){
            getEntity().setLogin(login);
        }
        if(senha != null && !"".equals(senha.trim())){
            getEntity().setSenha(senha);
        }
        super.salvar();
    }

    @Override
    public void editar(Usuario entity) {
        try {
            entity = getLogic().buscarPorId(entity.getId());
            super.editar(entity);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    
    public void ativarDesativar(Usuario usuario){
        try {
            if(usuario.getDataDesativacao() == null){
                usuario.setDataDesativacao(new Date());
            } else {
                usuario.setDataDesativacao(null);
            }
            usuario = getLogic().salvar(usuario);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
        }
    }
    
    
    @Override
    public UsuarioLogic getLogic() {
        return logic;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}

