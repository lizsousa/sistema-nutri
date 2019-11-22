package br.edu.ifg.sistemanutri.bean;

import br.edu.ifg.sistemanutri.util.JsfUtil;
import br.edu.ifg.sistemanutri.util.ReportUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@SessionScoped
public class RelatorioBean extends JsfUtil{
    
    @PersistenceContext
    private EntityManager entityManager;

    private Connection connection;
        public StreamedContent relatorioMovimento() {
        try {
            DefaultStreamedContent file;
            InputStream inputStream = getClass().getResourceAsStream("/br/edu/ifg/sistemanutri/relatorios/ENTRADA_produto.jrxml");
            byte[] relatorio = ReportUtil.reportToPDF(inputStream, null, getConnection());
            InputStream relatorioStream = new ByteArrayInputStream(relatorio);
            String nome = "relatorio_movimento_estoque_"+new SimpleDateFormat("yyyy_MM_dd").format(new Date())+".pdf";
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
    
}
