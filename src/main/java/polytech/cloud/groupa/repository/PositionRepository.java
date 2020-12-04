package polytech.cloud.groupa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import polytech.cloud.groupa.model.Position;

import java.util.List;

/**
 * This repository is used to persist and retrieve Position objects
 *
 * @author Ewald Janin
 *
 * @see Position
 */
public interface PositionRepository extends JpaRepository<Position, Long> {

    /**
     * This Query retrieves the <b>nbMax</b> nearest position(s) by calculating distance using latitude and longitude
     *
     * @param lat float latitude of position from where to get nearest Position list
     * @param lon float longitude of position from where to get nearest Position list
     * @param nbMax int maximum number of nearest Position to retrieve
     *
     * @return List of nearest positions
     *
     * @see Position
     */
    @Query(value = "SELECT p.PositionID, p.lat, p.lon, SQRT(" +
            "POWER(111.1 * (p.lat - ?1), 2)+\n" +
            "POWER(111.1 * (?2 - p.lon) * COS(p.lat / 57.3), 2)) AS distance " +
            "FROM Position p ORDER BY distance ASC LIMIT ?3", nativeQuery = true)
    List<Position> findNearestPosition(float lat, float lon, int nbMax);
}
