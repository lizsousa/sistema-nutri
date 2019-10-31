package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Usuario;
import br.edu.ifg.sistemanutri.entity.UsuarioHasPermissao;
import br.edu.ifg.sistemanutri.entity.Permissao;
import br.edu.ifg.sistemanutri.logic.UsuarioLogic;
import br.edu.ifg.sistemanutri.logic.PermissaoLogic;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UsuarioBean extends GenericCrud<Usuario, UsuarioLogic>{

    @Inject
    private UsuarioLogic logic;
    
    @Inject
    private PermissaoLogic permissaoLogic;
    
    private UsuarioHasPermissao usuarioHasPermissao = new UsuarioHasPermissao();
    
    public void novoUsuarioHasPermissao(){
        usuarioHasPermissao = new UsuarioHasPermissao();
    }
    
    
    public void adicionarPermissao(){
        if(getEntity().getUsuariosHasPermissoes()== null){
            getEntity().setUsuariosHasPermissoes(new ArrayList<UsuarioHasPermissao>());
        }
        if(!getEntity().getUsuariosHasPermissoes().contains(usuarioHasPermissao)){
            getEntity().getUsuariosHasPermissoes().add(usuarioHasPermissao);
        }
        novoUsuarioHasPermissao();
    }
    public void removerPermissao(UsuarioHasPermissao permissao){
         if(getEntity().getUsuariosHasPermissoes().contains(permissao)){
            getEntity().getUsuariosHasPermissoes().remove(permissao);
        }
       
        
    }
    
    
    @Override
    public UsuarioLogic getLogic() {
        return logic;
    }
    
    public List<Permissao> getPermissaos(){
        try {
            return permissaoLogic.buscar(null);
        } catch (NegocioException ex){
            addMensagemErro(ex);
        }catch(SistemaException ex) {
            addMensagemFatal(ex);
        }
        return null;
    }
    
    
    public UsuarioHasPermissao getUsuarioHasPermissao() {
        return usuarioHasPermissao;
    }
    
}
