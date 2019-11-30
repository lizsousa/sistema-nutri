package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.bean.UsuarioBean;
import br.edu.ifg.sistemanutri.dao.UsuarioDAO;
import br.edu.ifg.sistemanutri.entity.Usuario;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class UsuarioLogic implements GenericLogic<Usuario, Integer> {

    @Inject
    private UsuarioDAO dao;
    @Inject
    private UsuarioBean bean;

    @Override
    public Usuario salvar(Usuario entity) throws NegocioException, SistemaException {
        if ("".equals(entity.getNome().trim())) {
            throw new NegocioException("Nome do usuário é obrigatório.");
        }
        if (entity.getDataCadastro() == null) {
            entity.setDataCadastro(new Date());
        }

        if (entity.getId() == null && verificarLoginExistente(entity.getLogin())) {
            throw new NegocioException("O usuário já cadastrado.");
        }
        entity = dao.salvar(entity);
        return entity;
    }

    @Override
    public void deletar(Usuario entity) throws NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public Usuario buscarPorId(Integer id) throws NegocioException, SistemaException {
        Usuario usuario = dao.buscarPorId(id);
        usuario.getPermissoes().size();
        return usuario;
    }

    @Override
    public List<Usuario> buscar(Usuario entity) throws NegocioException, SistemaException {
        List<Usuario> usuarios = dao.listar();
        return usuarios;
    }

    public Boolean verificarLoginExistente(String login) {
        Usuario user = dao.buscar(login);
        return user != null;
    }

}
