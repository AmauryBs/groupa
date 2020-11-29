package polytech.cloud.groupa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import polytech.cloud.groupa.Model.Position;
import polytech.cloud.groupa.Model.Utilisateur;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    @Query("DELETE FROM Position p WHERE p.positionId = ?1")
    void deleteWithId(int id);
}
