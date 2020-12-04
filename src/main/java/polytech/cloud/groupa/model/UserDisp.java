package polytech.cloud.groupa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents a User object with all needed fields
 *
 * @author Ewald Janin
 *
 * @see User
 */
@JsonPropertyOrder({ "id", "firstName", "lastName", "birthDay", "position" })
public class UserDisp implements Serializable {

    /**
     * String representation of the id to match project's specs
     */
    private String id;

    /**
     * String representation of the User's firstname
     */
    private String firstName;

    /**
     * String representation of the User's lastname
     */
    private String lastName;

    /**
     * String representation of the User's birthday with pattern set according to project's specs
     */
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date birthDay;

    /**
     * A representation of the User's position without the id to match project's specs
     *
     * @see PositionNoId
     * @see Position
     */
    private PositionNoId position;

    /**
     * Creates a UserDisp using given User's values
     *
     * @param u a User from which values are taken
     *
     * @see User
     */
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
