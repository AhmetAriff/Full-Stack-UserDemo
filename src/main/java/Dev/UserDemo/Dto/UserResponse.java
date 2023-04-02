package Dev.UserDemo.Dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserResponse {

    private Integer userId;

    private String firstName;

    private String lastName;

    private String userName;

    private String address;

    private String gender;

    private Instant createdDate;

    private String imageFileName;
}
