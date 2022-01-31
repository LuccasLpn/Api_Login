package academy.springbootlogin.mapper;


import academy.springbootlogin.domain.Role;
import academy.springbootlogin.domain.User;
import academy.springbootlogin.requests.RolePostRequestBody;
import academy.springbootlogin.requests.RolePutRequestBody;
import academy.springbootlogin.requests.UserPostRequestBody;
import academy.springbootlogin.requests.UserPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUserPost(UserPostRequestBody userPostRequestBody);
    public abstract User toUserPut(UserPutRequestBody userPutRequestBody);
    public abstract Role toRolePost(UserPostRequestBody userPostRequestBody);
    public abstract Role toRolePut(UserPutRequestBody userPutRequestBody);

}
