package academy.springbootlogin.repository;


import academy.springbootlogin.domain.Role;
import academy.springbootlogin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByUsername(String username);
}
