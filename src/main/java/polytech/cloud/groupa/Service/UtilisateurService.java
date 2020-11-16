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
