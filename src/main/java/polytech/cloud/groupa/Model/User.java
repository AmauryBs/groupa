package polytech.cloud.groupa.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Integer id;

    @Column(name = "firstName", nullable = false, length = 255)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 255)
    private String lastName;

    @Column(name = "birthDay", nullable = false)
    private Date birthDay;

    @Column(name = "lat", nullable = false)
    private float lat;

    @Column(name = "lon", nullable = false)
    private float lon;


    /*@Id
    public Integer getId(){
        return this.id;
    }*/

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

   /* public void setId(Integer id) {
    }*/

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
