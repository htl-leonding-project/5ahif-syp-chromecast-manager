package at.htl.control;

import at.htl.entities.InstallAt;
import at.htl.entities.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
@Transactional
public class InstallAtRepository implements PanacheRepository<InstallAt> {
    @Inject
    RoomRepository roomRepository;
    @Inject
    DeviceRepository deviceRepository;
    @Inject
    UserRepository userRepository;

    @Transactional
    public InstallAt save(InstallAt installAtToSave) {
        if(!roomRepository.isPersistent(installAtToSave.getRoom())){
            roomRepository.save(installAtToSave.getRoom());
        }
        if(!deviceRepository.isPersistent(installAtToSave.getDevice())){
            deviceRepository.save(installAtToSave.getDevice());
        }
        if(!userRepository.isPersistent(installAtToSave.getUser())){
            userRepository.save(installAtToSave.getUser());
        }

        if (installAtToSave.getId() == null)
        {
            persist(installAtToSave);
            return installAtToSave;
        }
//        if(Collections.unmodifiableList(listAll()).size()!=0){
//            for(InstallAt currentInstallAt : Collections.unmodifiableList(listAll())){
//                if(installAtToSave.getId() == currentInstallAt.getId() ){
//                    return installAtToSave;
//                }
//            }
//        }

//        if(!deviceRepository.getAllDevices().contains(installAtToSave.getDevice())){
//                deviceRepository.getEntityManager().merge(installAtToSave.getDevice());
//        }
        return getEntityManager().merge(installAtToSave);
    }

    @Transactional
    public InstallAt delete(Long id)
    {
        InstallAt installAt = findById(id);
        getEntityManager().remove(installAt);
        return installAt;
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

    public List<InstallAt> findAllInstallAts() {
        return Collections.unmodifiableList(listAll());
    }

    public List<InstallAt> findInstallAtsByRoomId(long id) {
        List<InstallAt> installAts = new LinkedList<>();

        for(InstallAt currentInstallAt : findAllInstallAts()){
            if(currentInstallAt.getRoom().getId() == id){
                installAts.add(currentInstallAt);
            }
        }
        return installAts;
    }
}
