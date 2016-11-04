package ejb;

import model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 11/4/2016.
 */
@Stateless
public class EmailSenderBean implements EmailSender{

    @Resource(name = "java:jboss/mail/gmail")
    private Session mailSession;

    @Inject
    private EmailHelper emailHelper;

    private Logger logger = Logger.getLogger("EmailSenderBean");

    public void sendEmail(EmailType emailType, User user) {
        logger.info("Trying to send email to ==== " + user.getEmail());
        String emailContent = emailHelper.getEmailContent(emailType, user);

        try {
            Address from = new InternetAddress(mailSession.getProperty("mail.smtp.user"));
            Address to = new InternetAddress(user.getEmail());

            MimeMessage email = new MimeMessage(mailSession);
            email.setFrom(from);
            email.setRecipient(Message.RecipientType.TO, to);
            email.setSubject(emailType.getTitle());
            email.setText(emailContent);

            Transport.send(email);
        } catch (AddressException e) {
            logger.log(Level.WARNING, "Invalid email address " + e);
        } catch (MessagingException e) {
            logger.log(Level.WARNING, "Error sending email message to " + user.getEmail() + " " + e);
            e.printStackTrace();

        }

        logger.info("Mail sent to " + user.getEmail() + " for " + emailContent);
    }

}
