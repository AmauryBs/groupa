package polytech.cloud.groupa.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import polytech.cloud.groupa.Model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    @Query("DELETE FROM Position p WHERE p.id = ?1")
    void deleteWithId(int id);
}
