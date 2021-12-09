package at.htl.control;

import at.htl.entities.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
@Transactional
public class RoomRepository implements PanacheRepositoryBase<Room, Long> {
    /**
     * Use the method 'merge' of the EntityManager to persist the entity
     * - when the object doesn't exist in the database or the id is null, a new database entry is created (persist)
     * - when the object already exists in the database, it will be updated
     *
     * @param roomToSave - object to persist
     * @return the managed instance of the object
     */
    public Room save(Room roomToSave) {
        if (roomToSave == null)
        {
            throw new NullPointerException("Room to save is null");
        }

        if(Collections.unmodifiableList(listAll()).size()!=0){
            for(Room currentRoom : Collections.unmodifiableList(listAll())){
                if( (roomToSave.getId() == currentRoom.getId()) || roomToSave.getRoomName().equals(currentRoom.getRoomName()) || currentRoom.getRoomNumber() == roomToSave.getRoomNumber()){
                    return currentRoom;
                }
            }
        }

        return getEntityManager().merge(roomToSave);
    }

    public Room delete(Long id)
    {
        Room room = findById(id);
        getEntityManager().remove(room);
        return room;
    }

    public Room update(Long id,int number,String name)
    {
        Room room = findById(id);
        room.setRoomName(name);
        room.setRoomNumber(number);
        getEntityManager().merge(room);
        return room;
    }

    public List<Room> readCSV(String fileName)
    {
        //URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        //assert url != null;

        try {
            Path findCSV = find("rooms.csv", ".").stream().findFirst().get();
            Stream<String> stream = Files.lines(findCSV, StandardCharsets.UTF_8);
            return stream.skip(1).map(line ->
                    {
                        String [] splitted = line.split(";"); return new Room(Integer.parseInt(splitted[0]),splitted[1]);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static Collection<Path> find(String fileName, String searchDirectory) throws IOException {
        try (Stream<Path> files = Files.walk(Paths.get(searchDirectory))) {
            return files
                    .filter(f -> f.getFileName().toString().equals(fileName))
                    .collect(Collectors.toList());

        }
    }

    public Room getRoomDummy(){
        Room room = new Room();

        room.setRoomNumber(4);
        room.setRoomName("5AHIF");

        return room;
    }
}
