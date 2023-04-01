package Dev.UserDemo.Dto;

import lombok.Data;

@Data
public class AddUserRequest {

    private String firstName;

    private String lastName;

    private String userName;

    private String address;

    private String gender;
}
