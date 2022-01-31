package academy.springbootlogin.service;

import academy.springbootlogin.domain.Role;
import academy.springbootlogin.domain.User;
import academy.springbootlogin.mapper.UserMapper;
import academy.springbootlogin.repository.RoleRepository;
import academy.springbootlogin.repository.UserRepository;
import academy.springbootlogin.requests.RolePostRequestBody;
import academy.springbootlogin.requests.RolePutRequestBody;
import academy.springbootlogin.requests.UserPostRequestBody;
import academy.springbootlogin.requests.UserPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public User save(UserPostRequestBody userPostRequestBody){
        return userRepository.save(UserMapper.INSTANCE.toUserPost(userPostRequestBody));
    }

    public User findByIdOrThrowBadRequestException(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadCredentialsException("User Not Found"));
    }


    public void delete(long id) {
        userRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(UserPutRequestBody userPutRequestBody) {
        userPutRequestBody.setPassword(encoder.encode(userPutRequestBody.getPassword()));
        User savedUser = findByIdOrThrowBadRequestException(userPutRequestBody.getId());
        User user = UserMapper.INSTANCE.toUserPut(userPutRequestBody);
        user.setId(savedUser.getId());
        userRepository.save(user);
    }

    public List<User>findall(){
        return userRepository.findAll();
    }





}
