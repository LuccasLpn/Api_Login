package academy.springbootlogin.repository;

import academy.springbootlogin.domain.User;
import academy.springbootlogin.util.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
@DisplayName("Testin for User Repository")
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Save Persist User When SuccessFull")
    public void save_PersistUser_WhenSuccessFull(){
        User user = UserCreator.createdUserValid();
        User savedUser = this.userRepository.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isNotNull();
        Assertions.assertThat(savedUser.getUsername()).isNotNull();
        Assertions.assertThat(savedUser.getUsername()).isEqualTo(savedUser.getUsername());
        Assertions.assertThat(savedUser.getPassword()).isNotNull();
        Assertions.assertThat(savedUser.getPassword()).isEqualTo(savedUser.getPassword());
    }

    @Test
    @DisplayName("Save Update User When SuccessFull")
    public void save_UpdateUser_WhenSuccessFull(){
        User user = UserCreator.createdUserValid();
        User savedUser = this.userRepository.save(user);
        savedUser.setUsername("Luiz");
        User update = this.userRepository.save(savedUser);
        Assertions.assertThat(update.getUsername()).isNotNull();
        Assertions.assertThat(update.getId()).isNotNull();
        Assertions.assertThat(update.getUsername()).isEqualTo(savedUser.getUsername());
        Assertions.assertThat(update.getPassword()).isNotNull();
        Assertions.assertThat(update.getPassword()).isEqualTo(savedUser.getPassword());
    }


    @Test
    @DisplayName("Delete Removes User When SuccessFull")
    public void Delete_removeUser_WhenSuccessFull(){
        User user = UserCreator.createdUserValid();
        User savedUser = this.userRepository.save(user);
        this.userRepository.delete(savedUser);
        Optional<User> byId = this.userRepository.findById(savedUser.getId());
        Assertions.assertThat(byId.isEmpty());
    }


    @Test
    @DisplayName("Find By User Name return list")
    public void find_ByUsername_WhenSuccessFull(){
        User user = UserCreator.createdUserValid();
        User savedUser = this.userRepository.save(user);
        String name = savedUser.getUsername();
        User byUsername = this.userRepository.findByUsername(name);
        Assertions.assertThat(byUsername).isNotNull();
    }


    @Test
    @DisplayName("Find By User Name return empty list")
    public void findByUsername_ReturnEmptyList_When(){
        User adadada = this.userRepository.findByUsername("adadada");
        Assertions.assertThat(adadada).isNull();
    }











}