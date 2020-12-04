package polytech.cloud.groupa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polytech.cloud.groupa.exceptions.ResourceNotFoundException;
import polytech.cloud.groupa.model.User;
import polytech.cloud.groupa.model.UserDisp;
import polytech.cloud.groupa.service.UserService;
import polytech.cloud.groupa.utils.Constants;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService services){
        this.service = services;
    }



    /**GET REQUESTS**/

    @GetMapping("/user/{id}")
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

    @GetMapping("/user")
    public ResponseEntity getAllByPage(@RequestParam(name = "page", defaultValue = "0") int page) {
        try {
            List<User> users = service.getNUser(page);
            List<UserDisp> us = new ArrayList<>();
            users.parallelStream().forEachOrdered(user -> us.add(new UserDisp(user)));
            return new ResponseEntity<>(us, HttpStatus.OK); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @GetMapping(value = "/user/age" , params = "gt")
    public ResponseEntity getUserWithAgeSup(@RequestParam(name = "gt") int gt) {
        try {
            if (gt < 0) { return new ResponseEntity<>("Age cannot be negative", HttpStatus.BAD_REQUEST); }
            List<User> users = service.getUsersWithAgeSup(gt,100);
            List<UserDisp> us = new ArrayList<>();
            users.forEach(user -> us.add(new UserDisp(user)));
            return new ResponseEntity<>(us, HttpStatus.OK); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @GetMapping(value = "/user/age", params = "eq")
    public ResponseEntity getUserWithAgeEq(@RequestParam(name = "eq") int eq) {
        try {
            if (eq < 0) { return new ResponseEntity<>("Age cannot be negative", HttpStatus.BAD_REQUEST); }
            List<User> users = service.getUsersWithAgeEq(eq,100);
            List<UserDisp> us = new ArrayList<>();
            users.forEach(user -> us.add(new UserDisp(user)));
            return new ResponseEntity<>(us, HttpStatus.OK); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @GetMapping("/user/search")
    public ResponseEntity getAllUserByName(@RequestParam(name = "term") String term) {
        try {
            List<User> users = service.getAllUsersByName(term,100);
            List<UserDisp> us = new ArrayList<>();
            users.forEach(user -> us.add(new UserDisp(user)));
            return new ResponseEntity<>(us, HttpStatus.OK); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @GetMapping("/user/nearest")
    public ResponseEntity getNearestUsers(@RequestParam(value = "lat") float lat, @RequestParam(value = "lon") float lon, @RequestParam(value = "number", defaultValue = Constants.NB_MAX_NEARBY_USERS_STR) int numberMaxUsers) {
        try {
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

    /*
    @GetMapping("/user")
    public List<Utilisateur> getAll(){
        List<Utilisateur> users = service.getAllUsers();
        return users;
    }
    */



    /**POST REQUESTS**/

    @PostMapping("/user")
    public ResponseEntity addUser(@RequestBody User user){
        try {
            User u = service.addUser(user);
            return new ResponseEntity<>(new UserDisp(u), HttpStatus.CREATED); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }



    /**PUT REQUESTS**/

    @PutMapping("/user/{id}")
    public ResponseEntity modifyUser(@PathVariable("id") long id, @RequestBody User user){
        try {
            User u = service.updateUser(id, user);
            return new ResponseEntity<>(new UserDisp(u), HttpStatus.OK); }
        catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @PutMapping("/user")
    public ResponseEntity replaceAllUsers(@RequestBody List<User> users){
        try {
            List<User> us = service.replaceAllUsers(users);
            List<UserDisp> usdisp = new ArrayList<>();
            users.parallelStream().forEachOrdered(user -> usdisp.add(new UserDisp(user)));
            return new ResponseEntity<>(usdisp, HttpStatus.CREATED); }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }



    /**DELETE REQUESTS**/

    @DeleteMapping("/user")
    public ResponseEntity deleteAllUser(){
        try {
            service.deleteAllUser();
            return new ResponseEntity<>(HttpStatus.OK);}
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

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