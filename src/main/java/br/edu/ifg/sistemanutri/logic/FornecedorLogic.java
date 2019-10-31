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
    public Fornecedor salvar(Fornecedor entity) throws NegocioException, SistemaException{
        
            if("".equals(entity.getNomeFantasia().trim())){
                throw new NegocioException("Nome do fornecedor é obrigatório.");
            }
            if(entity.getCnpjCpf()== null){
                if(!Assert.isCnpjcpfValido(entity.getCnpjCpf())){
                    throw new NegocioException("CNPJ ou CPF inválido.");
                }
            }else{
                if(entity.getCnpjCpf()== null && !Assert.isCnpjcpfValido(entity.getCnpjCpf())){
                    throw new NegocioException("CNPJ ou CPF inválido.");
                }

            }
            if(entity.getCnpjCpf()!= null && Assert.isCnpjcpfValido(entity.getCnpjCpf())){
                entity.setInscricao("");

             }
        
         if(!Assert.isValidEmail(entity.getEmail())){
             throw new NegocioException("Email inválido.");
        }               
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
    public List<Fornecedor> buscar(Fornecedor entity) throws  NegocioException, SistemaException {
        List<Fornecedor> fornecedores = dao.listar();
        return fornecedores;
    }
 
    
}
