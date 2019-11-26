package br.edu.ifg.sistemanutri.util;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;

public class PersistenceFactory {

    Flyway flyway;

    @Bean
    @Produces
    public EntityManager getEntityManager() {
        EntityManager entityManager;

        entityManager = Persistence
                .createEntityManagerFactory("sistema_nutri_pu")
                .createEntityManager();

//        if(flyway == null){
//            this.flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/siscom", "root","ifg").load();
//            flyway.migrate();
//        }
//        Flyway flyway = Flyway.configure().dataSource(
//                entityManager.getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url").toString(), 
//                entityManager.getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.user").toString(),
//                entityManager.getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.password").toString()).load();
        return entityManager;
    }
//
    public void close(@Disposes EntityManager entityManager) {
        entityManager.close();
    }
}
