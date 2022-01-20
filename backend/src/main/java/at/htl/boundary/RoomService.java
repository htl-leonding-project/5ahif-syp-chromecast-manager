package at.htl.boundary;

import at.htl.control.RoomRepository;
import at.htl.entities.Room;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/api")
public class RoomService {
    @Inject
    RoomRepository roomRepository;

    @Inject
    Logger logger;

    @GET
    @Path("/rooms")
    public Response findAllRooms(){
        return Response.ok(roomRepository.findAllRooms()).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/room/{id}")
    public Response getRoomById(@PathParam("id") Long id){
        Room room;
        room = roomRepository.findById(id);

        if(room != null){
            return Response.ok(room).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createRoom(@FormParam("roomNumber") int roomNumber
            ,@FormParam("roomName") String roomName)
    {
        Room newRoom = new Room(roomNumber,roomName);
        newRoom = roomRepository.save(newRoom);

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
    public Response updateRoom(@PathParam("id") Long roomId, JsonValue jsonValue)
    {
        var job = jsonValue.asJsonObject();
        Room room = roomRepository.update(roomId,
                job.getInt("roomNumber"),
                job.getString("roomName"));
        return Response.ok(room).build();
    }
}
