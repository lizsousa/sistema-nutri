package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.UnidadeMedidaDAO;
import br.edu.ifg.sistemanutri.entity.UnidadeMedida;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UnidadeMedidaLogic implements GenericLogic<UnidadeMedida, Integer> {

    @Autowired
    private UnidadeMedidaDAO dao;
    
    @Override
    public UnidadeMedida salvar(UnidadeMedida entity) throws  NegocioException, SistemaException {
        if("".equals(entity.getSigla().trim())){
            throw new NegocioException("a unidade de medida é obrigatório.");
        }
        entity = dao.salvar(entity);
        return entity;
    }

    @Override
    public void deletar(UnidadeMedida entity) throws  NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public UnidadeMedida buscarPorId(Integer id) throws  NegocioException, SistemaException {
        UnidadeMedida unidadeMedida = dao.buscarPorId(id);
        return unidadeMedida;
    }

    @Override
    public List<UnidadeMedida>buscar(UnidadeMedida entity) throws  NegocioException, SistemaException {
        List<UnidadeMedida> unidadeMedidas = dao.listar();
        return unidadeMedidas;
    }
    
}
