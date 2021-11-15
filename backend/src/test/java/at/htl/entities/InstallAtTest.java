package at.htl.entities;


import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class InstallAtTest {


   private User user;
   private Room room;
   private Device device;

    @BeforeAll
    public void onInit()
    {
        user = new User("Moritz","Deadlift123");
        room = new Room(91,"K05");
        device = new Device("Chromecast","Google");

    }


    @Test
    void name() {

    }


}
