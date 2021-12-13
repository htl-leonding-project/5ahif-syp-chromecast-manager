package at.htl.control;

import at.htl.entities.Device;
import at.htl.entities.InstallAt;
import at.htl.entities.Room;
import at.htl.entities.User;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.assertj.db.output.Outputs.output;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstallAtRepositoryTest {
    @Inject
    InstallAtRepository installAtRepository;
    @Inject
    RoomRepository roomRepository;
    @Inject
    DeviceRepository deviceRepository;
    @Inject
    UserRepository userRepository;

    InstallAt installAt;

    @Inject
    AgroalDataSource ds;

    @BeforeEach
    public void init(){
        installAtRepository = new InstallAtRepository();

        installAt = installAtRepository.getInstallAtDummy();
    }

    @Order(001)
    @Test
    void test_001_isTableStructureInstallAtCorrect() {

        Table table = new Table(ds,"HTL_INSTALLAT");
        org.assertj.db.api.Assertions.assertThat(table)
                .hasNumberOfColumns(7)
                .column(0).hasColumnName("I_ID")
                .column(1).hasColumnName("I_DESCRIPTION")
                .column(2).hasColumnName("I_INSTALLDATE")
                .column(3).hasColumnName("I_REMOVEDATE")
                .column(4).hasColumnName("I_D_ID")
                .column(5).hasColumnName("I_R_ID")
                .column(6).hasColumnName("I_U_ID");
    }

    @Order(003)
    @Test
    @Transactional
    void test_003_persistMultipleDevicesWithSameInstallAt(){
        Device d1 = new Device("Apple", "Monitor");

        InstallAt installAt1= new InstallAt(LocalDate.now(), null , "installation1", null, null, d1);
        InstallAt installAt2= new InstallAt(LocalDate.now(), null , "installation2", null, null, d1);

        installAtRepository.save(installAt1);
        installAtRepository.save(installAt2);

        Table table = new Table(ds, "HTL_DEVICE");
        org.assertj.db.api.Assertions.assertThat(table).hasNumberOfRows(1);
    }

    @Order(002)
    @Test
    @Transactional
    void test_002_persistInstallAtWithRelationalEntities(){
        //arrange
        Room room = roomRepository.getRoomDummy();
        Device device = deviceRepository.getDeviceDummy();
        User user = userRepository.getUserDummy();
        InstallAt installAt = installAtRepository.getInstallAtDummy();
        installAt.setRoom(room);
        installAt.setDevice(device);
        installAt.setUser(user);


        //act
        installAtRepository.save(installAt);
        installAtRepository.save(installAt);

        //assert
        Table table = new Table(ds, "HTL_INSTALLAT");
        output(table).toConsole();
        org.assertj.db.api.Assertions.assertThat(table).hasNumberOfRows(1);
        table.getColumn(1).toString();
        org.assertj.db.api.Assertions.assertThat(table)
                .column("I_ID").value().isEqualTo(2000)
                .column("I_DESCRIPTION").value().isEqualTo(installAt.getDescription())
                .column("I_INSTALLDATE").value().isEqualTo(installAt.getInstallDate())
                .column("I_REMOVEDATE").value().isEqualTo(installAt.getRemoveDate())
                .column("I_D_ID").value().isEqualTo(2L)
                .column("I_R_ID").value().isEqualTo(1028)
                .column("i_U_ID").value().isEqualTo(1L);
    }
}
