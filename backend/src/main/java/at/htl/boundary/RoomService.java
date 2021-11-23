package at.htl.boundary;

import at.htl.control.RoomRepository;
import at.htl.entities.Room;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
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

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deleteRoom(@PathParam("id") String roomId)
    {
        Room room = roomRepository.delete(Long.parseLong(roomId));
        return Response.ok(room.getRoomName()).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateRoom(@PathParam("id") String roomId, String number,String name)
    {
        Room room = roomRepository.update(Long.parseLong(roomId),Integer.parseInt(number),name);
        return Response.ok(room.getRoomName()).build();
    }

}
