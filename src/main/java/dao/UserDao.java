package dao;

import model.User;
import to.LoginRequest;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

/**
 * Created by User on 11/1/2016.
 */
@Remote
public interface UserDao {

    Integer insertUser(User user);

    User getUserById(int id);

    User getUserByEmail(String email);

    User updateUser(User User);

    User getUserByLoginRequest(LoginRequest loginRequest);

    }
