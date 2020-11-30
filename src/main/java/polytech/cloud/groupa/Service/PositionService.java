package polytech.cloud.groupa.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polytech.cloud.groupa.Model.Position;
import polytech.cloud.groupa.Repository.PositionRepository;
import polytech.cloud.groupa.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    @Autowired
    private PositionRepository repository;

    public Optional<Position> getById(int id){
        return repository.findById(id);
    }

    public void modifyAllPosition(List<Position> positions){
        this.repository.deleteAll();
        this.repository.saveAll(positions);
    }

    public void updatePosition(Position pos,int id){
        Optional<Position> positionToUpdate = this.repository.findById(id);
        if(positionToUpdate.isPresent()){
            Position position = positionToUpdate.get();
            if(position.getLat()!=pos.getLat()){
                position.setLat(pos.getLat());
            }
            if(position.getLon()!=pos.getLon()){
                position.setLon(pos.getLon());
            }
            this.repository.save(position);
        }

        this.repository.save(pos);
    }
}
