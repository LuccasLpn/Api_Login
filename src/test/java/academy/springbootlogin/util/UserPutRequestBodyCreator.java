package academy.springbootlogin.util;

import academy.springbootlogin.requests.UserPutRequestBody;

public class UserPutRequestBodyCreator {




    public static UserPutRequestBody createdUserPutRequestBody(){
        return UserPutRequestBody.builder()
                .id(1L)
                .username("Luccas")
                .password("lu072324")
                .authorities("ROLE_ADMIN")
                .build();
    }
}
