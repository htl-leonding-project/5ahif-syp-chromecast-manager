package at.htl.control;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class RoomRepositoryTest {


    @Inject RoomRepository roomRepository;
}
