package interceptors;

import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 11/2/2016.
 */
//@Provider
public class CORSInterceptor implements PostProcessInterceptor {

    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "*";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Origin, Content-Type, X-Auth-Token";


    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "true";

    private static final String ACCESS_CONTROL_ALLOW_METHODS = "GET,POST,PUT,DELETE,OPTIONS,HEAD";


    public void postProcess(ServerResponse response) {

        System.out.println("1234");

        MultivaluedMap<String, Object> headers = response.getMetadata();

        List<Object> cors1 = new ArrayList<Object>();
        cors1.add(ACCESS_CONTROL_ALLOW_ORIGIN);

        List<Object> cors2 = new ArrayList<Object>();
        cors2.add(ACCESS_CONTROL_ALLOW_HEADERS);

        List<Object> cors3 = new ArrayList<Object>();
        cors3.add(ACCESS_CONTROL_ALLOW_CREDENTIALS);

        List<Object> cors4 = new ArrayList<Object>();
        cors4.add(ACCESS_CONTROL_ALLOW_METHODS);

        headers.put("Access-Control-Allow-Origin", cors1);
        headers.put("Access-Control-Allow-Headers", cors2);
        headers.put("Access-Control-Allow-Credentials", cors3);
        headers.put("Access-Control-Allow-Methods", cors4);

        System.out.println(headers);
    }

}
