package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.io.Serializable;
import java.util.List;

public interface GenericLogic<E, ID extends Serializable> extends Serializable{
    
    E salvar(E entity) throws NegocioException, SistemaException;
    void deletar(E entity) throws NegocioException, SistemaException;
    
    E buscarPorId(ID id) throws NegocioException, SistemaException;
    List<E> buscar(E entity) throws NegocioException, SistemaException;
    
}
