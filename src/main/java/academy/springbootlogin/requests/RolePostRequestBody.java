package academy.springbootlogin.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePostRequestBody {

    private String username;
    private String password;
    private String authorities;

}