package polytech.cloud.groupa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import polytech.cloud.groupa.exceptions.ResourceNotFoundException;
import polytech.cloud.groupa.model.Position;
import polytech.cloud.groupa.model.User;
import polytech.cloud.groupa.repository.PositionRepository;
import polytech.cloud.groupa.repository.UserRepository;
import polytech.cloud.groupa.utils.Constants;

import java.util.*;

@Service
public class UserService {

    private UserRepository userRepo;

    private PositionRepository positionRepo;

    @Autowired
    public UserService(UserRepository userRepo, PositionRepository positionRepo){
        this.userRepo = userRepo; this.positionRepo = positionRepo;
    }


    public User getUserById(long Id) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findById(Id);
        if (user.isPresent()) {
            return user.get(); }
        else {
            throw new ResourceNotFoundException("user", "id", Id);
        }
    }

    public User addUser(User user){
        //user.setPosition(positionRepo.save(user.getPosition()));
        return userRepo.save(user);
    }

    public void deleteUser(long id) throws ResourceNotFoundException {
        User toDelete = getUserById(id);
        Position pos = toDelete.getPosition();
        userRepo.delete(toDelete);
        if (userRepo.countDistinctByPosition(pos) == 0) {
            positionRepo.delete(pos); }
    }

    public void deleteAllUser(){
        this.userRepo.deleteAll();
        this.positionRepo.deleteAll();
    }

    public List<User> replaceAllUsers(List<User> users){
        deleteAllUser();/*
        users.forEach(user -> {
            user.setPosition(positionRepo.save(user.getPosition()));
        });*/
        return userRepo.saveAll(users);
    }

    public List<User> getNUser(int pageNumber){
        return userRepo.findAll(PageRequest.of(pageNumber, Constants.USER_PAGE_SIZE)).getContent();
    }

    public List<User> getUsersWithAgeSup(int age, int pageNumber){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        Date date = cal.getTime();
        return userRepo.findByBirthDayBefore(date, PageRequest.of(pageNumber, Constants.USER_PAGE_SIZE));
    }

    public List<User> getUsersWithAgeEq(int age, int pageNumber){
        Date dateMin; Date dateMax;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        dateMin = cal.getTime();
        //System.out.println(dateMin.toString());
        cal.add(Calendar.YEAR, -age+1);
        cal.add(Calendar.DATE, -1);
        dateMax = cal.getTime();
        //System.out.println(dateMax.toString());
        return userRepo.findByBirthDayBetween(dateMin, dateMax, PageRequest.of(pageNumber, Constants.USER_PAGE_SIZE));
    }

    public List<User> getAllUsersByName(String name , int pageNumber){
        return userRepo.findByLastNameContains(name, PageRequest.of(pageNumber, Constants.USER_PAGE_SIZE));
    }


    public List<User> getNearestUsers(float lat, float lon, int nbMaxUsers) throws ResourceNotFoundException {
        List<Position> nearestPositions = positionRepo.findNearestPosition(lat, lon, nbMaxUsers);
        if (nearestPositions.isEmpty()) { throw new ResourceNotFoundException("position", "any field", "any value"); }
        return userRepo.findByPositionIsIn(nearestPositions, PageRequest.of(0, nbMaxUsers));
    }

    @Transactional
    public User updateUser(long id, User user) throws ResourceNotFoundException {
        User saved = null;
        try {
            Optional<User> toUpdate = userRepo.findById(id);
            if (toUpdate.isPresent()) {
                User oldUser = toUpdate.get();
                Position oldPos = oldUser.getPosition();
                boolean posUpdated = false;
                if ((user.getFirstName() != null) && !("".equals(user.getFirstName()))) {
                    oldUser.setFirstName(user.getFirstName());
                }
                if ((user.getLastName() != null) && !("".equals(user.getLastName()))) {
                    oldUser.setLastName(user.getLastName());
                }
                if (user.getBirthDay() != null) {
                    oldUser.setBirthDay(user.getBirthDay());
                }
                if (user.getPosition() != null && ((!Objects.equals(user.getPosition().getLat(), oldUser.getPosition().getLat())) || (!Objects.equals(user.getPosition().getLon(), oldUser.getPosition().getLon())))) {
                    Position newpos = positionRepo.saveAndFlush(user.getPosition());
                    posUpdated = true;
                    oldUser.setPosition(newpos);
                }
                saved = userRepo.saveAndFlush(oldUser);
                if (posUpdated) {
                    positionRepo.delete(oldPos);
                }
            } else {
                throw new ResourceNotFoundException("user", "id", id);
            }
        }
        catch (ObjectOptimisticLockingFailureException e) {
            System.out.println("Saved entity : " + saved.toString());
            System.err.println(e.getLocalizedMessage()); }
        finally {
            return saved; }
    }

    //TODO: A Refaire
    /*
    public List<User> getNearestUser(float lat, float lon){
        //Pageable limit = PageRequest.of(0,10);
        List<User> users = repository.findAll();
        List<User> user = new ArrayList<>();
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
    */
}
