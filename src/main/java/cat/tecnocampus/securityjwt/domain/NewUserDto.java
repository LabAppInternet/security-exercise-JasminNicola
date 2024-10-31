package cat.tecnocampus.securityjwt.domain;
import lombok.Data;

@Data
public class NewUserDto {
    String username;
    String email;
    String password;
    Role role;//todo

}
