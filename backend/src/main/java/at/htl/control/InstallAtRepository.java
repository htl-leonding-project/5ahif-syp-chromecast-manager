package at.htl.control;

import at.htl.entities.InstallAt;
import at.htl.entities.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class InstallAtRepository implements PanacheRepository<InstallAt> {


    @Transactional
    public InstallAt save(InstallAt installAtToSave) {
        if (installAtToSave == null)
        {
            throw new NullPointerException("Installation is null");
        }

        return getEntityManager().merge(installAtToSave);
    }

    @Transactional
    public void delete(Long id)
    {
        InstallAt installAt = findById(id);
        getEntityManager().remove(installAt);
    }

}
