package polytech.cloud.groupa.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polytech.cloud.groupa.Model.Utilisateur;
import polytech.cloud.groupa.Repository.UtilisateurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository repository;

    public UtilisateurService(UtilisateurRepository repo){
        this.repository = repo;
    }

    public List<Utilisateur> getAllUsers(){return repository.findAll();}

    public Optional<Utilisateur> getUserById(int Id){
        return repository.findById(Id);
    }

    public void addUser(Utilisateur user){
        this.repository.save(user);
    }

    public void deleteUser(int id) {
        this.repository.deleteById(id);
    }

    public void deleteAllUser(){
        this.repository.deleteAll();
    }

    public void modifyAllUser(List<Utilisateur> users){
        this.repository.deleteAll();
        this.repository.saveAll(users);
    }

    public List<Utilisateur> getUsersWithAgeSup(int age){
        List<Utilisateur> users = repository.findWithAgeSup(age);
        if(users.size()>100){
            return users.subList(0,99);
        }else{
            return users;
        }
    }

    public List<Utilisateur> getUsersWithAgeEq(int age){
        List<Utilisateur> users = repository.findWithAgeEq(age);
        if(users.size()>100){
            return users.subList(0,99);
        }else{
            return users;
        }
    }

    public List<Utilisateur> getAllUsersByName(String name){
        List<Utilisateur> users = repository.findWithName(name);
        if(users.size()>100){
            return users.subList(0,99);
        }else{
            return users;
        }
    }

    public List<Utilisateur> getNearestUser(int lat, int lon){
        List<Utilisateur> users = repository.findByLatAndLon(lat,lon);
        if(users.size()>10){
            return users.subList(0,9);
        }else{
            return users;
        }
    }

    public void updateUser(Utilisateur user, int id){
        Optional<Utilisateur> UserToUpdate = this.repository.findById(id);
        if(UserToUpdate.isPresent()){
            Utilisateur User = UserToUpdate.get();

            if(User.getBirthDate()!=user.getBirthDate()){
                User.setBirthDate(user.getBirthDate());
            }
            if(User.getFirstName()!=user.getFirstName()){
                User.setFirstName(user.getFirstName());
            }
            if(User.getLastName()!=user.getLastName()){
                User.setLastName(user.getLastName());
            }
            if(User.getLat()!=user.getLat()){
                User.setLat(user.getLat());
            }
            if(User.getLon()!=user.getLon()){
                User.setLon(user.getLon());
            }

            this.repository.save(User);
        }

        this.repository.save(user);
    }
}
