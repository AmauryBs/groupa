package polytech.cloud.groupa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polytech.cloud.groupa.Model.Utilisateur;
import polytech.cloud.groupa.Service.UtilisateurService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UtilisateurController {
    private UtilisateurService service;

    @Autowired
    public UtilisateurController(UtilisateurService services){
        this.service = services;
    }

    @GetMapping("/user")
    public List<Utilisateur> getAll(){
        List<Utilisateur> users = service.getAllUsers();
        return users;
    }

    @GetMapping("/user/{id}")
    public Utilisateur getById(@PathVariable("id") int id){
        Optional<Utilisateur> user = service.getUserById(id);
        return user.orElseGet(Utilisateur::new);
    }

    @PutMapping("/user")
    public void ModifyAllUser(){

    }

    @PostMapping("/user")
    public void AddUsers(){

    }

    @PostMapping("/user")
    public ResponseEntity AddUser(@RequestBody Utilisateur user){
        this.service.addUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user")
    public void DeleteAllUser(){

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity DeleteUser(@PathVariable("id") int id){
        this.service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity ModifyUser(@PathVariable("id") int id, @RequestBody Utilisateur user){
        this.service.updateUser(user,id);
        return ResponseEntity.noContent().build();
    }


}
