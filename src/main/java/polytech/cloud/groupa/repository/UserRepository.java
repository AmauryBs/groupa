package polytech.cloud.groupa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import polytech.cloud.groupa.model.Position;
import polytech.cloud.groupa.model.User;

import org.springframework.data.domain.Pageable;
import polytech.cloud.groupa.utils.Constants;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByBirthDayBefore(Date dateMax, @PageableDefault(size = Constants.USER_PAGE_SIZE) Pageable pageable);

    List<User> findByBirthDayBetween(Date dateMin, Date dateMax, @PageableDefault(size = Constants.USER_PAGE_SIZE) Pageable pageable);

    List<User> findByLastNameContains(String name, @PageableDefault(size = Constants.USER_PAGE_SIZE) Pageable pageable);

    List<User> findByPositionIsIn(List<Position> positions, @PageableDefault(size = Constants.USER_PAGE_SIZE) Pageable pageable);

    int countDistinctByPosition(Position position);

    /*
    @Query("SELECT u FROM User u WHERE u.birthDay<?1")
    List<User> findWithAgeSup(Date date, @PageableDefault(size = Constants.USER_PAGE_SIZE) Pageable pageable);
    */

    /*
    @Query("SELECT u FROM User u WHERE u.birthDay >=?1 AND u.birthDay <=?2")
    List<User> findWithAgeEq(Date date, Date dateMax, @PageableDefault(size = 100) Pageable pageable);
    */

    /*
    @Query("SELECT u FROM User u WHERE u.lastName = ?1")
    List<User> findWithName(String name, @PageableDefault(size = 100) Pageable pageable);
    */

    /*
    @Query("SELECT u FROM User u")
    List<User> findNUser(@PageableDefault(size = Constants.USER_PAGE_SIZE) Pageable pageable);
    */

    /*
    @Query("DELETE FROM User u WHERE u.id = ?1")
    void deleteWithId(long id);
    */

    /*
    @Query("SELECT u1 FROM User u1, User u, WHERE u1.latitude - ?1 <")
    public List<User> findByLatAndLon(int lat,int lon, Pageable pageable);
    */
}
