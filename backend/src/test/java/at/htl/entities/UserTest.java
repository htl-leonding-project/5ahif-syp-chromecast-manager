package at.htl.entities;

import at.htl.control.DeviceRepository;
import at.htl.control.InstallAtRepository;
import at.htl.control.RoomRepository;
import at.htl.control.UserRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
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

}