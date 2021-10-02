package at.htl.control;

import at.htl.entities.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

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
    @Transactional
    public Room save(Room roomToSave) {
        return getEntityManager().merge(roomToSave);
    }

    @Transactional
    public void generateRoom() {
        Room room = new Room("XXX");
        save(room);
    }
}
