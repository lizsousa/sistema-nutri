package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.UsuarioDAO;
import br.edu.ifg.sistemanutri.dao.UsuarioHasPermissaoDAO;
import br.edu.ifg.sistemanutri.entity.Usuario;
import br.edu.ifg.sistemanutri.entity.UsuarioHasPermissao;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import javax.inject.Inject;

public class UsuarioLogic implements GenericLogic<Usuario, Integer> {

    @Inject
    private UsuarioDAO dao;
    
    @Inject
    private UsuarioHasPermissaoDAO nfeHasPermissaoDAO;
    
    @Override
    public Usuario salvar(Usuario entity) throws  NegocioException, SistemaException {        

        List<UsuarioHasPermissao> listaUsuarioPermissao = entity.getUsuariosHasPermissoes();
        entity.setUsuariosHasPermissoes(null);
        entity = dao.salvar(entity);
        if(listaUsuarioPermissao != null && !listaUsuarioPermissao.isEmpty()){
            for (UsuarioHasPermissao notaFiscalPermissao : listaUsuarioPermissao) {
                notaFiscalPermissao.setUsuario(entity);
                notaFiscalPermissao = nfeHasPermissaoDAO.salvar(notaFiscalPermissao);
            }
            entity.setUsuariosHasPermissoes(listaUsuarioPermissao);
        }
        return entity;
    }

    @Override
    public void deletar(Usuario entity) throws  NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public Usuario buscarPorId(Integer id) throws  NegocioException, SistemaException {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Usuario> buscar(Usuario entity) throws  NegocioException, SistemaException {
        return dao.listar();
    }
    
}
