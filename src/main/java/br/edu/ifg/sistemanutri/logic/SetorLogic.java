package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.SetorDAO;
import br.edu.ifg.sistemanutri.entity.Setor;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import javax.inject.Inject;

public class SetorLogic implements GenericLogic<Setor, Integer> {

    @Inject
    private SetorDAO dao;
    
    @Override
    public Setor salvar(Setor entity) throws  NegocioException, SistemaException {
        if("".equals(entity.getNome().trim())){
            throw new NegocioException("Nome é obrigatório.");
        }
        if (entity.getId() == null && verificarSetorExistente(entity.getNome())) {
            throw new NegocioException("O setor já cadastrado.");
        }
        entity = dao.salvar(entity);
        return entity;
    }

    @Override
    public void deletar(Setor entity) throws  NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public Setor buscarPorId(Integer id) throws  NegocioException, SistemaException {
        Setor setor = dao.buscarPorId(id);
        return setor;
    }

    @Override
    public List<Setor> buscar(Setor entity) throws  NegocioException, SistemaException {
        List<Setor> setores = dao.listar();
        return setores;
    }
    
    public Boolean verificarSetorExistente(String nome) {
        Setor set = dao.buscar(nome);
        return set != null;
    }

    
}
