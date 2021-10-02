package at.htl.boundary;

import at.htl.control.RoomRepository;
import at.htl.entities.Room;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@RequestScoped
@Path("/rooms")
public class RoomEndpoint {
    @Inject
    RoomRepository roomRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRooms(){
        List<Room> rooms = Room.listAll();
        return Response.ok(roomRepository.findAllRooms()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
        return Room.findByIdOptional(id)
                .map(room -> Response.ok(room).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoom (Room room, @Context UriInfo info){
        if(room == null) {
            return Response.status(400).header("reason", "room-id is missing or out of range (>0)").build();
        }
        room = roomRepository.save(room);
        UriBuilder uriBuilder = info
                .getAbsolutePathBuilder()
                .path(Long.toString(room.getRoomNumber()));
        return Response.created(uriBuilder.build()).build();
    }
}
