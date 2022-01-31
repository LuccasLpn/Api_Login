package academy.springbootlogin.repository;

import academy.springbootlogin.domain.Role;
import academy.springbootlogin.util.RoleCreator;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
@DisplayName("Testing For RoleRepository")
class RoleRepositoryTest {


    @Autowired
    private RoleRepository roleRepository;



    @Test
    @DisplayName("Save Persist role when SuccessFull")
    public void Save_PersistRole_WhenSuccessFull(){
        Role role = RoleCreator.createdRoleValid();
        Role savedRole = this.roleRepository.save(role);
        Assertions.assertThat(savedRole).isNotNull();
        Assertions.assertThat(savedRole.getId()).isNotNull();
        Assertions.assertThat(savedRole.getPassword()).isNotNull();
        Assertions.assertThat(savedRole.getUsername()).isNotNull();
        Assertions.assertThat(savedRole.getAuthorities()).isNotNull();
        Assertions.assertThat(savedRole.getId()).isEqualTo(savedRole.getId());
        Assertions.assertThat(savedRole.getPassword()).isEqualTo(savedRole.getPassword());
        Assertions.assertThat(savedRole.getUsername()).isEqualTo(savedRole.getUsername());
        Assertions.assertThat(savedRole.getAuthorities()).isEqualTo(savedRole.getAuthorities());
    }


    @Test
    @DisplayName("Saved Update role when SuccessFull")
    public void Save_UpdateRole_WhenSuccessFull(){
        Role role = RoleCreator.createdRoleValid();
        Role savedRole = this.roleRepository.save(role);
        savedRole.setUsername("Luiz");
        Role updateRole = this.roleRepository.save(savedRole);
        Assertions.assertThat(updateRole).isNotNull();
        Assertions.assertThat(updateRole.getId()).isNotNull();
        Assertions.assertThat(updateRole.getUsername()).isEqualTo(savedRole.getUsername());
    }


    @Test
    @DisplayName("Delete Removes role when SuccessFull")
    public void Delete_RemovesRole_WhenSuccessFull(){
        Role role = RoleCreator.createdRoleValid();
        Role savedRole = this.roleRepository.save(role);
        this.roleRepository.delete(savedRole);
        Optional<Role> roleOptional = this.roleRepository.findById(savedRole.getId());
        Assertions.assertThat(roleOptional.isEmpty());
    }


    @Test
    @DisplayName("FindBy Username role when SuccessFull")
    public void FindBy_UsernameRole_WhenSuccessFull(){
        Role role = RoleCreator.createdRoleValid();
        Role savedRole = this.roleRepository.save(role);
        String username = savedRole.getUsername();
        Role byUsername = this.roleRepository.findByUsername(username);
        Assertions.assertThat(byUsername).isEqualTo(savedRole);
    }








}