package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.PermissaoDAO;
import br.edu.ifg.sistemanutri.entity.Permissao;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PermissaoLogic implements GenericLogic<Permissao, Integer> {

    @Autowired
    private PermissaoDAO dao;
    
    @Override
    public Permissao salvar(Permissao entity) throws  NegocioException, SistemaException {
        if("".equals(entity.getNome().trim())){
            throw new NegocioException("Nome da permissão é obrigatório.");
        }
//        entity.setNome(StringUtil.replaceAllSpecialCharacters(entity.getNome()));
        dao.salvar(entity);
        return null;
    }

    @Override
    public void deletar(Permissao entity) throws  NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public Permissao buscarPorId(Integer id) throws  NegocioException, SistemaException {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Permissao> buscar(Permissao entity) throws  NegocioException, SistemaException {
        return dao.listar();
    }
    
}
