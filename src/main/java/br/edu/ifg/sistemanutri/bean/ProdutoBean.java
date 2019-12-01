package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.dao.ProdutoDAO;
import br.edu.ifg.sistemanutri.entity.Produto;
import br.edu.ifg.sistemanutri.entity.UnidadeMedida;
import br.edu.ifg.sistemanutri.logic.ProdutoLogic;
import br.edu.ifg.sistemanutri.logic.UnidadeMedidaLogic;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ProdutoBean extends GenericCrud<Produto, ProdutoLogic>{

    @Inject
    private ProdutoLogic logic;
    @Inject
    private ProdutoDAO dao;
    @Inject
    private UnidadeMedidaLogic unidadeMedidalogic;
    
    
    private String descricao;
    private Date dataValidade;
    private BigDecimal estoqueMinimo;
    private BigDecimal estoqueAtual;

    
    @Override
    public void salvar() {
        if(descricao != null && !"".equals(descricao.trim())){
            getEntity().setDescricao(descricao);
        }
        
        super.salvar();
    }
//    public void notificarEstoque(Produto entity){
//        entity = dao.notificar(entity.getEstoqueAtual(), entity.getEstoqueMinimo());
//    }
    @Override
    public void editar(Produto entity) {
        try {
            entity = getLogic().buscarPorId(entity.getId());
            super.editar(entity);
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    public List<UnidadeMedida> getUnidadeMedidas(){
        try {
            return unidadeMedidalogic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
    }
    
        public List<Produto> getProdutos(){
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
    public ProdutoLogic getLogic() {
        return logic;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public UnidadeMedidaLogic getUnidadeMedidalogic() {
        return unidadeMedidalogic;
    }

    public void setUnidadeMedidalogic(UnidadeMedidaLogic unidadeMedidalogic) {
        this.unidadeMedidalogic = unidadeMedidalogic;
    }

    public BigDecimal getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public BigDecimal getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(BigDecimal estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

}

