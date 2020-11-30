package polytech.cloud.groupa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polytech.cloud.groupa.Classes.UserFormatted;
import polytech.cloud.groupa.Model.Position;
import polytech.cloud.groupa.Model.User;
import polytech.cloud.groupa.Service.PositionService;
import polytech.cloud.groupa.Service.UserService;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private PositionService pos;

    public UserController(UserService services){
        this.service = services;
    }

    //@GetMapping("/user")
    //public List<User> getAll(){
    //    List<User> users = service.getAllUsers();
    //    return users;
    //}

    @GetMapping("/user")
    public List<UserFormatted> getAllByPage(@RequestParam(name = "page",defaultValue = "0") int page) throws ParseException {
        List<User> users = service.getNUser(page);
        List<UserFormatted> userFormatteds = new ArrayList<>();
        for(int i=0;i<users.size();i++){
            userFormatteds.add(new UserFormatted(users.get(i),pos.getById(users.get(i).getPositionId()).get()));
            userFormatteds.get(i).setBirthDay((Date) service.transformDate(userFormatteds.get(i).getBirthDay()));
        }
        return userFormatteds;
    }

    @GetMapping(value = "/user/age" , params = "gt")
    public List<UserFormatted> getUserWithAgeSup(@DefaultValue("0") @RequestParam(name = "gt") int gt) throws ParseException {
        List<User> users = service.getUsersWithAgeSup(gt,100);
        List<UserFormatted> userFormatteds = new ArrayList<>();
        for(int i=0;i<users.size();i++){
            userFormatteds.add(new UserFormatted(users.get(i),pos.getById(users.get(i).getPositionId()).get()));
            userFormatteds.get(i).setBirthDay((Date) service.transformDate(userFormatteds.get(i).getBirthDay()));
        }
        return userFormatteds;
    }

    @GetMapping(value = "/user/age", params = "eq")
    public List<UserFormatted> getUserWithAgeEq(@DefaultValue("0") @RequestParam(name = "eq") int eq) throws ParseException {
        List<User> users = service.getUsersWithAgeEq(eq,100);
        List<UserFormatted> userFormatteds = new ArrayList<>();
        for(int i=0;i<users.size();i++){
            userFormatteds.add(new UserFormatted(users.get(i),pos.getById(users.get(i).getPositionId()).get()));
            userFormatteds.get(i).setBirthDay((Date) service.transformDate(userFormatteds.get(i).getBirthDay()));
        }
        return userFormatteds;
    }

    @GetMapping("/user/search")
    public List<UserFormatted> getAllUserByName(@DefaultValue("toto") @RequestParam(name = "term") String term) throws ParseException {
        List<User> users = service.getAllUsersByName(term,100);
        List<UserFormatted> userFormatteds = new ArrayList<>();
        for(int i=0;i<users.size();i++){
            userFormatteds.add(new UserFormatted(users.get(i),pos.getById(users.get(i).getPositionId()).get()));
            userFormatteds.get(i).setBirthDay((Date) service.transformDate(userFormatteds.get(i).getBirthDay()));
        }
        return userFormatteds;
    }

   /* @GetMapping("/user/nearest")
    public List<UserFormatted> getNearestUsers(@RequestParam(value = "lat") float lat, @RequestParam(value = "lon") float lon) throws ParseException {
        List<User> users = service.getNearestUser(lat,lon);
        List<UserFormatted> userFormatteds = new ArrayList<>();
        for(int i=0;i<users.size();i++){
            userFormatteds.add(new UserFormatted(users.get(i),pos.getById(users.get(i).getPositionId()).get()));
            userFormatteds.get(i).setBirthDay((Date) service.transformDate(userFormatteds.get(i).getBirthDay()));
        }
        return userFormatteds;
    }*/

    @GetMapping("/user/{id}")
    public UserFormatted getById(@PathVariable("id") String id) throws ParseException {
        Optional<User> user = service.getUserById(id);
        UserFormatted userFormatted = new UserFormatted(user.get(),pos.getById(user.get().getPositionId()).get());
        userFormatted.setBirthDay((Date) service.transformDate(userFormatted.getBirthDay()));
        return userFormatted;
    }

    @PutMapping("/user")
    public ResponseEntity modifyAllUser(@RequestBody List<User> users, @RequestBody List<Position> positions){
        this.service.modifyAllUser(users);
        this.pos.modifyAllPosition(positions);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user")
    public ResponseEntity addUser(@RequestBody User user, @RequestBody Position position){
        this.service.addUser(user,position);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteAllUser(){
        this.service.deleteAllUser();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id){
        this.service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity modifyUser(@PathVariable("id") String id, @RequestBody User user, @RequestBody Position position){
        this.service.updateUser(user,id);
        this.pos.updatePosition(position,user.getPositionId());
        return ResponseEntity.noContent().build();
    }


}