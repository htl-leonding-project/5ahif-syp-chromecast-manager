package at.htl.boundary;


import at.htl.control.UserRepository;
import at.htl.entities.Room;
import at.htl.entities.User;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/user")
public class UserService {


    @Inject
    UserRepository userRepository;

    @Inject
    Logger logger;

    @GET
    @Path("/users")
    public Response getAllUser()
    {
        return Response.ok(userRepository.findAllUsers()).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")
    public Response getRoomById(@PathParam("id") Long id){
        User user;
        user = userRepository.findById(id);

        if(user != null){
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/create-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(JsonObject jsonObject)
    {
        User newUser = new User(jsonObject.getString("name"),jsonObject.getString("passwordHash"));
        newUser = userRepository.save(newUser);

        logger.infof("User created: %s",newUser.getName());
        return Response.ok(newUser).build();
    }

    @Path("/delete-user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String userId){

        User user = userRepository.delete(Long.parseLong(userId));
        return Response.ok(user.getName()).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update-user/{id}")
    public Response updateRoom(@PathParam("id") Long userId, JsonValue jsonValue)
    {
        var job = jsonValue.asJsonObject();
        User user = userRepository.update(userId,
                job.getString("name"),
                job.getString("passwordHash"));
        return Response.ok(user).build();
    }

}