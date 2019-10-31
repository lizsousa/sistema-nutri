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
import org.springframework.beans.factory.annotation.Autowired;

@Named
@SessionScoped
public class UsuarioBean extends GenericCrud<Usuario, UsuarioLogic>{

    @Autowired
    private UsuarioLogic logic;
    
    @Autowired
    private PermissaoLogic permissaoLogic;
    
    private Permissao Permissao = new Permissao();
    
    public void novaPermissao(){
        Permissao = new Permissao();
    }
    
    
    public void adicionarPermissao(){
        if(getEntity().getPermissoes()== null){
            getEntity().setPermissoes(new ArrayList<Permissao>());
        }
        if(!getEntity().getPermissoes().contains(Permissao)){
            getEntity().getPermissoes().add(Permissao);
        }
        novaPermissao();
    }
    public void removerPermissao(Permissao permissao){
         if(getEntity().getPermissoes().contains(permissao)){
            getEntity().getPermissoes().remove(permissao);
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
    
    
    public Permissao getPermissao() {
        return Permissao;
    }
    
}
