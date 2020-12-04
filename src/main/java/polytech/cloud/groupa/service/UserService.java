package polytech.cloud.groupa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import polytech.cloud.groupa.exceptions.ResourceNotFoundException;
import polytech.cloud.groupa.model.Position;
import polytech.cloud.groupa.model.User;
import polytech.cloud.groupa.repository.PositionRepository;
import polytech.cloud.groupa.repository.UserRepository;
import polytech.cloud.groupa.utils.Constants;

import java.util.*;

/**
 * This classed is used to manipulate and persist User objects, using a UserRepository and a PositionRepository
 *
 * @author Ewald Janin, Felix Martelin
 *
 * @see User
 * @see UserRepository
 * @see PositionRepository
 */
@Service
public class UserService {

    /**
     * Used to retrieve and persist User objects
     *
     * @see UserRepository
     * @see User
     */
    private UserRepository userRepo;

    /**
     * Used to retrieve and persist Position objects
     *
     * @see PositionRepository
     * @see Position
     */
    private PositionRepository positionRepo;

    /**
     * Construct a UserService bean using autowired needed repositories beans
     *
     * @param userRepo a UserRepository autowired bean
     * @param positionRepo a PositionRepository autowired bean
     */
    @Autowired
    public UserService(UserRepository userRepo, PositionRepository positionRepo){
        this.userRepo = userRepo; this.positionRepo = positionRepo;
    }

