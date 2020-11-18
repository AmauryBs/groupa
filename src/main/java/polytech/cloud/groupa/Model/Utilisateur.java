package polytech.cloud.groupa.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Users", schema = "Projet_cloud", catalog = "")
public class Utilisateur {
    private String Id;
    private String FirstName;
    private String LastName;
    private Date BirthDate;
    private Long lat;
    private Long lon;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public String getId(){
        return this.Id;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthDate() {
        return BirthDate;
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

    @Basic
    @Column(name = "latitude", nullable = true, length = 100)
    public Long getLat() {
        return lat;
    }

    @Basic
    @Column(name = "longitude", nullable = true, length = 100)
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
