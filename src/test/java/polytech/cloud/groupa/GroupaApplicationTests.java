package polytech.cloud.groupa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import polytech.cloud.groupa.service.UserService;
import polytech.cloud.groupa.model.Position;
import polytech.cloud.groupa.model.User;
import polytech.cloud.groupa.repository.PositionRepository;
import polytech.cloud.groupa.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class GroupaApplicationTests {


    @Autowired
    private PositionRepository positionRepository;


    @Test
    @DisplayName("should call delete all function")
    void deleteAllUsersTest() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        PositionRepository positionRepository = Mockito.mock(PositionRepository.class);
        UserService userService = new UserService(userRepository,positionRepository);
        userService.deleteAllUser();
        Mockito.verify(userRepository,Mockito.times(1)).deleteAll();
        Mockito.verify(positionRepository,Mockito.times(1)).deleteAll();
    }

    @Test
    @DisplayName("should create one User")
    void addOneUserTest() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        PositionRepository positionRepository = Mockito.mock(PositionRepository.class);
        UserService userService = new UserService(userRepository,positionRepository);
        userService.addUser(new User("test", "test", new Position(BigDecimal.valueOf(0), BigDecimal.valueOf(0)),new Date(System.currentTimeMillis())));
        Mockito.verify(userRepository,Mockito.times(1)).save(new User("test", "test", new Position(BigDecimal.valueOf(0), BigDecimal.valueOf(0)), new Date(System.currentTimeMillis())));
    }

//    @Test
//    @DisplayName("should delete one user")
//    void deleteOneUserTest() throws Exception {
//        UserRepository userRepository = Mockito.mock(UserRepository.class);
//        PositionRepository positionRepository = Mockito.mock(PositionRepository.class);
//        UserService userService = new UserService(userRepository,positionRepository);
//        User user = userService.addUser(new User("test", "test", new Position(BigDecimal.valueOf(0), BigDecimal.valueOf(0)),new Date(System.currentTimeMillis())));
//        userService.deleteUser(user.getId());
//        Mockito.verify(userRepository,Mockito.times(1)).delete(user);
//        Mockito.verify(positionRepository,Mockito.times(1)).delete(user.getPosition());
//    }

}
