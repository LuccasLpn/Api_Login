package academy.springbootlogin.controller;

import academy.springbootlogin.domain.User;
import academy.springbootlogin.requests.UserPostRequestBody;
import academy.springbootlogin.requests.UserPutRequestBody;
import academy.springbootlogin.service.RoleService;
import academy.springbootlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController @RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;


    @PostMapping(path = "/api/save")
    public ResponseEntity<User>insert(@RequestBody @Valid UserPostRequestBody user){
        roleService.save(user);
        user.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/api/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/api/replace")
    public ResponseEntity<Void> replace(@RequestBody UserPutRequestBody userPutRequestBody) {
        userPutRequestBody.setPassword(encoder.encode(userPutRequestBody.getPassword()));
        userService.replace(userPutRequestBody);
        roleService.replace(userPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/api/findall")
    public ResponseEntity<List<User>>findall(){
        return ResponseEntity.ok().body(userService.findall());
    }


}
