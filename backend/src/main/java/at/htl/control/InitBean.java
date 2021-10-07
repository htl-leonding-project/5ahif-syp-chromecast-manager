package at.htl.control;


import at.htl.entities.Room;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class InitBean {

    private final String FILE_NAME = "rooms.csv";

    @Inject
    RoomRepository roomRepository;

    @Inject
    Logger log;

    @Transactional
    void onStartUp(@Observes StartupEvent event)
    {
        List<Room> rooms = roomRepository.readCSV(FILE_NAME);
        //log.info(rooms);
        rooms.forEach(r -> System.out.println(r));
        for (Room r: rooms) {
            roomRepository.save(r);
        }
    }

}
