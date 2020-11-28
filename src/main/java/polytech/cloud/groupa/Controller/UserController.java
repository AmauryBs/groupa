package polytech.cloud.groupa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polytech.cloud.groupa.Model.User;
import polytech.cloud.groupa.Service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService services){
        this.service = services;
    }

    @GetMapping("/user")
    public List<User> getAll(){
        List<User> users = service.getAllUsers();
        return users;
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable("id") int id){
        Optional<User> user = service.getUserById(id);
        return user.orElseGet(User::new);
    }

    @PutMapping("/user")
    public ResponseEntity modifyAllUser(@RequestBody List<User> users){
        this.service.modifyAllUser(users);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user")
    public ResponseEntity addUser(@RequestBody User user){
        this.service.addUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteAllUser(){
        this.service.deleteAllUser();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id){
        this.service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity modifyUser(@PathVariable("id") int id, @RequestBody User user){
        this.service.updateUser(user,id);
        return ResponseEntity.noContent().build();
    }


}
