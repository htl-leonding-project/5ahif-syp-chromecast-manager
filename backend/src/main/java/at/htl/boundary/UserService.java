<<<<<<< HEAD
package at.htl.boundary;


import at.htl.control.UserRepository;
import at.htl.entities.Room;
import at.htl.entities.User;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/api")
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
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createRoom(@FormParam("name") String name
            ,@FormParam("passwordHash") String passwordHash)
    {
        User newUser = new User(name,passwordHash);
        newUser = userRepository.save(newUser);

        logger.infof("User created: %s",newUser.getName());
        return Response.ok(newUser).build();
    }

}
=======
package at.htl.boundary;


import at.htl.control.UserRepository;
import at.htl.entities.Room;
import at.htl.entities.User;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/users")
public class UserService {


    @Inject
    UserRepository userRepository;

    @Inject
    Logger logger;

    @GET
    @Path("/allUsers")
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
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createRoom(@FormParam("name") String name
            ,@FormParam("passwordHash") String passwordHash)
    {
        User newUser = new User(name,passwordHash);
        newUser = userRepository.save(newUser);

        logger.infof("User created: %s",newUser.getName());
        return Response.ok(newUser).build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateRoom(@PathParam("id") Long userId, JsonValue jsonValue)
    {
        var job = jsonValue.asJsonObject();
        User user = userRepository.update(userId,
                job.getString("name"),
                job.getString("passwordHash"));
        return Response.ok(user).build();
    }

}
>>>>>>> parent of c8d4889... new-Branch commit
