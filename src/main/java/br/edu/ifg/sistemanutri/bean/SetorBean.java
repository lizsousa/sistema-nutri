package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Setor;
import br.edu.ifg.sistemanutri.logic.SetorLogic;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class SetorBean extends GenericCrud<Setor, SetorLogic>{

    @Inject
    private SetorLogic logic;
    
    private String nome;
    private String descricao;

    
    @Override
    public void salvar() {
        if(nome != null && !"".equals(nome.trim())){
            getEntity().setNome(nome);
        }
        if(descricao != null && !"".equals(descricao.trim())){
            getEntity().setDescricao(descricao);
        }
        super.salvar();
    }

    @Override
    public void editar(Setor entity) {
        try {
            entity = getLogic().buscarPorId(entity.getId());
            super.editar(entity);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            ex.printStackTrace();
        }
    }
            
    public List<Setor> getSetors(){
        try {
            return logic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
    }


    
    @Override
    public SetorLogic getLogic() {
        return logic;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



}

