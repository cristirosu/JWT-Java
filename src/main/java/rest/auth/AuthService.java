package rest.auth;

import com.auth0.jwt.JWTSigner;
import dao.UserDao;
import exceptions.RPGException;
import model.User;
import org.hibernate.Query;
import to.LoginRequest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashMap;

/**
 * Created by User on 11/1/2016.
 */
@RequestScoped
public class AuthService {

    @EJB
    private UserDao userDao;

    public AuthService() {

    }

    public String login(LoginRequest loginRequest) throws RPGException {
        User user = userDao.getUserByLoginRequest(loginRequest);

        if(user == null){
            throw new RPGException(404,"Incorrect username or password");
        }

        if(!user.getConfirmed()){
            throw new RPGException(403,"Please confirm your email before logging in");
        }

        System.out.println(user);

        final String issuer = "https://cristi.red/";
        final String secret = "ABC784*3125*123";

        final long iat = System.currentTimeMillis() / 1000L; // issued at claim
        final long exp = iat + 600000000L; // expires claim. In this case the token expires in 60 seconds
        final int userId = user.getUserId();

        final JWTSigner signer = new JWTSigner(secret);
        final HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("iss", issuer);
        claims.put("exp", exp);
        claims.put("iat", iat);
        claims.put("userId", userId);

        String jwt = signer.sign(claims);

        return jwt;
    }

    public Integer register(User user){
        return userDao.insertUser(user);
    }

    public void activateUser(Integer id){
        User user = userDao.getUserById(id);
        userDao.updateUser(user);
    }

    public Boolean emailAlreadyExists(String email){
        User user = userDao.getUserByEmail(email);
        return user != null;
    }

}
