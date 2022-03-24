package at.htl.boundary;

import at.htl.control.DeviceRepository;
import at.htl.control.RoomRepository;
import at.htl.entities.Device;
import at.htl.entities.Room;
import jdk.dynalink.linker.LinkerServices;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/device")
public class DeviceService {
    @Inject
    DeviceRepository deviceRepository;

    @Inject
    Logger logger;

    @GET
    @Path("/devices")
    public Response findAllDevices(){
        return Response.ok(deviceRepository.findAllDevices()).build();
    }

    @GET
    @Path("/sortByCategories/{category}")
    public Response sortedByCategories(@PathParam("category") String category){
        return Response.ok(deviceRepository.searchByCategory(category)).build();
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
    @Path("/create-device")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDevice(JsonObject device)
    {
        var deviceToAdd = new Device(device.getString("name"),device.getString("brand"),device.getString("ean")
                ,device.getString("category"));
        Device newDevice = deviceRepository.save(deviceToAdd);
        deviceRepository.save(newDevice);

        logger.infof("Device created: %s",newDevice.getName());
        return Response.ok(newDevice).build();
    }
    @DELETE
    @Path("delete-device/{id}")
    public Response deleteDevice(@PathParam("id") Long id){
        Device d = deviceRepository.delete(id);
        return Response.ok(d.getName()).build();

    }
    @PUT
    @Path("update-device/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDevice(@PathParam("id") Long id,JsonObject jsonObject)
    {
        Device device = deviceRepository.update(id,jsonObject.getString("name"),
                jsonObject.getString("name"));
        return Response.ok(device).build();
    }
    @GET
    @Path("/freeDevices")
    public Response findFreeDevices(){

        List<Device> freeDevices = deviceRepository.findFreedevices();
        return Response.ok(freeDevices).build();
    }
}
