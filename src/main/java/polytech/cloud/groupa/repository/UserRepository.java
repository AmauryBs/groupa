package polytech.cloud.groupa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import polytech.cloud.groupa.model.Position;
import polytech.cloud.groupa.model.User;

import org.springframework.data.domain.Pageable;
import polytech.cloud.groupa.utils.Constants;

import java.util.Date;
import java.util.List;

/**
 * This repository is used to persist and retrieve User objects
 *
 * @author Ewald Janin
 *
 * @see User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves User list of users whose birthDay is before specified date
     *
     * @param dateMax Date the latest birthDay possible
     * @param pageable optional pagination
     *
     * @return List of User objects matching requirements
     *
     * @see User
     * @see Pageable
     */
    List<User> findByBirthDayBefore(Date dateMax, @PageableDefault(size = Constants.USER_PAGE_SIZE) Pageable pageable);

    /**
     * Retrieves User list of users whose birthDay is between specified dates
     *
     * @param dateMin Date the first birthDay possible
     * @param dateMax Date the latest birthDay possible
     * @param pageable optional pagination
     *
     * @return List of User objects matching requirements
     *
     * @see User
     * @see Pageable
     */
    List<User> findByBirthDayBetween(Date dateMin, Date dateMax, @PageableDefault(size = Constants.USER_PAGE_SIZE) Pageable pageable);

    /**
     * Retrieves User list of users whose lastName contains specified name
     *
     * @param name String which lastName must match
     * @param pageable optional pagination
     *
     * @return List of User objects matching requirements
     *
     * @see User
     * @see Pageable
     */
    List<User> findByLastNameContains(String name, @PageableDefault(size = Constants.USER_PAGE_SIZE) Pageable pageable);

    /**
     * Retrieves User list of users whose Position is in the list
     *
     * @param positions List of Position that User must match
     * @param pageable optional pagination
     *
     * @return List of User objects matching requirements
     *
     * @see User
     * @see Position
     * @see Pageable
     */
    @Query(value = "SELECT u.id, u.firstName, u.lastName, u.birthDay, u.position " +
            "FROM User u " +
            "WHERE u.position IN ?1 ORDER BY FIELD(`u`.`position`,?1)", nativeQuery = true)
    List<User> findByPositionIsInAndOrderByPositionInList(List<Position> positions, @PageableDefault(size = Constants.USER_PAGE_SIZE) Pageable pageable);

}
