package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.ProdutoDAO;
import br.edu.ifg.sistemanutri.entity.Produto;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProdutoLogic implements GenericLogic<Produto, Integer> {

    @Autowired
    private ProdutoDAO dao;
    
    @Override
    public Produto salvar(Produto entity) throws  NegocioException, SistemaException {
        if("".equals(entity.getDescricao().trim())){
            throw new NegocioException("a descricao do produto é obrigatório.");
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
        List<Produto> produtos = dao.listar();
        return produtos;
    }
    
}
