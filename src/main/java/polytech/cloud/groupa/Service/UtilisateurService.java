package polytech.cloud.groupa.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import polytech.cloud.groupa.Model.Utilisateur;
import polytech.cloud.groupa.Repository.UtilisateurRepository;

import java.util.*;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository repository;

    public UtilisateurService(UtilisateurRepository repo){
        this.repository = repo;
    }

    public List<Utilisateur> getAllUsers(){return repository.findAll();}

    public Optional<Utilisateur> getUserById(String Id){
        return repository.findById(Id);
    }

    public void addUser(Utilisateur user){
        this.repository.save(user);
    }

    public void deleteUser(String id) {
        this.repository.deleteById(id);
    }

    public void deleteAllUser(){
        this.repository.deleteAll();
    }

    public void modifyAllUser(List<Utilisateur> users){
        this.repository.deleteAll();
        this.repository.saveAll(users);
    }

    public List<Utilisateur> getNUser(int n){
        Pageable limitUser;
        limitUser = PageRequest.of(n, 100);
        List<Utilisateur> users = repository.findNUser(limitUser);
        return users;
    }

    public List<Utilisateur> getUsersWithAgeSup(int age, int limit){
        Pageable limitUser = PageRequest.of(0,limit);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        date = cal.getTime();
        List<Utilisateur> users = repository.findWithAgeSup(date, limitUser);
        return users;
    }

    public List<Utilisateur> getUsersWithAgeEq(int age, int limit){
        Pageable limitUser = PageRequest.of(0,limit);
        Date date = new Date();
        Date dateMax = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        date = cal.getTime();
        cal.add(Calendar.YEAR, age);
        cal.add(Calendar.DATE, -1);
        dateMax = cal.getTime();
        List<Utilisateur> users = repository.findWithAgeEq(date,dateMax,limitUser);
        return users;
    }

    public List<Utilisateur> getAllUsersByName(String name , int limit){
        Pageable limitUser = PageRequest.of(0,limit);
        List<Utilisateur> users = repository.findWithName(name,limitUser);
        return users;
    }

    public float calculateDistance(float lat, float Long, float latuser, float longuser){
        return Math.abs(lat-latuser)+Math.abs(Long - longuser);
    }
    public List<Utilisateur> getNearestUser(float lat, float lon){
        //Pageable limit = PageRequest.of(0,10);
        List<Utilisateur> users = repository.findAll();
        List<Utilisateur> user = new ArrayList<>();
        for(int i=0;i<users.size();i++){
            if(user.size()>10) {
                for (int j = 0; j < user.size(); j++) {
                    if (calculateDistance(lat,lon,users.get(i).getLat(),users.get(i).getLon()) < calculateDistance(lat,lon,user.get(j).getLat(),user.get(j).getLon())){
                        user.remove(j);
                        user.add(users.get(i));
                        j=user.size();
                    }
                }
            }else{
                user.add(users.get(i));
            }
        }
        return users;
    }

    public void updateUser(Utilisateur user, String id){
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
