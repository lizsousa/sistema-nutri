package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.util.JsfUtil;
import br.edu.ifg.sistemanutri.util.ReportUtil;
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
public class RelatorioEntradaBean extends JsfUtil {

    @Inject
    private EntityManager entityManager;

    private Date dataInicio;
    private Date dataFim;

    public RelatorioEntradaBean() {
        
        dataInicio = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).minusDays(15).toInstant());
        dataFim = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
            
    
    
    private Connection connection;

    public StreamedContent relatorioMovimento() {
        try {
            DefaultStreamedContent file;
            InputStream inputStream = getClass().getResourceAsStream("/br/edu/ifg/sistemanutri/relatorios/ENTRADA_produto.jrxml");
            
            Map paramentros = new HashMap();
            paramentros.put("DATA_INICIO",dataInicio);
            paramentros.put("DATA_FIM",dataFim);
            
            
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
    
}
