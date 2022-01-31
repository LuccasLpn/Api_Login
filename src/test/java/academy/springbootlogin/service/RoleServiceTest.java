package academy.springbootlogin.service;

import academy.springbootlogin.domain.Role;
import academy.springbootlogin.repository.RoleRepository;
import academy.springbootlogin.util.RoleCreator;
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
class RoleServiceTest {


    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;


    @BeforeEach
    void setUp(){

        PageImpl<Role> rolePage = new PageImpl<>(
                List.of(RoleCreator.createdRoleValid())
        );

        BDDMockito.when(roleRepository.save(ArgumentMatchers.any(Role.class)))
                .thenReturn(RoleCreator.createdRoleValid());

        BDDMockito.doNothing().when(roleRepository)
                .delete(ArgumentMatchers.any(Role.class));

        BDDMockito.when(roleRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(RoleCreator.createdRoleValid()));

    }

    @Test
    @DisplayName("Save Return Role When SuccesssFull")
    void Save_ReturnRole_WhenSuccessFull(){
        Role save = roleService.save(
                UserPostRequestBodyCreator.createdUserPostRequestBody());
        Assertions.assertThat(save).isNotNull().isEqualTo(RoleCreator.createdRoleValid());
    }

    @Test
    @DisplayName("FindById Return Role for id When SuccessFull")
    void findByIdOrThrowBadRequestExceptionRole_ReturnRole_WhenSuccessFull(){
        Long exptedId = RoleCreator.createdRoleValid().getId();
        Role role = roleService.findByIdOrThrowBadRequestExceptionRole(1L);
        Assertions.assertThat(role).isNotNull();
        Assertions.assertThat(role.getId()).isNotNull().isEqualTo(exptedId);
    }

    @Test
    @DisplayName("Delete Removes Role When SuccesssFull")
    void Delete_RemovesRole_WhenSuccessFull(){
        Assertions.assertThatCode(() -> roleService.delete(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Replace Update Role When SuccesssFull")
    void Replace_UpdateRole_WhenSuccessFull(){
        Assertions.assertThatCode(()->
                roleService.replace(UserPutRequestBodyCreator.createdUserPutRequestBody()));
    }

}