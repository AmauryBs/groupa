package polytech.cloud.groupa.Classes;

import polytech.cloud.groupa.Model.Position;
import polytech.cloud.groupa.Model.User;

import java.sql.Date;

public class UserFormatted {
    private String Id;
    private String FirstName;
    private String LastName;
    private Date BirthDay;
    private Position position;

    public UserFormatted(String id, String f, String l, Date b, Position pos){
        this.Id = id;
        this.FirstName = f;
        this.LastName = l;
        this.BirthDay = b;
        this.position = pos;
    }

    public UserFormatted(User u, Position pos){
        this.Id = u.getId();
        this.FirstName = u.getFirstName();
        this.LastName = u.getLastName();
        this.BirthDay = u.getBirthDay();
        this.position = pos;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public Date getBirthDay() {
        return BirthDay;
    }

    public Position getPosition() {
        return position;
    }

    public String getId() {
        return Id;
    }

    public void setBirthDay(Date birthDay) {
        BirthDay = birthDay;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}