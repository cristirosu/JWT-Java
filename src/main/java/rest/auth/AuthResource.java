package rest.auth;

import com.auth0.jwt.internal.org.apache.commons.lang3.ObjectUtils;
import ejb.EmailSender;
import ejb.EmailType;
import exceptions.RPGException;
import model.User;
import to.LoginRequest;
import to.LoginResponse;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.transaction.*;
import javax.transaction.RollbackException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by User on 11/1/2016.
 */
@Path("/authentication")
@Consumes("application/json")
@Produces("application/json")
public class AuthResource {

    @Inject
    private AuthService authService;

    @Inject
    private EmailSender emailSender;

    @POST
    @Path("/login")
    public Response login(LoginRequest loginRequest) throws RPGException {
        System.out.println(emailSender);
        String token = authService.login(loginRequest);

        System.out.println("token = " + token);

        return Response.ok(new LoginResponse(token)).build();
    }

    @POST
    @Path("/register")
    public Response register(User user) throws NamingException, SQLException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException, RPGException {
        System.out.println(user);

        if(authService.emailAlreadyExists(user.getEmail())){
            throw new RPGException(409,"Email already exists");
        }

        Integer userId = authService.register(user);
        user.setUserId(userId);


        emailSender.sendEmail(EmailType.ACTIVATION_EMAIL, user);
        return Response.status(200).build();
    }

    @GET
    @Path("/activation/{id}")
    public Response activate(@PathParam("id") Integer id){
        authService.activateUser(id);
        return Response.status(200).build();
    }

    @GET
    @Path("/private")
    public Response privat(){
        return Response.status(200).build();
    }

}
