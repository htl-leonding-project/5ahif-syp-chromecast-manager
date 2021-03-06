package at.htl.entities;

import at.htl.control.DeviceRepository;
import at.htl.control.InstallAtRepository;
import at.htl.control.RoomRepository;
import at.htl.control.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void test_000_globalVariablesNotNull() {
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
        //act

        //assert
        assertEquals(device1.getName(), "Beamer");
        assertEquals(device1.getBrand(), "Epson");

        assertEquals(device2.getName(), "Monitor");
        assertEquals(device2.getBrand(), "iiyama");
    }

    @Test
    @Order(3)
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

    @Test
    @Order(4)
    public void test_003_removeDevice(){
        //arrange
        Device device1 = deviceRepository.getDeviceDummy();
        //act
        deviceRepository.save(device1);
        deviceRepository.delete(device1);
        //assert
        assertEquals(deviceRepository.count(),0);
    }

    @Test
    @Order(5)
    public void test_004_removeDeviceThatDoesNotExist(){
        
    }

    @Test
    @Order(6)
    public void test_005_firstDBTest(){

    }
}