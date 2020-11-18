package polytech.cloud.groupa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polytech.cloud.groupa.Model.Utilisateur;
import polytech.cloud.groupa.Service.UtilisateurService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
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

    @GetMapping("/user")
    public List<Utilisateur> getAllByPage(@DefaultValue("0") @RequestParam(value = "page") int page){
        List<Utilisateur> users = service.getNUser(page);
        return users;
    }

    @GetMapping("/user/age")
    public List<Utilisateur> getUserWithAgeSup(@DefaultValue("0") @RequestParam(value = "gt") int gt){
        List<Utilisateur> users = service.getUsersWithAgeSup(gt,100);
        return users;
    }

    @GetMapping("/user/age")
    public List<Utilisateur> getUserWithAgeEq(@DefaultValue("0") @RequestParam(value = "eq") int eq){
        List<Utilisateur> users = service.getUsersWithAgeEq(eq,100);
        return users;
    }

    @GetMapping("/user/search")
    public List<Utilisateur> getAllUserByName(@DefaultValue("toto") @RequestParam(value = "term") String term){
        List<Utilisateur> users = service.getAllUsersByName(term,100);
        return users;
    }

    @GetMapping("/user/nearest")
    public List<Utilisateur> getNearestUsers(@RequestParam(value = "lat") float lat, @RequestParam(value = "lon") float lon){
        List<Utilisateur> users = service.getNearestUser(lat,lon);
        return users;
    }

    @GetMapping("/user/{id}")
    public Utilisateur getById(@PathVariable("id") String id){
        Optional<Utilisateur> user = service.getUserById(id);
        return user.orElseGet(Utilisateur::new);
    }

    @PutMapping("/user")
    public ResponseEntity modifyAllUser(@RequestBody List<Utilisateur> users){
        this.service.modifyAllUser(users);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user")
    public ResponseEntity addUser(@RequestBody Utilisateur user){
        this.service.addUser(user);
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
    public ResponseEntity modifyUser(@PathVariable("id") String id, @RequestBody Utilisateur user){
        this.service.updateUser(user,id);
        return ResponseEntity.noContent().build();
    }


}
