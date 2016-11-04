package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by User on 11/3/2016.
 */
@Provider
public class BussinessExceptionMapper implements ExceptionMapper<Exception> {

    public Response toResponse(Exception e) {
        e.printStackTrace();
        System.out.println("Exception Mapper Genric BOYZ ");
        return Response.status(500).entity(new GenericException("An unexpected error has occured")).build();
    }
}
