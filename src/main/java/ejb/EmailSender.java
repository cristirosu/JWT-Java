package ejb;

import model.User;

import javax.ejb.Local;

/**
 * Created by User on 11/4/2016.
 */
@Local
public interface EmailSender {

    void sendEmail(EmailType emailType, User user);

}
