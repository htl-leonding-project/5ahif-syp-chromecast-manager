package at.htl.control;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

public class MyEntityManagerFactory<T> {
    private static MyEntityManagerFactory instance;
    private EntityManagerFactory emf;
    private EntityManager em;

    public MyEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("JPA-WEB");
        em = emf.createEntityManager();
    }

    public static MyEntityManagerFactory getMyEntityManagerFactory(){
        if(instance==null)instance = new MyEntityManagerFactory();
        return instance;
    }

    public EntityManager getEntityManager(){
        return em;
    }
}
