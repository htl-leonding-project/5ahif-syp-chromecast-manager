package at.htl.control;

import at.htl.control.InitBean;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class InitDbTest {

    @Inject
    InitBean initBean;

    @Inject
    AgroalDataSource source;

    @ConfigProperty(name = "table.room")
    String roomTable;

    @DisplayName("HTL_ROOM")
    @Test
    void t100_countRowsInRoom() {
        Table table = new Table(source, roomTable);
        output(table).toConsole();
        assertThat(table)
                .hasNumberOfRows(62);
    }

}
