package at.htl.control;

import at.htl.entities.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    public List<Room> findAllRooms(){
        return Collections.unmodifiableList(listAll(Sort.by("ROOMNUMBER")));
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
                        String [] splitted = line.split(";"); return new Room(Integer.parseInt(splitted[0]),"");
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
}
