package academy.springbootlogin.util;

import academy.springbootlogin.domain.Role;

public class RoleCreator {

    public static Role createdRoleValid(){
        return Role.builder()
                .id(1L)
                .username("Luccas")
                .password("LU072324")
                .authorities("ROLE_ADMIN")
                .build();

    }

    public static Role createdRoleValidUpdate(){
        return Role.builder()
                .username("Luccas Pereira")
                .password("lu072324")
                .authorities("ROLE_ADMIN")
                .id(1L)
                .build();
    }
}
