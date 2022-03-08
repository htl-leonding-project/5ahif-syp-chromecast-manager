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
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("/install")
public class InstallAtService {

    @Inject
    InstallAtRepository installAtRepository;


    @Inject
    Logger logger;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");



    @GET
    @Path("/installs")
    public Response findAllInstallAts(){
        return Response.ok(installAtRepository.findAllInstallAts()).build();
    }

    @GET
    @Path("/room/{id}")
    public Response findAllInstallAtsByRoomId(@PathParam("id") Long id){
        List<InstallAt> installAts;
        installAts = installAtRepository.findInstallAtsByRoomId(id);

        if(installAts.size() > 0){
            return Response.ok(installAts.stream().map(i -> {
                        Object dto = new Object() {
                            public long id = i.getId();
                            public String deviceName = i.getDevice().getName();
                            public String  deviceBrand = i.getDevice().getBrand();
                            public String installedFrom = i.getUser().getName();
                            public String installDate = i.getInstallDate().toString();
                        };
                        return dto;
                    }).collect(Collectors.toList())).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();



    }

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
