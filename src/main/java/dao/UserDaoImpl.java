package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import sql.Queries;
import to.LoginRequest;

import javax.ejb.*;
import javax.persistence.PersistenceContext;

/**
 * Created by User on 11/1/2016.
 */
@Stateless
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private Session session;

    public UserDaoImpl() {

    }

    public Integer insertUser(User user){
        Integer id = (Integer) session.save(user);
        return id;
    }

    public User getUserById(int id){
        User user = (User) session.get(User.class, id);
        return user;
    }

    public User getUserByEmail(String email){
        Query query = session.getNamedQuery(Queries.FIND_USER_BY_EMAIL)
                        .setString("email", email);
        return (User) query.uniqueResult();
    }

    public User updateUser(User user){
        user = (User) session.get(User.class, user.getUserId());
        user.setConfirmed(true);
        return user;
    }

    public User getUserByLoginRequest(LoginRequest loginRequest){
        Query query = session.getNamedQuery(Queries.FIND_USER_BY_LOGIN_REQUEST)
                        .setString("email", loginRequest.getEmail())
                        .setString("password", loginRequest.getPassword());

        User user = (User) query.uniqueResult();

        System.out.println(user);
        return user;
    }

}
