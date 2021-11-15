package at.htl.entities;


import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.common.constraint.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@QuarkusTest
public class InstallAtTest {


    private static User user;
    private static Room room;
    private static Device device;

    @BeforeAll
    public static void onInit()
    {
        user = new User("Moritz","Deadlift123");
        room = new Room(91,"K05");
        device = new Device("Chromecast","Google");

    }


    @Test
    void checkIsNull() {

        Assert.assertNotNull(user);
        Assert.assertNotNull(room);
        Assert.assertNotNull(device);

        InstallAt installAt = new InstallAt(LocalDate.now(),LocalDate.now().plusDays(3),"Test",user,room,device);

        Assert.assertNotNull(installAt);

    }

    @Test
    void checkRemoveDate() {
        InstallAt installAt = new InstallAt(LocalDate.now(),LocalDate.now().minusDays(1),"Test",user,room,device);

        boolean isNull = installAt.getRemoveDate() == null;

        Assert.assertTrue(isNull);
    }




}

