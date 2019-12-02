package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.ProdutoDAO;
import br.edu.ifg.sistemanutri.entity.Produto;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import javax.inject.Inject;

public class ProdutoLogic implements GenericLogic<Produto, Integer> {

    @Inject
    private ProdutoDAO dao;
    
    @Override
    public Produto salvar(Produto entity) throws  NegocioException, SistemaException {
        if("".equals(entity.getDescricao().trim())){
            throw new NegocioException("a descricao do produto é obrigatório.");
        }
        
        if (entity.getId() == null && verificarProdutoExistente(entity.getDescricao())) {
            throw new NegocioException("O produto já cadastrado.");
        }
        entity = dao.salvar(entity);
        return entity;
    }
    @Override
    public void deletar(Produto entity) throws  NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public Produto buscarPorId(Integer id) throws  NegocioException, SistemaException {
        Produto produto = dao.buscarPorId(id);
        return produto;
    }

    @Override
    public List<Produto>buscar(Produto entity) throws  NegocioException, SistemaException {
        List<Produto> produtos = dao.buscar(entity);
        return produtos;
    }
    
    public Boolean verificarProdutoExistente(String descricao) {
        Produto prod = dao.buscar(descricao);
        return prod != null;
    }
    
}
