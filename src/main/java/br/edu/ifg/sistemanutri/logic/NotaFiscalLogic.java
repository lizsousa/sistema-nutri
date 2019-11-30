package br.edu.ifg.sistemanutri.logic;

import br.edu.ifg.sistemanutri.dao.NotaFiscalDAO;
import br.edu.ifg.sistemanutri.dao.NotaFiscalHasProdutoDAO;
import br.edu.ifg.sistemanutri.entity.Estoque;
import br.edu.ifg.sistemanutri.entity.NotaFiscal;
import br.edu.ifg.sistemanutri.entity.NotaFiscalHasProduto;
import br.edu.ifg.sistemanutri.logic.enuns.TipoEstoque;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class NotaFiscalLogic implements GenericLogic<NotaFiscal, Integer> {

    @Inject
    private NotaFiscalDAO dao;
    
    @Inject
    private NotaFiscalHasProdutoDAO nfeHasProdutoDAO;
    
    @Inject
    private EstoqueLogic estoquelogic;
    
    @Override
    public NotaFiscal salvar(NotaFiscal notaFiscal) throws  NegocioException, SistemaException {  
        
        boolean novaNota = notaFiscal.getId() == null;
        
        List<NotaFiscalHasProduto> listaNotaFiscalProduto = notaFiscal.getNotaFiscalsHasProdutos();
        notaFiscal.setNotaFiscalsHasProdutos(null);
         
        
        notaFiscal = dao.salvar(notaFiscal);
        
        if(listaNotaFiscalProduto != null && !listaNotaFiscalProduto.isEmpty()){
            for (NotaFiscalHasProduto notaFiscalProduto : listaNotaFiscalProduto) {
                notaFiscalProduto.setNotaFiscal(notaFiscal);
                notaFiscalProduto = nfeHasProdutoDAO.salvar(notaFiscalProduto);
            }
            notaFiscal.setNotaFiscalsHasProdutos(listaNotaFiscalProduto);
        }
        if(novaNota){
            for(NotaFiscalHasProduto nhp : notaFiscal.getNotaFiscalsHasProdutos()){
                Estoque estoque = new Estoque();
                estoque.setTipoEstoque(TipoEstoque.ENTRADA);
                estoque.setFornecedor(notaFiscal.getFornecedor());
                estoque.setProduto(nhp.getProduto());
                estoque.setQuantidade(nhp.getQuantidade());
                estoque.setDataMovimento(new Date());
                estoquelogic.salvar(estoque);
            }
        }
//        
//        if (entity.getId() == null && verificarNFExistente(entity.getNumero())) {
//            throw new NegocioException("O nota fiscal já cadastrado.");
//        }
        return notaFiscal;
    }

    @Override
    public void deletar(NotaFiscal entity) throws  NegocioException, SistemaException {
        dao.deletar(entity);
    }

    @Override
    public NotaFiscal buscarPorId(Integer id) throws  NegocioException, SistemaException {
        return dao.buscarPorId(id);
    }

    @Override
    public List<NotaFiscal> buscar(NotaFiscal entity) throws  NegocioException, SistemaException {
        return dao.listar();
    }
    
    public Boolean verificarNFExistente(int numero) {
        NotaFiscal nf = dao.buscar(numero);
        return nf != null;
    }

    
}
