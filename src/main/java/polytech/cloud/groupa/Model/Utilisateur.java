package polytech.cloud.groupa.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "Users")
public class Utilisateur {
    private String Id;
    private String FirstName;
    private String LastName;
    private Date BirthDate;
    private Long lat;
    private Long lon;


    @Id
    public String getId(){
        return this.Id;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
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

    public Long getLat() {
        return lat;
    }

    public Long getLon() {
        return lon;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }
}
