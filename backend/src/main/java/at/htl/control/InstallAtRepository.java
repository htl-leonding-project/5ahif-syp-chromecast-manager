package at.htl.control;

import at.htl.entities.InstallAt;
import at.htl.entities.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class InstallAtRepository implements PanacheRepository<InstallAt> {
    public InstallAt save(InstallAt installAtToSave) {
        return getEntityManager().merge(installAtToSave);
    }
}
