package academy.springbootlogin.service;

import academy.springbootlogin.domain.User;
import academy.springbootlogin.repository.UserRepository;
import academy.springbootlogin.util.UserCreator;
import academy.springbootlogin.util.UserPostRequestBodyCreator;
import academy.springbootlogin.util.UserPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class UserServiceTest {


    @InjectMocks
    private UserService userService;


    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        PageImpl<User> userPage = new PageImpl<>(
                List.of(UserCreator.createdUserValid())
        );

        BDDMockito.when(userRepository.findAll())
                        .thenReturn(List.of(UserCreator.createdUserValid()));

        BDDMockito.when(userRepository.save(ArgumentMatchers.any(User.class)))
                .thenReturn(UserCreator.createdUserValid());

        BDDMockito.when(userRepository.findById(ArgumentMatchers.anyLong()))
                        .thenReturn(Optional.of(UserCreator.createdUserValid()));

        BDDMockito.doNothing().when(userRepository).delete(ArgumentMatchers.any(User.class));
    }


    @Test
    @DisplayName("Save Return User when SuccessFull")
    void Save_ReturnsUser_WhenSuccessFull(){
        User save = userService.save(
                UserPostRequestBodyCreator.createdUserPostRequestBody());
        Assertions.assertThat(save).isNotNull().isEqualTo(UserCreator.createdUserValid());
    }


    @Test
    void FindByIdOrThrowBadRequestException_ReturnUser_WhenSuccesFull(){
        Long exptedId = UserCreator.createdUserValid().getId();
        User user = userService.findByIdOrThrowBadRequestException(1L);
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isNotNull().isEqualTo(exptedId);
    }

    @Test
    @DisplayName("Delete Removes User when SuccessFull")
    void Delete_RemoveUser_WhenSuccessFull(){
        Assertions.assertThatCode(()-> userService.delete(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Replace Update User When SuccessFull")
    void Replace_UpdateUser_WhenSuccessFull(){
        Assertions.assertThatCode(()->
                userService.replace(UserPutRequestBodyCreator.createdUserPutRequestBody()));
    }


    @Test
    @DisplayName("Listall Return list of User When SuccessFull")
    void ListAll_ReturnList_OfUser_WhenSuccessFull(){
        String expectedUsername = UserCreator.createdUserValid().getUsername();
        List<User> users = userService.findall();
        Assertions.assertThat(users).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(users.get(0).getUsername()).isEqualTo(expectedUsername);
    }






}