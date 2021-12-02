package at.htl.control;

import at.htl.entities.Room;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class RoomRepositoryTest {


    @Inject RoomRepository roomRepository;



    @DisplayName("Returns the method 'readFile()' a list with 70 names?")
    @Test
    void t200_readFile() {
        List<Room> rooms = roomRepository.readCSV("rooms.csv");
        assertThat(rooms).hasSize(62);
    }



}
