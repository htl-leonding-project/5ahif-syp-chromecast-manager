package at.htl.control;

import at.htl.entities.Room;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoomRepositoryTest {
    @Inject
    RoomRepository roomRepository;

    @Inject
    AgroalDataSource ds;


    @ConfigProperty(name = "table.room")
    String roomTable;


    @BeforeEach
    @Transactional
    public void onInit(){
        roomRepository = new RoomRepository();
        roomRepository.deleteAll(); //does not work somehow
    }

    @DisplayName("Returns the method 'readFile()' a list with 70 names?")
    @Test
    void t200_readFile() {
        List<Room> rooms = roomRepository.readCSV("rooms.csv");
        assertThat(rooms).hasSize(62);
    }

    @DisplayName("Is the table-structure of H_ROOM correct?")
    @Test
    void t210_isTableStructureRoomCorrect() {

        Table table = new Table(ds, roomTable);
        org.assertj.db.api.Assertions.assertThat(table)
                .hasNumberOfColumns(3)
                .column(0).hasColumnName("R_ID")
                .column(1).hasColumnName("R_NAME")
                .column(2).hasColumnName("R_NUMBER");
    }



    @Order(001)
    @Test
    @Transactional
    void test_001_persistRoom(){
        //arrange
        Room room = new Room(1000, "TestRoom01");

        //act
        roomRepository.save(room);
        roomRepository.save(room);

        //assert
        Table table = new Table(ds, "HTL_ROOM");
        org.assertj.db.api.Assertions.assertThat(table).hasNumberOfRows(1);
        org.assertj.db.api.Assertions.assertThat(table)
                //There were alread rooms created (InitBean Id's: 1000-1061)
                .column("R_ID").value().isEqualTo(1062)
                .column("R_NUMBER").value().isEqualTo(1000)
                .column("R_NAME").value().isEqualTo("TestRoom01");
    }

    @Order(002)
    @Test
    @Transactional
    void test_002_persist2DifferentRoomsWithSameNameAndOrRoomNumber(){
        //arrange
        Room room1 = new Room(1000, "TestRoom01");
        Room room2 = new Room(1000, "TestRoom01");

        //act
        roomRepository.save(room1);
        roomRepository.save(room2);

        room2.setRoomName("TestRoom02");
        roomRepository.save(room2);

        room2.setRoomName("TestRoom01");
        room2.setRoomNumber(1001);
        roomRepository.save(room2);

        //assert
        Table table = new Table(ds, "HTL_ROOM");
        org.assertj.db.api.Assertions.assertThat(table).hasNumberOfRows(1);
    }

    @Order(003)
    @Test
    @Transactional
    void test_003_persistRoomWithInstallAt(){
        //todo
    }

    @Order(004)
    @Test
    @Transactional
    void test_004_persistRoomAndDevice(){

    }
}