    /**
     * Retrieves User object from database whose id matches specified id
     * @param Id the id User must match
     *
     * @return retrieved User
     *
     * @throws ResourceNotFoundException if the User does not exist
     * @see User
     * @see UserRepository
     */
    public User getUserById(long Id) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findById(Id);
        if (user.isPresent()) {
            return user.get(); }
        else {
            throw new ResourceNotFoundException("user", "id", Id);
        }
    }

    /**
     * Persists and return User
     *
     * @param user User to persists
     *
     * @return saved User with id field filled
     * @see User
     * @see UserRepository
     */
    public User addUser(User user){
        return userRepo.save(user);
    }

    /**
     * Deletes User object from database whose id matches specified id <br />
     * Also deletes the Position object associated with the User.
     *
     * @param id the id User must match
     *
     * @throws ResourceNotFoundException if the User does not exist
     * @see User
     * @see UserRepository
     * @see Position
     * @see PositionRepository
     */
    public void deleteUser(long id) throws ResourceNotFoundException {
        User toDelete = getUserById(id);
        Position pos = toDelete.getPosition();
        userRepo.delete(toDelete);
        positionRepo.delete(pos);
    }

    /**
     * Deletes all existing User objects and Position objects in database
     *
     * @see User
     * @see UserRepository
     * @see Position
     * @see PositionRepository
     */
    public void deleteAllUser(){
        this.userRepo.deleteAll();
        this.positionRepo.deleteAll();
    }

    /**
     * Replaces all existing User objects in database by given User List
     *
     * @param users List of new User objects
     *
     * @return saved user List with id fields filled
     * @see User
     * @see UserRepository
     */
    public List<User> replaceAllUsers(List<User> users){
        deleteAllUser();
        return userRepo.saveAll(users);
    }

    /**
     * Retrieves the page <b>pageNumber</b> of User objects<br />
     * Page size is defined in Constants
     *
     * @param pageNumber the page number to retrieve
     *
     * @return List of User objects retrieved
     *
     * @see Constants#USER_PAGE_SIZE
     * @see User
     * @see UserRepository
     */
    public List<User> getNUser(int pageNumber){
        return userRepo.findAll(PageRequest.of(pageNumber, Constants.USER_PAGE_SIZE)).getContent();
    }

    /**
     * Retrieves the page <b>pageNumber</b> of User objects whose age is more than <b>age</b><br />
     * Page size is defined in Constants.
     *
     * @param age the minimum age of users to retrieve
     * @param pageNumber the page number to retrieve
     *
     * @return List of matching User objects retrieved
     *
     * @see Constants#USER_PAGE_SIZE
     * @see User
     * @see UserRepository
     */
    public List<User> getUsersWithAgeSup(int age, int pageNumber){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        Date date = cal.getTime();
        return userRepo.findByBirthDayBefore(date, PageRequest.of(pageNumber, Constants.USER_PAGE_SIZE));
    }

    /**
     * Retrieves the page <b>pageNumber</b> of User objects whose age is <b>age</b><br />
     * Page size is defined in Constants.
     *
     * @param age the age of users to retrieve
     * @param pageNumber the page number to retrieve
     *
     * @return List of matching User objects retrieved
     *
     * @see Constants#USER_PAGE_SIZE
     * @see User
     * @see UserRepository
     */
    public List<User> getUsersWithAgeEq(int age, int pageNumber){
        Date dateMin; Date dateMax;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        dateMin = cal.getTime();
        cal.add(Calendar.YEAR, -age+1);
        cal.add(Calendar.DATE, -1);
        dateMax = cal.getTime();
        return userRepo.findByBirthDayBetween(dateMin, dateMax, PageRequest.of(pageNumber, Constants.USER_PAGE_SIZE));
    }

    /**
     * Retrieves the page <b>pageNumber</b> of User objects whose lastName contains <b>name</b><br />
     * Page size is defined in Constants.
     *
     * @param name String whose User's lastName must contain
     * @param pageNumber the page number to retrieve
     *
     * @return List of matching User objects retrieved
     *
     * @see Constants#USER_PAGE_SIZE
     * @see User
     * @see UserRepository
     */
    public List<User> getAllUsersByName(String name , int pageNumber){
        return userRepo.findByLastNameContains(name, PageRequest.of(pageNumber, Constants.USER_PAGE_SIZE));
    }

    /**
     * Retrieves the  <b>nbMaxUsers</b> nearest User objects whose lastName contains <b>name</b>
     * Page size is defined in Constants.
     *
     * @param lat float latitude of position from where to get nearest User list
     * @param lon float longitude of position from where to get nearest User list
     * @param nbMaxUsers the maximum number of User objects to retrieve
     *
     * @return List of matching User objects retrieved
     *
     * @see Constants#NB_MAX_NEARBY_USERS
     * @see User
     * @see UserRepository
     * @see Position
     * @see PositionRepository
     */
    public List<User> getNearestUsers(float lat, float lon, int nbMaxUsers) throws ResourceNotFoundException {
        List<Position> nearestPositions = positionRepo.findNearestPosition(lat, lon, nbMaxUsers);
        if (nearestPositions.isEmpty()) { throw new ResourceNotFoundException("position", "any field", "any value"); }
        return userRepo.findByPositionIsInAndOrderByPositionInList(nearestPositions, PageRequest.of(0, nbMaxUsers));
    }

    /**
     * Updates User whose <b>id</b> is given with values carried by given <b>user</b>
     * @param id long id of the User to update
     * @param user User object carrying values to update
     * @return updated User object
     * @throws ResourceNotFoundException if User with specified id does not exists in database
     *
     * @see User
     * @see UserRepository
     */
    public User updateUser(long id, User user) throws ResourceNotFoundException {
        Optional<User> toUpdate = userRepo.findById(id);
        if (toUpdate.isPresent()) {
            User oldUser = toUpdate.get();
            Position oldPos = oldUser.getPosition();
            boolean posUpdated = false;
            if ((user.getFirstName() != null) && !("".equals(user.getFirstName()))) {
                oldUser.setFirstName(user.getFirstName()); }
            if ((user.getLastName() != null) && !("".equals(user.getLastName()))) {
                oldUser.setLastName(user.getLastName()); }
            if (user.getBirthDay() != null) {
                oldUser.setBirthDay(user.getBirthDay()); }
            if (user.getPosition() != null && ((!Objects.equals(user.getPosition().getLat(), oldUser.getPosition().getLat())) || (!Objects.equals(user.getPosition().getLon(), oldUser.getPosition().getLon())))) {
                posUpdated = true; }
            User saved = userRepo.save(oldUser);
            if (posUpdated) {
                positionRepo.delete(oldPos); }
            return saved; }
        else {
            throw new ResourceNotFoundException("user", "id", id); }
    }

}
