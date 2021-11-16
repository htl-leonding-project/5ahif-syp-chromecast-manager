package at.htl.entities;


import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.common.constraint.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class InstallAtTest {


    private static User user;
    private static Room room;
    private static Device device;


    @BeforeEach
    public void onInit()
    {
        user = new User("Moritz","Deadlift123");
        room = new Room(91,"K05");
        device = new Device("Chromecast","Google");

    }


    @Test
    void test_000_IsNotNull() {
        Assert.assertNotNull(user);
        Assert.assertNotNull(room);
        Assert.assertNotNull(device);

        InstallAt installAt = new InstallAt(LocalDate.now(),LocalDate.now().plusDays(3),"Test",user,room,device);

        Assert.assertNotNull(installAt);

    }

    @Test
    void test_001_checkRemoveDate() {
        InstallAt installAt = new InstallAt(LocalDate.now(),LocalDate.now().minusDays(1),"Test",user,room,device);

        boolean isNull = installAt.getRemoveDate() == null;

        Assert.assertTrue(isNull);
    }

    @Test
    void test_002_checkInstallDate() {
        InstallAt installAt = new InstallAt(LocalDate.now().plusDays(3),LocalDate.now().plusDays(2),"Test",user,room,device);

        boolean isNull = installAt.getRemoveDate() == null;

        Assert.assertTrue(isNull);
    }









}

