package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Fornecedor;
import br.edu.ifg.sistemanutri.entity.Setor;
import br.edu.ifg.sistemanutri.logic.EstoqueLogic;
import br.edu.ifg.sistemanutri.logic.FornecedorLogic;
import br.edu.ifg.sistemanutri.logic.SetorLogic;
import br.edu.ifg.sistemanutri.logic.enuns.TipoEstoque;
import br.edu.ifg.sistemanutri.util.JsfUtil;
import br.edu.ifg.sistemanutri.util.ReportUtil;
import br.edu.ifg.sistemanutri.util.exception.NegocioException;
import br.edu.ifg.sistemanutri.util.exception.SistemaException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@SessionScoped
public class RelatorioBean extends JsfUtil{

    @Inject
    private EntityManager entityManager;

    private Connection connection;
    
    @Inject
    private EstoqueLogic estoqueLogic;
    
    @Inject
    private FornecedorLogic fornecedorLogic;
    
    @Inject
    private SetorLogic setorLogic;
    
    private Date dataInicio;
    private Date dataFim;
    private TipoEstoque tipoEstoque;
    Fornecedor fornecedorSelecionado;
    private Setor setor;
    private String Discricao;

    public RelatorioBean() {
        dataInicio = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).minusDays(15).toInstant());
        dataFim = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());  
    }

    public StreamedContent relatorioMovimentoEntrada() {
        try {
            DefaultStreamedContent file;
            InputStream inputStream = getClass().getResourceAsStream("/br/edu/ifg/sistemanutri/relatorios/ENTRADA_produto.jrxml");
            System.out.println("$$$$$$ Data Inicio:"+dataInicio);
            System.out.println("$$$$$$ Data Fim:"+dataFim);
            HashMap parametros = new HashMap();
            parametros.put("DATA_INICIO",dataInicio);
            parametros.put("DATA_FIM",dataFim);
            parametros.put("TIPO_ESTOQUE",tipoEstoque.name());
            parametros.put("FORNECEDOR",fornecedorSelecionado.getId());
            
            byte[] relatorio = ReportUtil.reportToPDF(inputStream, parametros, getConnection());
            InputStream relatorioStream = new ByteArrayInputStream(relatorio);
            String nome = "relatorio_movimento_estoque_" + new SimpleDateFormat("yyyy_MM_dd").format(new Date()) + ".pdf";
            file = new DefaultStreamedContent(relatorioStream, "application/pdf", nome);
            return file;
        } catch (Exception ex) {
            Logger.getLogger(RelatorioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public StreamedContent relatorioMovimentoSaida() {
        try {
            DefaultStreamedContent file;
            InputStream inputStream = getClass().getResourceAsStream("/br/edu/ifg/sistemanutri/relatorios/SAIDA_produto.jrxml");
            
            HashMap<String, Object> parametros = new HashMap();
            parametros.put("DATA_INICIO",dataInicio);
            parametros.put("DATA_FIM",dataFim);
            parametros.put("TIPO_ESTOQUE",tipoEstoque.name());
            parametros.put("SETOR",setor.getId());
            
            byte[] relatorio = ReportUtil.reportToPDF(inputStream, parametros, getConnection());
            InputStream relatorioStream = new ByteArrayInputStream(relatorio);
            String nome = "relatorio_movimento_estoque_" + new SimpleDateFormat("yyyy_MM_dd").format(new Date()) + ".pdf";
            file = new DefaultStreamedContent(relatorioStream, "application/pdf", nome);
            return file;
        } catch (Exception ex) {
            Logger.getLogger(RelatorioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
 
    private Connection getConnection() {
        Session session = (Session) entityManager.getDelegate();
        session.doWork(
                new Work() {
            public void execute(Connection connection) throws SQLException {
                setConnection(connection);
            }
        }
        );
        return this.connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
    public List<Fornecedor> getFornecedores(){
        try {
            return fornecedorLogic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
    }
    
    public List<Setor> getSetors(){
        try {
            return setorLogic.buscar(null);
        } catch (SistemaException ex) {
            addMensagemFatal(ex);
        } catch (NegocioException ex) {
            addMensagemErro(ex);
        }
        return null;
   }
     public TipoEstoque[] getTiposEstoque(){
        return TipoEstoque.values();
    }
     
    /*public FornecedorLogic getFornecedorLogic() {
        return fornecedorLogic;
    }

    public void setFornecedorLogic(FornecedorLogic fornecedorLogic) {
        this.fornecedorLogic = fornecedorLogic;
    }*/
    public TipoEstoque getTipoEstoque() {
        return tipoEstoque;
    }

    public void setTipoEstoque(TipoEstoque tipoEstoque) {
        this.tipoEstoque = tipoEstoque;
    }

    public Fornecedor getFornecedorSelecionado() {
        return fornecedorSelecionado;
    }

    public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
        this.fornecedorSelecionado = fornecedorSelecionado;
    }
    
    public SetorLogic getSetorLogic() {
        return setorLogic;
    }

    public void setSetorLogic(SetorLogic setorLogic) {
        this.setorLogic = setorLogic;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
    
    public String getDiscricao() {
        return Discricao;
    }

    public void setDiscricao(String Discricao) {
        this.Discricao = Discricao;
    }
  
}
