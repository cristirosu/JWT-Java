package ejb;

/**
 * Created by User on 11/4/2016.
 */
public enum EmailType {

    ACTIVATION_EMAIL("Activation notification");

    private String title;

    private EmailType(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

}
