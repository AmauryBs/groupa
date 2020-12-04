package polytech.cloud.groupa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Position")
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "positionId", nullable = false)
    private long positionId;

    @Basic
    @Column(name = "lat", nullable = false, precision = 8, scale = 8)
    private BigDecimal lat;

    @Basic
    @Column(name = "lon", nullable = false, precision = 8, scale = 8)
    private BigDecimal lon;


    public long getPositionId(){
        return this.positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return String.format("Position [positionId: %d, lat: %s, lon: %s]", positionId, lat, lon);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return positionId == position.positionId &&
                Objects.equals(lat, position.lat) &&
                Objects.equals(lon, position.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, lat, lon);
    }
}
