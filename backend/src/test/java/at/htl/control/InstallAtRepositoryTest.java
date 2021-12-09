package at.htl.control;

import at.htl.entities.InstallAt;
import at.htl.entities.Room;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstallAtRepositoryTest {
    @Inject
    InstallAtRepository installAtRepository;

    InstallAt installAt;

    @Inject
    AgroalDataSource ds;

    @ConfigProperty(name = "table.installat")
    String installAtTable;

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
                .column(4).hasColumnName("DEVICE_D_ID")
                .column(5).hasColumnName("ROOM_R_ID")
                .column(6).hasColumnName("USER_U_ID");
    }

    @Order(002)
    @Test
    @Transactional
    void test_002_persistInstallAt(){
        //arrange
        InstallAt installAt = installAtRepository.getInstallAtDummy();

        //act
        installAtRepository.save(installAt);
        installAtRepository.save(installAt);

        //assert
        Table table = new Table(ds, "HTL_INSTALLAT");
        org.assertj.db.api.Assertions.assertThat(table).hasNumberOfRows(1);
        table.getColumn(1).toString();
        org.assertj.db.api.Assertions.assertThat(table)
                .column("I_ID").value().isEqualTo(2000)
                .column("I_DESCRIPTION").value().isEqualTo(installAt.getDescription())
                .column("I_INSTALLDATE").value().isEqualTo(installAt.getInstallDate())
                .column("I_REMOVEDATE").value().isEqualTo(installAt.getRemoveDate())
                .column("DEVICE_D_ID").value().isEqualTo(installAt.getDevice())
                .column("ROOM_R_ID").value().isEqualTo(installAt.getRoom())
                .column("USER_U_ID").value().isEqualTo(installAt.getUser());
    }
}
