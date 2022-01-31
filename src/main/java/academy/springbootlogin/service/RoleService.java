package academy.springbootlogin.service;

import academy.springbootlogin.domain.Role;
import academy.springbootlogin.domain.User;
import academy.springbootlogin.mapper.UserMapper;
import academy.springbootlogin.repository.RoleRepository;
import academy.springbootlogin.requests.RolePostRequestBody;
import academy.springbootlogin.requests.UserPostRequestBody;
import academy.springbootlogin.requests.UserPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements UserDetailsService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(roleRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Username not fount"));
    }


    @Transactional
    public Role save(UserPostRequestBody userPostRequestBody){
    return roleRepository.save(UserMapper.INSTANCE.toRolePost(userPostRequestBody));
    }

    public Role findByIdOrThrowBadRequestExceptionRole(long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new BadCredentialsException("User Not Found"));
    }

    public void delete(long id) {
        roleRepository.delete(findByIdOrThrowBadRequestExceptionRole(id));
    }

    public void replace(UserPutRequestBody userPutRequestBody) {
        userPutRequestBody.setPassword(encoder.encode(userPutRequestBody.getPassword()));
        Role savedRole = findByIdOrThrowBadRequestExceptionRole(userPutRequestBody.getId());
        Role role = UserMapper.INSTANCE.toRolePut(userPutRequestBody);
        role.setId(savedRole.getId());
        roleRepository.save(role);
    }



}
