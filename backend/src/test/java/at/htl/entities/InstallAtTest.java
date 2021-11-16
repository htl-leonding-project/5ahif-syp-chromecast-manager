package at.htl.entities;

import io.smallrye.common.constraint.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InstallAtTest {
    private static User user;
    private static Room room;
    private static Device device;
    private static InstallAt installAt;

    @BeforeEach
    public void onInit()
    {
        user = new User("Moritz","Deadlift123");
        room = new Room(91,"K05");
        device = new Device("Chromecast","Google");
    }


    @Test
    @Order(1)
    public void test_000_IsNotNull() {
        //arrange
        InstallAt installAt = new InstallAt(LocalDate.now(),LocalDate.now().plusDays(3),"Test",user,room,device);

        //assert
        Assert.assertNotNull(user);
        Assert.assertNotNull(room);
        Assert.assertNotNull(device);

        Assert.assertNotNull(installAt);
    }

    @Test
    @Order(2)
    public void test_001_checkRemoveDate() {
        InstallAt installAt = new InstallAt(LocalDate.now(),LocalDate.now().minusDays(1),"Test",user,room,device);

        boolean isNull = installAt.getRemoveDate() == null;

        Assert.assertTrue(isNull);
    }

    @Test
    @Order(3)
    public void test_002_checkInstallDate() {
        InstallAt installAt = new InstallAt(LocalDate.now().plusDays(3),LocalDate.now().plusDays(2),"Test",user,room,device);

        boolean isNull = installAt.getRemoveDate() == null;

        Assert.assertTrue(isNull);
    }

    @Test
    @Order(4)
    public void test_003_installAt(){
        //arragnge
        InstallAt installAt1 = new InstallAt(
                LocalDate.of(2021, Month.JANUARY, 1),
                LocalDate.of(2021, Month.JANUARY, 11),
                "test1",
                user,
                room,
                device);

        InstallAt installAt2 = new InstallAt();
        installAt2.setInstallDate(LocalDate.of(2021, Month.FEBRUARY, 2));
        installAt2.setRemoveDate(LocalDate.of(2021, Month.FEBRUARY, 12));
        installAt2.setDescription("test2");
        installAt2.setUser(user);
        installAt2.setRoom(room);
        installAt2.setDevice(device);

        InstallAt installAt3 = mock(InstallAt.class);

        //act
        when(installAt3.getInstallDate()).thenReturn(LocalDate.of(2021, Month.MARCH, 3));
        when(installAt3.getRemoveDate()).thenReturn(LocalDate.of(2021, Month.MARCH, 13));
        when(installAt3.getDescription()).thenReturn("test3");
        when(installAt3.getUser()).thenReturn(user);
        when(installAt3.getRoom()).thenReturn(room);
        when(installAt3.getDevice()).thenReturn(device);

        //assert
        assertEquals(installAt1.getDescription(),"test1");
        assertEquals(installAt2.getDescription(),"test2");

        assertEquals(installAt3.getDescription(),"test3");
        assertEquals(installAt3.getUser(),user);
        assertEquals(installAt3.getRoom(),room);
        assertEquals(installAt3.getDevice(),device);
    }




}

