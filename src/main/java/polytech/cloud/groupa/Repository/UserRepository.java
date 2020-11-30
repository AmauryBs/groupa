package polytech.cloud.groupa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import polytech.cloud.groupa.Model.User;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.birthDay<?1")
    List<User> findWithAgeSup(Date date, @PageableDefault(size = 100) Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.birthDay >=?1 AND u.birthDay <=?2")
    List<User> findWithAgeEq(Date date, Date dateMax, @PageableDefault(size = 100) Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.lastName = ?1")
    List<User> findWithName(String name, @PageableDefault(size = 100) Pageable pageable);

    @Query("SELECT u FROM User u")
    List<User> findNUser(@PageableDefault(size = 100) Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    Optional<User> findById(String id);

    @Query("DELETE FROM User u WHERE u.id = ?1")
    void deleteWithId(String id);

    //@Query("SELECT u1 FROM User u1, User u, WHERE u1.latitude - ?1 <")
    //public List<User> findByLatAndLon(int lat,int lon, Pageable pageable);
}
