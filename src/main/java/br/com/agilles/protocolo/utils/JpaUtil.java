package br.com.agilles.protocolo.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by jille on 22/05/2017.
 */
@ApplicationScoped
public class JpaUtil {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProtocoloPU");
    @Produces
    @RequestScoped
    public EntityManager getEntityManager(){
        return factory.createEntityManager();
    }

    public void close(@Disposes EntityManager manager){
        manager.close();

    }


}
