package polytech.cloud.groupa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polytech.cloud.groupa.model.Position;
import polytech.cloud.groupa.repository.PositionRepository;

import java.util.Optional;

@Service
public class PositionService {

    private PositionRepository positionRepo;

    @Autowired
    public PositionService(PositionRepository positionRepo){
        this.positionRepo = positionRepo;
    }

    public Optional<Position> getById(long id){
        return positionRepo.findById(id);
    }

    /*
    public void modifyAllPosition(List<Position> positions){
        this.positionRepo.deleteAll();
        this.positionRepo.saveAll(positions);
    }
    */

    /*
    public void updatePosition(Position pos,int id){
        Optional<Position> positionToUpdate = this.positionRepo.findById(id);
        if(positionToUpdate.isPresent()){
            Position position = positionToUpdate.get();
            if(position.getLat()!=pos.getLat()){
                position.setLat(pos.getLat());
            }
            if(position.getLon()!=pos.getLon()){
                position.setLon(pos.getLon());
            }
            this.positionRepo.save(position);
        }

        this.positionRepo.save(pos);
    }
    */

}
