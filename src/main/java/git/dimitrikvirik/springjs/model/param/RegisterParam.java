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
    private String Username;
    @Max(30)
    private String Firstname;
    @Max(30)
    private String Lastname;

    @ValidEmail
    private String Email;
    @Size(min = 8, max = 20)
    private String Password;
}
