package at.htl.control;

import at.htl.entities.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Transactional
public class RoomRepository implements PanacheRepository<Room> {
    /**
     * Use the method 'merge' of the EntityManager to persist the entity
     * - when the object doesn't exist in the database or the id is null, a new database entry is created (persist)
     * - when the object already exists in the database, it will be updated
     *
     * @param roomToSave - object to persist
     * @return the managed instance of the object
     */
    public Room save(Room roomToSave) {
        return getEntityManager().merge(roomToSave);
    }

    public List<Room> findAllRooms(){
        return Collections.unmodifiableList(listAll(Sort.by("ROOM_NUMBER")));
    }
}
