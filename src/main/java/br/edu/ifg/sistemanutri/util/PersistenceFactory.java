package br.edu.ifg.sistemanutri.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.flywaydb.core.Flyway;

public class PersistenceFactory {

    Flyway flyway;

    public EntityManager getEntityManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

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
//    public void close(@Disposes EntityManager entityManager) {
//        entityManager.close();
//    }
}
