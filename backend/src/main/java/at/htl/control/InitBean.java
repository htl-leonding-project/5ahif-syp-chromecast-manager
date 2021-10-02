package at.htl.control;

import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class InitBean {


    @Inject
    Logger log;

    @Inject
    RoomRepository roomRepository;

    void onStartup(@Observes StartupEvent event) {
        log.info("fill table Rooms ...");
        roomRepository.generateRoom();
    }

}
