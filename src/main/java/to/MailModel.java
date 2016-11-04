package to;

import java.io.Serializable;

/**
 * Created by User on 11/4/2016.
 */
public class MailModel implements Serializable {

    private String to;
    private String content;
    private String subject;

    public MailModel() {
    }

    public MailModel(String to, String content, String subject) {
        this.to = to;
        this.content = content;
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "MailModel{" +
                "to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
