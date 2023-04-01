package Dev.UserDemo.Service.Abstract;

import Dev.UserDemo.Dto.AddUserRequest;
import Dev.UserDemo.Dto.UpdateUserRequest;
import Dev.UserDemo.Dto.UserResponse;
import Dev.UserDemo.Entity.User;

import java.util.List;

public interface UserService {

    void addUser(AddUserRequest addUserRequest) ;

    void updateUser(UpdateUserRequest updateUserRequest);

    List<UserResponse> getAllUsers();

    void deleteUser(int userId);

    UserResponse getUser(int userId);
}
