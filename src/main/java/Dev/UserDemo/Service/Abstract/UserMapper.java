package Dev.UserDemo.Service.Abstract;


import Dev.UserDemo.Dto.AddUserRequest;
import Dev.UserDemo.Dto.UpdateUserRequest;
import Dev.UserDemo.Dto.UserResponse;
import Dev.UserDemo.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User addUserRequestToUser (AddUserRequest addUserRequest);

    UserResponse userToUserResponse(User user);

    User updateUser(@MappingTarget User user, UpdateUserRequest request);

    List<UserResponse> usersToUserResponses(List<User> users);
}
