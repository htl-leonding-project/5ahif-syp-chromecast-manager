package at.htl.control;


import at.htl.entities.Room;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class InitBean {

    private String fileName = "room.xlsx";

    @Inject
    RoomRepository roomRepository;


    @Transactional
    void onStartUp(@Observes StartupEvent event)
    {
        List<Room> rooms = roomRepository.readCSV("");
        for (Room r: rooms) {
            roomRepository.save(r);
        }
    }

}
