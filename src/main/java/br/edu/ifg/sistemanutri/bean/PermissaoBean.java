package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Permissao;
import br.edu.ifg.sistemanutri.logic.PermissaoLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@SessionScoped
public class PermissaoBean extends GenericCrud<Permissao, PermissaoLogic>{

    @Autowired
    private PermissaoLogic logic;
    
    @Override
    public PermissaoLogic getLogic() {
        return logic;
    }
    
}
