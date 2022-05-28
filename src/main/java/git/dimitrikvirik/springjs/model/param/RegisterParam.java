package git.dimitrikvirik.springjs.model.param;

import git.dimitrikvirik.springjs.validation.ValidEmail;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Valid
public class RegisterParam {

    @Length(min = 3, max = 30)
    @NotBlank
    private String Username;

    @Length(min = 3, max = 30)
    @NotBlank
    private String Firstname;

    @Length(min = 3, max = 30)
    @NotBlank
    private String Lastname;

    @ValidEmail
    @NotBlank
    private String Email;

    @Length(min = 8, max = 20)
    @NotBlank
    private String Password;
}
