package at.htl.control;

import at.htl.entities.Room;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoomRepositoryTest {
    @Inject
    RoomRepository roomRepository;

    @Inject
    AgroalDataSource ds;

    @BeforeEach
    @Transactional
    public void onInit(){
        roomRepository = new RoomRepository();
        roomRepository.deleteAll(); //does not work somehow
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
    void test_002_persist2DifferentRoomsWithSameName(){
        //arrange
        Room room1 = new Room(1000, "TestRoom01");
        Room room2 = new Room(1000, "TestRoom02");

        //act
        roomRepository.save(room1);
        roomRepository.save(room2);

        //assert
        Table table = new Table(ds, "HTL_ROOM");
        org.assertj.db.api.Assertions.assertThat(table).hasNumberOfRows(1);
    }

    @Order(003)
    @Test
    @Transactional
    void test_003_(){
        //todo
    }
}
