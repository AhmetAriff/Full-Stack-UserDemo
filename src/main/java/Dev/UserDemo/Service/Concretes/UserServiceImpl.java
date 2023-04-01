package Dev.UserDemo.Service.Concretes;

import Dev.UserDemo.Dto.AddUserRequest;
import Dev.UserDemo.Dto.UpdateUserRequest;
import Dev.UserDemo.Dto.UserResponse;
import Dev.UserDemo.Entity.User;
import Dev.UserDemo.Repository.UserRepository;
import Dev.UserDemo.Service.Abstract.UserMapper;
import Dev.UserDemo.Service.Abstract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    @Override
    public void addUser(AddUserRequest addUserRequest) {
        User user =   userMapper.addUserRequestToUser(addUserRequest);
        user.setCreatedDate(Instant.now());
        userRepository.save(user);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(updateUserRequest.getUserId())
                .orElseThrow(()->new RuntimeException("user not found"));

        userRepository.save(userMapper.updateUser(user,updateUserRequest));
    }


    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.usersToUserResponses(userRepository.findAll());
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.delete(userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("user not found")));
    }

    @Override
    public UserResponse getUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("user not found"));
        return userMapper.userToUserResponse(user);
    }
}
