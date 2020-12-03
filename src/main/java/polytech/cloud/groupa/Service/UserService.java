package polytech.cloud.groupa.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import polytech.cloud.groupa.Model.Position;
import polytech.cloud.groupa.Model.User;
import polytech.cloud.groupa.Repository.PositionRepository;
import polytech.cloud.groupa.Repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PositionRepository repo;

    public UserService(UserRepository repo){
        this.repository = repo;
    }

    public List<User> getAllUsers(){return repository.findAll();}

    public Optional<User> getUserById(long Id){
        return repository.findById(Id);
    }

    public void addUser(User user, Position position){
        this.repository.save(user);
        this.repo.save(position);
    }

    public void deleteUser(long id) {
        this.repo.deleteWithId(getUserById(id).get().getPositionId());
        this.repository.deleteWithId(id);
    }

    public void deleteAllUser(){
        this.repository.deleteAll();
        this.repo.deleteAll();
    }

    public Date transformDate(Date d) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(d.toString());
        return date;
    }

    public void modifyAllUser(List<User> users){
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
        Date date;
        Date dateMax;
        Calendar cal = Calendar.getInstance();
        Calendar calMax = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        date = cal.getTime();
        System.out.println(date.toString());
        calMax.add(Calendar.YEAR, -age+1);
        calMax.add(Calendar.DATE, -1);
        dateMax = calMax.getTime();
        System.out.println(dateMax.toString());
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
    //A Refaire
    /*
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
    }*/

    public void updateUser(User user, long id){
        Optional<User> UserToUpdate = this.repository.findById(id);
        if(UserToUpdate.isPresent()){
            User User = UserToUpdate.get();

            if(User.getBirthDay()!=user.getBirthDay()){
                User.setBirthDay(user.getBirthDay());
            }
            if(User.getFirstName()!=user.getFirstName()){
                User.setFirstName(user.getFirstName());
            }
            if(User.getLastName()!=user.getLastName()){
                User.setLastName(user.getLastName());
            }
            if(User.getPositionId()!=user.getPositionId()){
                User.setPositionId(user.getPositionId());
            }
            this.repository.save(User);
        }

        this.repository.save(user);
    }
}
