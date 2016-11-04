package ejb;

import model.User;

/**
 * Created by User on 11/4/2016.
 */
public class EmailHelper {

    public String getEmailContent(EmailType emailType, User user){
        switch (emailType) {
            case ACTIVATION_EMAIL: {
                return "http://localhost:8080/rpg/rest/authentication/activation/" + user.getUserId();
            }
            default: {
                return "Empty email";
            }
        }
    }

}
