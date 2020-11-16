package polytech.cloud.groupa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import polytech.cloud.groupa.Model.Utilisateur;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    @Query("SELECT u FROM User u WHERE u.age > ?1")
    public List<Utilisateur> findWithAgeSup(int age);

    @Query("SELECT u FROM User u WHERE u.age = ?1")
    public List<Utilisateur> findWithAgeEq(int age);

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    public List<Utilisateur> findWithName(String name);

    @Query("SELECT u FROM User u)
    public List<Utilisateur> findByLatAndLon(int lat,int lon);
}
