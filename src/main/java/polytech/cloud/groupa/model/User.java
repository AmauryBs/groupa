package polytech.cloud.groupa.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private long id;

    @Basic
    @Column(name = "firstName", nullable = false, length = 255)
    private String firstName;

    @Basic
    @Column(name = "lastName", nullable = false, length = 255)
    private String lastName;

    @Basic
    @JsonFormat(pattern="MM/dd/yyyy")
    @Column(name = "birthDay", nullable = false)
    private Date birthDay;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "position", referencedColumnName = "positionId", nullable=false)
    private Position position;


    public long getId(){
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

    public void setId(long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format(
                "User [id: %d, firstName: %s, lastName: %s, birthDay: %s, %s]",
                id,
                firstName,
                lastName,
                (birthDay == null) ? null : birthDay.toString(),
                (position == null) ? null : position.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthDay, user.birthDay) &&
                Objects.equals(position, user.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDay, position);
    }
}
