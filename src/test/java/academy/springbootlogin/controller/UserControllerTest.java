package academy.springbootlogin.controller;

import academy.springbootlogin.domain.User;
import academy.springbootlogin.requests.UserPostRequestBody;
import academy.springbootlogin.requests.UserPutRequestBody;
import academy.springbootlogin.service.UserService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    @BeforeEach
    void setUp(){
        PageImpl<User> userPage = new PageImpl<>(
                List.of(UserCreator.createdUserValid())
        );

        BDDMockito.when(userService.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                        .thenReturn(UserCreator.createdUserValid());

        BDDMockito.when(userService.save(ArgumentMatchers.any(UserPostRequestBody.class)))
                .thenReturn(UserCreator.createdUserValid());

        BDDMockito.doNothing().when(
                userService).replace(ArgumentMatchers.any(UserPutRequestBody.class));

        BDDMockito.doNothing().when(
                userService).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Save Return User When SuccesFull")
    void Save_ReturnUser_WhenSuccessFull(){
        User users = userController.insert(
                UserPostRequestBodyCreator.createdUserPostRequestBody()).getBody();
        Assertions.assertThat(users).isNotNull().isEqualTo(UserCreator.createdUserValid());
    }


    @Test
    @DisplayName("Detele Removes User when successful")
    void Delete_RemovesUser_WhenSuccessful(){
        Assertions.assertThatCode(()-> userController.delete(1L))
                .doesNotThrowAnyException();
        ResponseEntity<Void> entity = userController.
                delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


    @Test
    @DisplayName("Replace Update User when successful")
    void Replace_UpdateUser_WhenSuccessful(){
        Assertions.assertThatCode(()-> userController.replace(UserPutRequestBodyCreator.createdUserPutRequestBody()));
        ResponseEntity<Void> entity = userController.
                replace(UserPutRequestBodyCreator.createdUserPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }



}