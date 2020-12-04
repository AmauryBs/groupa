package polytech.cloud.groupa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;
import polytech.cloud.groupa.exceptions.ResourceNotFoundException;
import polytech.cloud.groupa.model.User;
import polytech.cloud.groupa.model.UserDisp;
import polytech.cloud.groupa.service.UserService;
import polytech.cloud.groupa.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a controller mapping the routes for all the requests on the 'User' objects. <br />
 * It uses UserDisp to display User objects according to project's specs.
 *
 * @author Ewald Janin, Felix Martelin
 * @see UserService
 * @see User
 * @see UserDisp
 */
@RestController
@RequestMapping("/")
public class UserController {

    /**
     * Is used to manipulated User objects and interact with repositories
     *
     *
     * @see UserService
     * @see User
     */
    private UserService service;

    /**
     * The constructor of the controller
     *
     * @param services UserService bean autowired by Spring
     *
     * @see UserService
     */
    @Autowired
    public UserController(UserService services){
        this.service = services;
    }



    /*GET REQUESTS*/

    /**
     * This method fetches a User in database using UserService. <br />
     * The URI for this method is <i>'/user/{id}'</i>
     *
     * @param idStr the id of wanted User, this is a PathVariable, at the end of the URI
     * @return a ResponseEntity with either the User and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#getUserById(long)
     * @see ResponseEntity
     * @see User
     */
    @GetMapping("/user/{id}")
    @Cacheable(value = "getById", key = "", unless="#result.getStatusCodeValue() != 200")
    public ResponseEntity getById(@PathVariable("id") String idStr) {
        try {
            long id = Long.valueOf(idStr);
            User user = service.getUserById(id);
            return new ResponseEntity<>(new UserDisp(user), HttpStatus.OK); }
        catch (NumberFormatException e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>("Error: Bad id : " + e.getMessage(), HttpStatus.NOT_FOUND); }
        catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    /**
     * This method fetches a list of User objects in database using UserService. <br />
     * The URI for this method is <i>'/user?page=value'</i>
     *
     * @param page int : optional page number, by default is 0. The size of a page is defined in app constants.
     * @return a ResponseEntity with either the User list and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#getNUser(int)
     * @see ResponseEntity
     * @see User
     * @see Constants#USER_PAGE_SIZE
     */
    @GetMapping("/user")
    @Cacheable(value = "getAllByPage", key = "", unless="#result.getStatusCodeValue() != 200")
    public ResponseEntity getAllByPage(@RequestParam(name = "page", defaultValue = "0") int page) {
        try {
            List<User> users = service.getNUser(page);
            List<UserDisp> us = new ArrayList<>();
            users.forEach(user -> us.add(new UserDisp(user)));
            return new ResponseEntity<>(us, HttpStatus.OK); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    /**
     * This method fetches a list of User objects in database using UserService. <br />
     * The age of the User objects will be greater or equal to parameter <b>gt</b> <br />
     * The URI for this method is <i>'/user/age?gt=value&page=value'</i>
     *
     * @param gt int : representing the minimum age of User objects to look for
     * @param page int : optional page number, by default is 0. The size of a page is defined in app constants.
     * @return a ResponseEntity with either the User list and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#getUsersWithAgeSup(int, int)
     * @see ResponseEntity
     * @see User
     * @see Constants#USER_PAGE_SIZE
     */
    @GetMapping(value = "/user/age" , params = "gt")
    @Cacheable(value = "getUserWithAgeSup", key = "", unless="#result.getStatusCodeValue() != 200")
    public ResponseEntity getUserWithAgeSup(@RequestParam(name = "gt") int gt, @RequestParam(name = "page", defaultValue = "0") int page) {
        try {
            if (gt < 0) { return new ResponseEntity<>("Age cannot be negative", HttpStatus.BAD_REQUEST); }
            if (page < 0) { return new ResponseEntity<>("Page number cannot be negative", HttpStatus.BAD_REQUEST); }
            List<User> users = service.getUsersWithAgeSup(gt, page);
            List<UserDisp> us = new ArrayList<>();
            users.forEach(user -> us.add(new UserDisp(user)));
            return new ResponseEntity<>(us, HttpStatus.OK); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    /**
     * This method fetches a list of User objects in database using UserService. <br />
     * The age of the User objects will be same as parameter <b>eq</b> <br />
     * The URI for this method is <i>'/user/age?eq=value&page=value'</i>
     *
     * @param eq int : representing the age of User objects to look for
     * @param page int : optional page number, by default is 0. The size of a page is defined in app constants.
     * @return a ResponseEntity with either the User list and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#getUsersWithAgeEq(int, int)
     * @see ResponseEntity
     * @see User
     * @see Constants#USER_PAGE_SIZE
     */
    @GetMapping(value = "/user/age", params = "eq")
    @Cacheable(value = "getUserWithAgeEq", key = "", unless="#result.getStatusCodeValue() != 200")
    public ResponseEntity getUserWithAgeEq(@RequestParam(name = "eq") int eq, @RequestParam(name = "page", defaultValue = "0") int page) {
        try {
            if (eq < 0) { return new ResponseEntity<>("Age cannot be negative", HttpStatus.BAD_REQUEST); }
            if (page < 0) { return new ResponseEntity<>("Page number cannot be negative", HttpStatus.BAD_REQUEST); }
            List<User> users = service.getUsersWithAgeEq(eq, page);
            List<UserDisp> us = new ArrayList<>();
            users.forEach(user -> us.add(new UserDisp(user)));
            return new ResponseEntity<>(us, HttpStatus.OK); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    /**
     * This method fetches a list of User objects in database using UserService. <br />
     * The lastName of the User objects will contain parameter <b>term</b> <br />
     * The URI for this method is <i>'/user/search?term=value&page=value'</i>
     *
     * @param term String : representing a part of lastName of User objects to look for
     * @param page int : optional page number, by default is 0. The size of a page is defined in app constants.
     * @return a ResponseEntity with either the User list and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#getAllUsersByName(String, int)
     * @see ResponseEntity
     * @see User
     * @see Constants#USER_PAGE_SIZE
     */
    @GetMapping("/user/search")
    @Cacheable(value = "getAllUserByName", key = "", unless="#result.getStatusCodeValue() != 200")
    public ResponseEntity getAllUserByName(@RequestParam(name = "term") String term, @RequestParam(name = "page", defaultValue = "0") int page) {
        try {
            if (page < 0) { return new ResponseEntity<>("Page number cannot be negative", HttpStatus.BAD_REQUEST); }
            List<User> users = service.getAllUsersByName(term, page);
            List<UserDisp> us = new ArrayList<>();
            users.forEach(user -> us.add(new UserDisp(user)));
            return new ResponseEntity<>(us, HttpStatus.OK); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    /**
     * This method fetches a list of User objects in database using UserService. <br />
     * The returned user objects are ordered by distance to specified position <b>(lat & lon)</b> request parameters. <br />
     * The maximum number of nearby users can be specified using <b>numberMaxUsers</b> request parameter. <br />
     * The URI for this method is <i>'/user/nearest?lat=value&lon=value&number=value'</i>
     *
     * @param lat float : latitude of the position form where to search for nearest User objects
     * @param lon float : longitude of the position form where to search for nearest User objects
     * @param numberMaxUsers int : max number of users to be returned, default value set in app constants.
     * @return a ResponseEntity with either the User list and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#getNearestUsers(float, float, int)
     * @see ResponseEntity
     * @see polytech.cloud.groupa.model.Position
     * @see User
     * @see Constants#NB_MAX_NEARBY_USERS
     */
    @GetMapping("/user/nearest")
    @Cacheable(value = "getNearestUsers", key = "", unless="#result.getStatusCodeValue() != 200")
    public ResponseEntity getNearestUsers(@RequestParam(value = "lat") float lat, @RequestParam(value = "lon") float lon, @RequestParam(value = "number", defaultValue = Constants.NB_MAX_NEARBY_USERS) int numberMaxUsers) {
        try {
            if (numberMaxUsers < 0) { return new ResponseEntity<>("Max number of users cannot be negative", HttpStatus.BAD_REQUEST); }
            List<User> users = service.getNearestUsers(lat, lon, numberMaxUsers);
            List<UserDisp> us = new ArrayList<>();
            users.forEach(user -> us.add(new UserDisp(user)));
            return new ResponseEntity<>(us, HttpStatus.OK);}
        catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }



    /*POST REQUESTS*/

    /**
     * This method creates a User object and persists it in database from the request body.<br />
     * The URI for this method is <i>'/user'</i>
     *
     * @param user the request body, in JSON format, with all needed fields and values
     * @return a ResponseEntity with either the newly created User and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#addUser(User)
     * @see ResponseEntity
     * @see User
     */
    @PostMapping("/user")
    @Caching(evict = {
            @CacheEvict(value = "getAllByPage", allEntries = true),
            @CacheEvict(value = "getUserWithAgeSup", allEntries = true),
            @CacheEvict(value = "getUserWithAgeEq", allEntries = true),
            @CacheEvict(value = "getAllUserByName", allEntries = true),
            @CacheEvict(value = "getNearestUsers", allEntries = true) })
    public ResponseEntity addUser(@RequestBody User user){
        try {
            User u = service.addUser(user);
            return new ResponseEntity<>(new UserDisp(u), HttpStatus.CREATED); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }



    /*PUT REQUESTS*/

    /**
     * This method updates a User object in database from the request body.<br />
     * The URI for this method is <i>'/user/{id}'</i>
     *
     * @param id long : the unique id of the User object to update
     * @param user the request body, in JSON format, with all needed fields and values
     * @return a ResponseEntity with either the newly updated User and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#updateUser(long, User)
     * @see ResponseEntity
     * @see User
     */
    @PutMapping("/user/{id}")
    @Caching(evict = {
            @CacheEvict(value = "getById", key = "#id"),
            @CacheEvict(value = "getAllByPage", allEntries = true),
            @CacheEvict(value = "getUserWithAgeSup", allEntries = true),
            @CacheEvict(value = "getUserWithAgeEq", allEntries = true),
            @CacheEvict(value = "getAllUserByName", allEntries = true),
            @CacheEvict(value = "getNearestUsers", allEntries = true) })
    public ResponseEntity modifyUser(@PathVariable("id") long id, @RequestBody User user){
        try {
            User u = service.updateUser(id, user);
            return new ResponseEntity<>(new UserDisp(u), HttpStatus.OK); }
        catch (ResourceNotFoundException e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); }
        catch (ObjectOptimisticLockingFailureException e) {
            System.err.println("ObjectOptimisticLockingFailureException caught in modifyUser");
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    /**
     * This method replaces all the User objects stored in persistence.<br />
     * The URI for this method is <i>'/user'</i>
     *
     * @param users the request body, in JSON format, array of User objects with all needed fields and values
     * @return a ResponseEntity with either the newly created User list and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#replaceAllUsers(List)
     * @see ResponseEntity
     * @see User
     */
    @PutMapping("/user")
    @Caching(evict = {
            @CacheEvict(value="getById", allEntries = true),
            @CacheEvict(value = "getAllByPage", allEntries = true),
            @CacheEvict(value = "getUserWithAgeSup", allEntries = true),
            @CacheEvict(value = "getUserWithAgeEq", allEntries = true),
            @CacheEvict(value = "getAllUserByName", allEntries = true),
            @CacheEvict(value = "getNearestUsers", allEntries = true) })
    public ResponseEntity replaceAllUsers(@RequestBody List<User> users){
        try {
            List<User> us = service.replaceAllUsers(users);
            List<UserDisp> usdisp = new ArrayList<>();
            users.forEach(user -> usdisp.add(new UserDisp(user)));
            return new ResponseEntity<>(usdisp, HttpStatus.CREATED); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }



    /*DELETE REQUESTS*/

    /**
     * This method deletes all the User objects stored in persistence. <br />
     * The URI for this method is <i>'/user'</i>
     *
     * @return a ResponseEntity with either empty body and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#deleteAllUser()
     * @see ResponseEntity
     * @see User
     */
    @DeleteMapping("/user")
    public ResponseEntity deleteAllUser(){
        try {
            service.deleteAllUser();
            return new ResponseEntity<>(HttpStatus.OK);}
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    /**
     * This method deletes the specified User object stored in persistence. <br />
     * The URI for this method is <i>'/user/{id}'</i>
     *
     * @param idStr the id of the User object to be deleted
     * @return a ResponseEntity with either empty body and a 200 OK status or an error message and corresponding HTTP status.
     *
     * @see UserService#deleteUser(long)
     * @see ResponseEntity
     * @see User
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String idStr){
        try {
            long id = Long.valueOf(idStr);
            service.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK); }
        catch (NumberFormatException e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>("Id malformed : " + idStr + " -- " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
        catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }


}