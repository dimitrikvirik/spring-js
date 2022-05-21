package git.dimitrikvirik.springjs.model.param;

import git.dimitrikvirik.springjs.validation.ValidEmail;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@Valid
public class RegisterParam {

    @Size(min = 3, max = 30)
    private String username;
    @Max(30)
    private String firstName;
    @Max(30)
    private String lastName;

    @ValidEmail
    private String email;
    @Size(min = 8, max = 20)
    private String password;
}
