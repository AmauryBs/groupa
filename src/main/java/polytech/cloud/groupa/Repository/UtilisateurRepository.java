package polytech.cloud.groupa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import polytech.cloud.groupa.Model.Utilisateur;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    @Query("SELECT u FROM user u WHERE u.birthdate > ?1")
    public List<Utilisateur> findWithAgeSup(Date date, @PageableDefault(size = 100) Pageable pageable);

    @Query("SELECT u FROM user u WHERE u.birthdate >= ?1 AND u.birthdate <= ?2")
    public List<Utilisateur> findWithAgeEq(Date date, Date dateMax, Pageable pageable);

    @Query("SELECT u FROM user u WHERE u.firstname = ?1")
    public List<Utilisateur> findWithName(String name, Pageable pageable);

    @Query("SELECT u FROM user u")
    public List<Utilisateur> findNUser(Pageable pageable);

    @Query("SELECT u FROM user u WHERE u.id = ?1")
    public Optional<Utilisateur> findById(String id);

    @Query("DELETE u FROM user u WHERE u.id = ?1")
    public void deleteById(String id);

    //@Query("SELECT u1 FROM User u1, User u, WHERE u1.latitude - ?1 <")
    //public List<Utilisateur> findByLatAndLon(int lat,int lon, Pageable pageable);
}
