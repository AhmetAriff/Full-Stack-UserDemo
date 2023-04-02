package Dev.UserDemo.Dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private Integer userId;

    private String firstName;

    private String lastName;

    private String userName;

    private String address;

    private String gender;

    private String imageFileName;
}
