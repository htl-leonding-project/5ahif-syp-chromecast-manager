package at.htl.control;

import at.htl.entities.InstallAt;
import at.htl.entities.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
@Transactional
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

    public List<InstallAt> getInstallAtListDummy() {
        List installAtList = new LinkedList<InstallAt>();

        installAtList.add(getInstallAtDummy());

        return installAtList;
    }

    public InstallAt getInstallAtDummy() {
        InstallAt installAt = new InstallAt();
        installAt.setInstallDate(LocalDate.of(2021, Month.JANUARY, 1));
        installAt.setRemoveDate(LocalDate.of(2021, Month.NOVEMBER, 11));
        installAt.setDescription("A mouse has been installed on the 1st.january.2021. ON the 11th.november.2021 it was deinstalled");
        return installAt;
    }
}
