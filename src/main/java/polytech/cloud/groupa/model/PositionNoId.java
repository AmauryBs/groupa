package polytech.cloud.groupa.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

/**
 * This class represents a Position object with latitude, longitude but without unique id to match project display specifications
 *
 * @author Ewald Janin
 * @see Position
 */
@JsonPropertyOrder({ "lat", "lon" })
public class PositionNoId {

    /**
     * latitude, using BigDecimal to avoid precision loss
     *
     * @see BigDecimal
     */

    private BigDecimal lat;

    /**
     * latitude, using BigDecimal to avoid precision loss
     *
     * @see BigDecimal
     */
    private BigDecimal lon;

    /**
     * Constructor using a Position object to retrieve its latitude and longitude
     *
     * @param p Position from where to retrieve latitude and longitude
     *
     * @see Position
     */
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
