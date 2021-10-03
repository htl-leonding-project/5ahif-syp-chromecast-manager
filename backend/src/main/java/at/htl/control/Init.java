package at.htl.control;

import at.htl.entities.Room;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class Init {
    //@Inject
    //Logger log;

   // @Inject
    RoomRepository roomRepository;

    void onStartup(@Observes StartupEvent event){
       // log.info("fill table Rooms ...");
        fillRooms();
    }

    @Transactional
    public void fillRooms(){
        roomRepository.save(new Room(03, "5BHIF"));
        roomRepository.save(new Room(04, "5AHIF"));

        roomRepository.save(new Room(22, "2CHITM"));
        roomRepository.save(new Room(23, "5CHIF"));
        roomRepository.save(new Room(24, "1BHITM"));
        roomRepository.save(new Room(25, "1AHITM"));
        roomRepository.save(new Room(26, "1CHIF"));
        roomRepository.save(new Room(71, "5AHITM"));
        roomRepository.save(new Room(72, "5XXXXXXXXXXHITM"));
    }
}
