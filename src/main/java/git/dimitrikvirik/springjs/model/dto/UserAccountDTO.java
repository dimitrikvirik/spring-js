package git.dimitrikvirik.springjs.model.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserAccountDTO {

    private String email;

    private String username;

    private String firstname;

    private String lastname;
}
