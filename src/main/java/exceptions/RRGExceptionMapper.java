package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by User on 11/2/2016.
 */
@Provider
public class RRGExceptionMapper implements ExceptionMapper<RPGException> {

    public Response toResponse(RPGException e) {
        System.out.println("RPG EXCEPTION BOYZ ");
        System.out.println(e.getErrorMessage());
        return Response.status(e.getStatus()).entity(new GenericException(e.getStatus(),e.getErrorMessage())).build();
    }
}
