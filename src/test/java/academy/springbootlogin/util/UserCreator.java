package academy.springbootlogin.util;

import academy.springbootlogin.domain.User;

public class UserCreator {


    public static User createdUserValid(){
        return User.builder()
                .id(1L)
                .username("Luccas")
                .password("lu072324")
                .authorities("ROLE_ADMIN")
                .build();
    }

    public static User createdUserValidUpdate(){
        return User.builder()
                .username("Luccas Pereira")
                .password("lu072324")
                .authorities("ROLE_ADMIN")
                .id(1L)
                .build();
    }



}
