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
    @Query("SELECT u FROM Users u WHERE u.birthDay<?1")
    List<Utilisateur> findWithAgeSup(Date date, @PageableDefault(size = 100) Pageable pageable);

    @Query("SELECT u FROM Users u WHERE u.birthDay >=?1 AND u.birthDay <=?2")
    List<Utilisateur> findWithAgeEq(Date date, Date dateMax, @PageableDefault(size = 100) Pageable pageable);

    @Query("SELECT u FROM Users u WHERE u.lastName = ?1")
    List<Utilisateur> findWithName(String name, @PageableDefault(size = 100) Pageable pageable);

    @Query("SELECT u FROM Users u")
    List<Utilisateur> findNUser(@PageableDefault(size = 100) Pageable pageable);

    @Query("SELECT u FROM Users u WHERE u.id = ?1")
    Optional<Utilisateur> findById(String id);

    @Query("DELETE FROM Users u WHERE u.id = ?1")
    void deleteWithId(String id);

    //@Query("SELECT u1 FROM User u1, User u, WHERE u1.latitude - ?1 <")
    //public List<Utilisateur> findByLatAndLon(int lat,int lon, Pageable pageable);
}
