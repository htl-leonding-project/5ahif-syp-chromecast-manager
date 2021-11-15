package at.htl.entities;

import at.htl.control.DeviceRepository;
import at.htl.control.InstallAtRepository;
import at.htl.control.RoomRepository;
import at.htl.control.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceTest {
    private DeviceRepository deviceRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    private InstallAtRepository installAtRepository;

    private User user;
    private Room room;
    private Device device;
    private List<InstallAt> installAtList;

    @BeforeEach
    public void onInit()
    {
        deviceRepository = new DeviceRepository();
        roomRepository = new RoomRepository();
        userRepository = new UserRepository();
        installAtRepository = new InstallAtRepository();

        device = deviceRepository.getDeviceDummy();
        room = roomRepository.getRoomDummy();
        user = userRepository.getUserDummy();
        installAtList = installAtRepository.getInstallAtListDummy();
    }


    @Test
    @Order(1)
    public void test_000_AllFieldsNotNull() {
        //assert
        assertNotNull(device);
        assertNotNull(room);
        assertNotNull(user);

        assertNotNull(deviceRepository);
        assertNotNull(roomRepository);
        assertNotNull(userRepository);
    }

    @Test
    @Order(2)
    public void test_001_createNewDevice(){
        //arrange
        Device device1 = new Device("Beamer","Epson");

        Device device2 = new Device();
        device2.setName("Monitor");
        device2.setBrand("iiyama");

        Device device3 = mock(Device.class);

        //act
        when(device3.getName()).thenReturn("Mouse");
        when(device3.getBrand()).thenReturn("Razer");

        //assert
        assertEquals(device1.getName(), "Beamer");
        assertEquals(device1.getBrand(), "Epson");

        assertEquals(device2.getName(), "Monitor");
        assertEquals(device2.getBrand(), "iiyama");

        assertEquals(device3.getName(), "Mouse");
        assertEquals(device3.getBrand(), "Razer");
    }

    @Test
    @Order(2)
    public void test_002_saveEmptyDevice(){
        //arrange
        Device device1 = null;
        Exception exception;
        String expectedMessage = "Device to save is null";
        //act
        exception = assertThrows(Exception.class, () -> {
            deviceRepository.save(device1);
        });
        //assert
        assertEquals(exception.getMessage(), expectedMessage);
    }
}