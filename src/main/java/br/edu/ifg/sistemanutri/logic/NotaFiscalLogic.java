package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.NotaFiscalDAO;
import br.edu.ifg.sistemanutri.entity.NotaFiscal;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import javax.inject.Inject;

public class NotaFiscalLogic implements GenericLogic<NotaFiscal, Integer> {

    @Inject
    private NotaFiscalDAO dao;
    
    @Override
    public NotaFiscal salvar(NotaFiscal entity) throws  NegocioException, SistemaException {
        
        entity = dao.salvar(entity);
        return entity;
    }

    @Override
    public void deletar(NotaFiscal entity) throws  NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public NotaFiscal buscarPorId(Integer id) throws  NegocioException, SistemaException {
        NotaFiscal notafiscais = dao.buscarPorId(id);
        return notafiscais;
    }

    @Override
    public List<NotaFiscal> buscar(NotaFiscal entity) throws  NegocioException, SistemaException {
        List<NotaFiscal> notafiscaies = dao.listar();
        return notafiscaies;
    }
    
}
