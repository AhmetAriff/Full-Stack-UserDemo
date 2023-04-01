package Dev.UserDemo.Controller;

import Dev.UserDemo.Dto.AddUserRequest;
import Dev.UserDemo.Dto.UpdateUserRequest;
import Dev.UserDemo.Dto.UserResponse;
import Dev.UserDemo.Service.Abstract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody AddUserRequest addUserRequest)
    {
        userService.addUser(addUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse>getUser(@PathVariable int id){return ResponseEntity.ok(userService.getUser(id));}

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser( @RequestBody UpdateUserRequest updateUserRequest,@PathVariable int id){
        userService.updateUser(updateUserRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
