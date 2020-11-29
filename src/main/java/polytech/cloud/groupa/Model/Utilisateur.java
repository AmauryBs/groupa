package polytech.cloud.groupa.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Users", catalog = "")
public class Utilisateur {
    private String Id;
    private String FirstName;
    private String LastName;
    private Date BirthDay;
    private int positionId;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public String getId(){
        return this.Id;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthDay() {
        return BirthDay;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 255)
    public String getFirstName() {
        return FirstName;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 255)
    public String getLastName() {
        return LastName;
    }

    public void setBirthDay(Date birthDate) {
        BirthDay = birthDate;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @Basic
    @Column(name = "positionid", nullable = false, length = 255)
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}
