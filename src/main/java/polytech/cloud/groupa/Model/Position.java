package polytech.cloud.groupa.Model;

import javax.persistence.*;

@Entity
@Table(name = "Position", catalog = "")
public class Position {

    private String Id;
    private Long lon;
    private Long lat;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public String getId(){
        return this.Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Basic
    @Column(name = "lon", nullable = false, length = 255)
    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    @Basic
    @Column(name = "lat", nullable = false, length = 255)
    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }
}
