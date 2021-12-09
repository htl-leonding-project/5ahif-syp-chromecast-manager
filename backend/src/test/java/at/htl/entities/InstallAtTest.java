package at.htl.entities;

import at.htl.control.DeviceRepository;
import at.htl.control.RoomRepository;
import at.htl.control.UserRepository;
import io.smallrye.common.constraint.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.lang.reflect.InaccessibleObjectException;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InstallAtTest {
    @Inject
    private UserRepository userRepository;
    @Inject
    private RoomRepository roomRepository;
    @Inject
    private DeviceRepository deviceRepository;

    private User user;
    private Room room;
    private Device device;
    private InstallAt installAt;
    private LocalDate installDate;
    private LocalDate removeDate;


    @BeforeEach
    public void onInit()
    {
        user = new User("Moritz","Deadlift123");
        room = new Room(91,"K05");
        device = new Device("Chromecast","Google");

        installDate = LocalDate.now();
        removeDate = LocalDate.now().plusDays(3);
        installAt = new InstallAt(installDate, removeDate, "Test",user,room,device);

    }


    @Order(1)
    @Test
    public void test_001_createInstallAT() {
        //assert
        Assert.assertNotNull(user);
        Assert.assertNotNull(room);
        Assert.assertNotNull(device);

        Assert.assertNotNull(installAt);
        Assert.assertNotNull(installAt.getUser());
        Assert.assertNotNull(installAt.getRoom());
        Assert.assertNotNull(installAt.getDevice());

        assertThat(installAt.getInstallDate()).isEqualTo(installDate);
        assertThat(installAt.getRemoveDate()).isEqualTo(removeDate);
    }

    @Test
    @Order(2)
    public void test_002_checkRemoveDate() {
        //arrange
        InstallAt installAt1 = new InstallAt(LocalDate.now(),LocalDate.now().minusDays(1),"Test",user,room,device);
        InstallAt installAt2 = new InstallAt(LocalDate.now(), null, "Test",user,room,device);
        InstallAt installAt3 = installAt;
        installAt3.setRemoveDate(LocalDate.now().minusDays(1));
        InstallAt installAt4 = installAt1;

        //act
        boolean isNull1 = installAt1.getRemoveDate() == null;
        boolean isNull2 =  installAt2.getRemoveDate() == null;
        boolean isNull3 =  installAt3.getRemoveDate() == null;
        installAt4.setRemoveDate(LocalDate.now().minusDays(1));
        boolean isNull4 = installAt4.getRemoveDate() == null;

        //assert
        Assert.assertTrue(isNull1);
        Assert.assertTrue(isNull2);
        Assert.assertTrue(isNull3);
        Assert.assertTrue(isNull4);
    }

    @Test
    @Order(3)
    public void test_003_checkInstallDate() {
        InstallAt installAt = new InstallAt(LocalDate.now().plusDays(3),LocalDate.now().plusDays(2),"Test",user,room,device);

        boolean isNull = installAt.getRemoveDate() == null;

        Assert.assertTrue(isNull);
    }

    @Test
    @Order(4)
    public void test_004_installAt(){
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

