package at.htl.entities;

import at.htl.control.DeviceRepository;
import at.htl.control.RoomRepository;
import at.htl.control.UserRepository;
import io.smallrye.common.constraint.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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


    @Order(001)
    @Test
    public void test_001_createInstallAT() {
        //arrange
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
}

