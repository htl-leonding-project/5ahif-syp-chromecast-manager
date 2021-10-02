package at.htl.boundary;

import at.htl.control.RoomRepository;
import at.htl.entities.Room;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@RequestScoped
@Path("room")
public class RoomEndpoint {
    @Inject
    RoomRepository roomRepository;

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
