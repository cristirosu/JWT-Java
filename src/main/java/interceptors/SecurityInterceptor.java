package interceptors;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import exceptions.RPGException;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.AcceptedByMethod;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import rest.auth.AuthResource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 11/1/2016.
 */
@Provider
public class SecurityInterceptor implements PreProcessInterceptor, AcceptedByMethod {

    private final String secret = "ABC784*3125*123";

    public ServerResponse preProcess(HttpRequest httpRequest, ResourceMethod resourceMethod) throws Failure, WebApplicationException {

        List<String> authHeaders = httpRequest.getHttpHeaders().getRequestHeader("Authorization");

        if(authHeaders == null){
            return new ServerResponse("Unauthorized", 401, new Headers<Object>());
        }

        String jwt = authHeaders.get(0).substring(7);

        System.out.println("JWT ===>> " +jwt);
        
        try {
            final JWTVerifier verifier = new JWTVerifier(secret);
            final Map<String, Object> claims= verifier.verify(jwt);
        } catch (JWTVerifyException e) {
            // Invalid Token
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ServerResponse("Unauthorized", 401, new Headers<Object>());
        } catch (IOException e) {
            e.printStackTrace();
            return new ServerResponse("Unauthorized", 401, new Headers<Object>());
        } catch (SignatureException e) {
            e.printStackTrace();
            return new ServerResponse("Unauthorized", 401, new Headers<Object>());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new ServerResponse("Unauthorized", 401, new Headers<Object>());
        }

        //returning null let's the message pass through the interceptor
        return null;
    }

    public boolean accept(Class aClass, Method method) {
        return !aClass.getName().equals(AuthResource.class.getName());
    }
}
