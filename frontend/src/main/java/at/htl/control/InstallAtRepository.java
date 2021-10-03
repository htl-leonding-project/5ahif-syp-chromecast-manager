package at.htl.control;

import at.htl.entities.InstallAt;
import at.htl.entities.Room;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class InstallAtRepository extends MyEntityManagerFactory<InstallAtRepository> {
    /**
     * Use the method 'merge' of the EntityManager to persist the entity
     * - when the object doesn't exist in the database or the id is null, a new database entry is created (persist)
     * - when the object already exists in the database, it will be updated
     *
     * @param installAtToSave - object to persist
     * @return the managed instance of the object
     */
    public InstallAt save(InstallAt installAtToSave) {
        return getEntityManager().merge(installAtToSave);
    }

    public List<InstallAt> findAllInstallAts(){
        return (List<InstallAt>)getEntityManager().createNamedQuery("InstallAt.findAll")
                .getResultList();
    }
}
