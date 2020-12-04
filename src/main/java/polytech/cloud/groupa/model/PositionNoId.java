package polytech.cloud.groupa.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({ "lat", "lon" })
public class PositionNoId {

    private BigDecimal lat;

    private BigDecimal lon;

    public PositionNoId(Position p) {
        this.lat = p.getLat();
        this.lon = p.getLon();
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
        return String.format("Position [lat: %s, lon: %s]", lat, lon);
    }
}
