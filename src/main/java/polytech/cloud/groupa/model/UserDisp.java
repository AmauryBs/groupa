package polytech.cloud.groupa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Date;

@JsonPropertyOrder({ "id", "firstName", "lastName", "birthDay", "position" })
public class UserDisp implements Serializable {


    private String id;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern="MM/dd/yyyy")
    private Date birthDay;

    private PositionNoId position;

    public UserDisp(User u) {
        id = String.valueOf(u.getId());
        firstName = u.getFirstName();
        lastName = u.getLastName();
        birthDay = u.getBirthDay();
        position = new PositionNoId(u.getPosition());
    }

    public String getId(){
        return this.id;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PositionNoId getPosition() {
        return position;
    }

    public void setPosition(PositionNoId position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format(
                "User [id: %s, firstName: %s, lastName: %s, birthDay: %s, %s]",
                id,
                firstName,
                lastName,
                (birthDay == null) ? null : birthDay.toString(),
                (position == null) ? null : position.toString());
    }
}
