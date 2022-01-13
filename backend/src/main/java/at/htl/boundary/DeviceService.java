package at.htl.boundary;

import at.htl.control.DeviceRepository;
import at.htl.control.RoomRepository;
import at.htl.entities.Device;
import at.htl.entities.Room;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/api")
public class DeviceService {
    @Inject
    DeviceRepository deviceRepository;

    @Inject
    Logger logger;

    @GET
    @Path("devices")
    public Response findAllRooms(){
        return Response.ok(deviceRepository.findAll()).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("device/{id}")
    public Response getDeviceById(@PathParam("id") Long id){
        Device device;
        device = deviceRepository.findById(id);

        if(device != null){
            return Response.ok(device).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createDevice(@FormParam("name") String name
            , @FormParam("brand") String brand)
    {
        Device newDevice = new Device(name,brand);
        deviceRepository.save(newDevice);

        logger.infof("Device created: %s",newDevice.getName());
        return Response.ok(newDevice).build();
    }
    @DELETE
    @Path("device/{id}")
    public Response deleteDevice(@PathParam("id") Long id){
        Device d = deviceRepository.delete(id);
        return Response.ok(d.getName()).build();

    }





}
