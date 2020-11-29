package polytech.cloud.groupa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polytech.cloud.groupa.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
