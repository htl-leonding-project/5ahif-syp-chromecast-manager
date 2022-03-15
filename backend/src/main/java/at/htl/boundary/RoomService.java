package at.htl.boundary;

import at.htl.control.InstallAtRepository;
import at.htl.control.RoomRepository;
import at.htl.entities.InstallAt;
import at.htl.entities.Room;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/room")
public class RoomService {
    @Inject
    RoomRepository roomRepository;

    @Inject
    InstallAtRepository installAtRepository;


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

//    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/getInstallsByRoom/{id}")
//    public Response getInstallsByRoomId(@PathParam("id") Long id){
//        List<InstallAt> installAts = installAtRepository.findAllInstallsById(id);
//
//        if (installAts == null){
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        installAts.forEach(i -> System.out.println(i.toString()));
//        return Response.ok(installAts).build();
//    }


    @POST
    @Path("/create-room")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoom(JsonObject room)
    {
        var roomToAdd = new Room(Integer.parseInt(room.getString("roomNumber")),room.getString("roomName"));
        var newRoom = roomRepository.save(roomToAdd);
        // info
        logger.infof("Room created: %s",newRoom.getRoomName());
        return Response.ok(newRoom).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/{name}")
    public Response deleteRoom(@PathParam("name") String roomName)
    {
        long roomId = roomRepository.findIdByName(roomName);
        installAtRepository.deleteByRoomId(roomId);
        Room room = roomRepository.delete(roomName);
        return Response.ok(room.getRoomName()).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{name}")
    public Response updateRoom(@PathParam("name") String roomName, JsonObject jsonObject)
    {

        Room room = roomRepository.update(roomName,
                jsonObject.getInt("roomNumber"),
                jsonObject.getString("roomName"));
        return Response.ok(room).build();
    }
}
