import exceptions.GenericException;
import exceptions.RPGException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/message2")
@Produces(MediaType.APPLICATION_JSON)
public class MessageRestService2 {

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg) throws RPGException {

        //testInterface.say(msg);
        String result = "Restful example : " + msg;

        throw new RPGException(401, "Unauthroized");

        //return Response.status(200).entity(new GenericException(result)).build();
    }

}