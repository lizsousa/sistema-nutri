package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.FornecedorDAO;
import br.edu.ifg.sistemanutri.entity.Fornecedor;
import br.edu.ifg.sistemanutri.util.Assert;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.List;
import javax.inject.Inject;

public class FornecedorLogic implements GenericLogic<Fornecedor, Integer> {

    @Inject
    private FornecedorDAO dao;

    @Override
    public Fornecedor salvar(Fornecedor entity) throws NegocioException, SistemaException {
        if ("".equals(entity.getNomeFantasia().trim())) {
            throw new NegocioException("Nome do fornecedor é obrigatório.");
        }
        if (entity.getCnpj() == null && entity.getCpf() == null){
            if (!Assert.isCnpjValido(entity.getCnpj())) {
                throw new NegocioException("CNPJ inválido.");
            }
            if(!Assert.isCpf(entity.getCpf())){
                 throw new NegocioException("CPF inválido.");
            }
        }
        if (entity.getTelefone() == null){
            if (!Assert.isValidTelefone(entity.getTelefone())){
                throw new NegocioException("Telefone inválido");
            }  
        }else{
              if (entity.getCnpj() == null && !Assert.isCpf(entity.getCpf())){
                    throw new NegocioException("CPF inválido.");
                }
                if(entity.getCpf()== null && !Assert.isCnpjValido(entity.getCnpj())){
                throw new NegocioException("CNPJ inválido.");
            }

        }
        if ((entity.getCnpj() != null) && entity.getCpf() != null &&  Assert.isCnpjValido(entity.getCnpj())){
                entity.setCpf("");
            }
            if(entity.getCnpj()!= null && entity.getCpf() != null && Assert.isCpf(entity.getCpf())){           
            entity.setCnpj("");        
            }
            if(!Assert.isValidEmail(entity.getEmail())){
             throw new NegocioException("Email inválido.");
            }
            if(entity.getCnpj()== null) entity.setCnpj("");
        dao.salvar(entity);
        return null; 
        }
    
     @Override
    public void deletar(Fornecedor entity) throws NegocioException, SistemaException {
        dao.deletar(entity);
    }
    
    @Override
    public Fornecedor buscarPorId(Integer id) throws NegocioException, SistemaException {
        Fornecedor fornecedor = dao.buscarPorId(id);
        return fornecedor;

    }

    @Override
    public List<Fornecedor> buscar(Fornecedor entity) throws NegocioException, SistemaException {
        return dao.listar();
    }
    
}
