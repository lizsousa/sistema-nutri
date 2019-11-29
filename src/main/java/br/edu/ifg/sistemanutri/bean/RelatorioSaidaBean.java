package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.entity.Setor;
import br.edu.ifg.sistemanutri.logic.EstoqueLogic;

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
public class RelatorioSaidaBean extends JsfUtil{
    
    @Inject
    private EntityManager entityManager;
    
    @Inject
    private EstoqueLogic estoqueLogic;
     
    @Inject
    private SetorLogic setorLogic;
    
    SetorLogic logic;
    
    private String tipoEstoque;
    private Date dataInicio;
    private Date dataFim;
    private String setor;
     
    public RelatorioSaidaBean() {
        
        dataInicio = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).minusDays(15).toInstant());
        dataFim = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        
    }
    
    private Connection connection;

    public StreamedContent relatorioMovimento() {
        try {
            DefaultStreamedContent file;
            InputStream inputStream = getClass().getResourceAsStream("/br/edu/ifg/sistemanutri/relatorios/SAIDA_produto.jrxml");
            
            Map paramentros = new HashMap();
            paramentros.put("DATA_INICIO",dataInicio);
            paramentros.put("DATA_FIM",dataFim);
            paramentros.put("SETOR",setor);
            
            
            byte[] relatorio = ReportUtil.reportToPDF(inputStream, null, getConnection());
            InputStream relatorioStream = new ByteArrayInputStream(relatorio);
            String nome = "relatorio_movimento_estoque_" + new SimpleDateFormat("yyyy_MM_dd").format(new Date()) + ".pdf";
            file = new DefaultStreamedContent(relatorioStream, "application/pdf", nome);
            return file;
        } catch (Exception ex) {
            Logger.getLogger(RelatorioEntradaBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getTipoEstoque() {
        return tipoEstoque;
    }

    public void setTipoEstoque(String tipoEstoque) {
        this.tipoEstoque = tipoEstoque;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
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

    public SetorLogic getSetorLogic() {
        return setorLogic;
    }

    public void setSetorLogic(SetorLogic setorLogic) {
        this.setorLogic = setorLogic;
    }

    public SetorLogic getLogic() {
        return logic;
    }

    public void setLogic(SetorLogic logic) {
        this.logic = logic;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
     
    

}
