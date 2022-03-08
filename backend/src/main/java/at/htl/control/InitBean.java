package at.htl.control;

import at.htl.entities.Device;
import at.htl.entities.InstallAt;
import at.htl.entities.Room;
import at.htl.entities.User;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class InitBean {

    private final String FILE_NAME_ROOMS = "rooms.csv";
    private final String FILE_NAME_DEVICES = "devices.csv";

    @Inject
    RoomRepository roomRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    DeviceRepository deviceRepository;

    @Inject
    InstallAtRepository installAtRepository;

    @Inject
    Logger log;

    User user1 = new User("Batuhan","123124ASD");
    User user2 = new User("Moritz","456754DFG");
    User user3 = new User("Patrick","asdasd3242");


    @Transactional
    void onStartUp(@Observes StartupEvent event)
    {

        addTestUsers();

        List<Room> rooms = roomRepository.readCSV(FILE_NAME_ROOMS);
        log.info(rooms);
        rooms.forEach(r -> System.out.println(r));
        for (Room r: rooms) {
            roomRepository.save(r);
        }
        System.out.println("--------------");

        //List<Device> devices = deviceRepository.readCSV(FILE_NAME_DEVICES);
       /* log.info(devices);
        devices.forEach(d -> System.out.println(d));
        for(Device d: devices){
            deviceRepository.save(d);
        }*/

        deviceRepository.save(new Device("Beamer","Microsoft"));
        deviceRepository.save(new Device("Maus","Lenovo"));
        deviceRepository.save(new Device("Tastatur","HP"));
        deviceRepository.save(new Device("Maus","Aspire"));
        deviceRepository.save(new Device("Maus","HP"));
        deviceRepository.save(new Device("Maus","Thinkpad"));
        deviceRepository.save(new Device("Beamer","Microsoft"));
        deviceRepository.save(new Device("Drucker","Dell"));
        deviceRepository.save(new Device("Drucker","Microsoft"));
        deviceRepository.save(new Device("Chromecast","Google"));
        deviceRepository.save(new Device("Chromecast","Google"));
        deviceRepository.save(new Device("Chromecast","Google"));
        deviceRepository.save(new Device("Maus","Apple"));
        deviceRepository.save(new Device("Microsoft","HP"));
        deviceRepository.save(new Device("Monitor","Medion"));
        deviceRepository.save(new Device("MusikBox","Medion"));
        Device currDevice = deviceRepository.findById(2L);
        InstallAt installAt = new InstallAt(LocalDate.now(),LocalDate.now().plusDays(2),"Der Gerät",userRepository.findById(1L),roomRepository.findById(1001L),deviceRepository.findById(2L));
        installAtRepository.save(installAt);


    }
    void addTestUsers()
    {
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

}
