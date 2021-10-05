package at.htl.entities.control;

import at.htl.entities.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class RoomRepository implements PanacheRepository<Room> {
    public Room save(Room roomToSave) {
        return getEntityManager().merge(roomToSave);
    }
}
