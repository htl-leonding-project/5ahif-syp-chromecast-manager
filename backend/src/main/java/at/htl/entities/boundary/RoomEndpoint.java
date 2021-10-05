package at.htl.entities.boundary;

import at.htl.entities.control.RoomRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/rooms")
public class RoomEndpoint {
    @Inject
    RoomRepository roomRepository;

    @GET
    public Response findAll(){
        return Response.ok(roomRepository.findAll()).build();
    }
}
