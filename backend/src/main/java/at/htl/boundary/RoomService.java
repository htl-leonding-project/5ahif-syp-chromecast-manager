package at.htl.boundary;

import at.htl.control.RoomRepository;
import at.htl.entities.Room;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/rooms")
public class RoomService {
    @Inject
    RoomRepository roomRepository;

    @Inject
    Logger logger;

    @GET
    public Response findAll(){
        return Response.ok(roomRepository.findAll()).build();
    }



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createRoom(@FormParam("roomNumber") int roomNumber
            ,@FormParam("roomName") String roomName)
    {
        Room newRoom = new Room(roomNumber,roomName);
        roomRepository.save(newRoom);

        logger.infof("Room created: %s",newRoom.getRoomName());
        return Response.ok(newRoom).build();
    }



}
