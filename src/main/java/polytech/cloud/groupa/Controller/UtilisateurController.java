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

    @GetMapping("/user")
    public List<Utilisateur> getAllByPage(@DefaultValue("0") @RequestParam int page){
        List<Utilisateur> users = service.getAllUsers();
        List<Utilisateur> usersPage = new ArrayList<>();
        if(users.size()>=99+100*page){
            usersPage = users.subList(0+100*page,99+100*page);
        }else {
            usersPage = users.subList(0 + 100 * page, users.size() - 1);
        }
        return usersPage;
    }

    @GetMapping("/user/age")
    public List<Utilisateur> getUserWithAgeSup(@DefaultValue("0") @RequestParam int gt){
        List<Utilisateur> users = service.getUsersWithAgeSup(gt);
    }

    @GetMapping("/user/age")
    public List<Utilisateur> getUserWithAgeEq(@DefaultValue("0") @RequestParam int eq){
        List<Utilisateur> users = service.getUsersWithAgeEq(eq);
    }

    @GetMapping("/user/search")
    public List<Utilisateur> getAllUserByName(@DefaultValue("toto") @RequestParam String term){
        List<Utilisateur> users = service.getAllUsersByName(term);
        return users;
    }

    @GetMapping("/user/nearest/{lat}&{lon}")

    @GetMapping("/user/{id}")
    public Utilisateur getById(@PathVariable("id") int id){
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
    public ResponseEntity deleteUser(@PathVariable("id") int id){
        this.service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity modifyUser(@PathVariable("id") int id, @RequestBody Utilisateur user){
        this.service.updateUser(user,id);
        return ResponseEntity.noContent().build();
    }


}
