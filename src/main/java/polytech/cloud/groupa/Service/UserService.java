package polytech.cloud.groupa.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polytech.cloud.groupa.Model.User;
import polytech.cloud.groupa.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserService(UserRepository repo){
        this.repository = repo;
    }

    public List<User> getAllUsers(){return repository.findAll();}

    public Optional<User> getUserById(int Id){
        return repository.findById(Id);
    }

    public void addUser(User user){
        this.repository.save(user);
    }

    public void deleteUser(int id) {
        this.repository.deleteById(id);
    }

    public void deleteAllUser(){
        this.repository.deleteAll();
    }

    public void modifyAllUser(List<User> users){
        this.repository.deleteAll();
        this.repository.saveAll(users);
    }

    public void updateUser(User user, int id){
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
