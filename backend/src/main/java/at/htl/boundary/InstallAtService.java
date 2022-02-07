package at.htl.boundary;


import at.htl.control.InstallAtRepository;
import at.htl.entities.Device;
import at.htl.entities.InstallAt;
import at.htl.entities.Room;
import at.htl.entities.User;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequestScoped
@Path("/installAt")
public class InstallAtService {

    @Inject
    InstallAtRepository installAtRepository;


    @Inject
    Logger logger;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");


    @POST
    @Path("/create-installat")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInstallAt(JsonObject installAt)
    {

        var installAtToAdd = new InstallAt(LocalDate.parse(installAt.getString("installDate"),formatter),
                LocalDate.parse(installAt.getString("removeDate"),formatter), installAt.getString("description"),new User(),new Room(),new Device());
        var newInstallAt = installAtRepository.save(installAtToAdd);
        // info
        logger.infof("InstallAt created: %d",newInstallAt.getId());
        return Response.ok(newInstallAt).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deleteInstallAt(@PathParam("id") Long id)
    {
        InstallAt installAt = installAtRepository.delete(id);
        return Response.ok(installAt).build();
    }

//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/update/{name}")
//    public Response updateRoom(@PathParam("name") String roomName, JsonObject jsonObject)
//    {
//
//        Room room = roomRepository.update(roomName,
//                jsonObject.getInt("roomNumber"),
//                jsonObject.getString("roomName"));
//        return Response.ok(room).build();
//    }


}
