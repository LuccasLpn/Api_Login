package academy.springbootlogin.util;

import academy.springbootlogin.requests.UserPostRequestBody;

public class UserPostRequestBodyCreator {




    public static UserPostRequestBody createdUserPostRequestBody(){
        return UserPostRequestBody.builder()
                .username("Luccas")
                .password("lu072324")
                .authorities("ROLE_ADMIN")
                .build();
    }
}
