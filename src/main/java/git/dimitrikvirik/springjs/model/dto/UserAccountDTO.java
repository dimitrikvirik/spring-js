package git.dimitrikvirik.springjs.model.dto;

import git.dimitrikvirik.springjs.model.domain.UserAccount;
import lombok.Data;

import javax.persistence.Column;

@Data
public class UserAccountDTO {

    private String email;

    private String username;

    private String firstname;

    private String lastname;

    private String about;

    public static UserAccountDTO from(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setEmail(userAccount.getEmail());
        userAccountDTO.setUsername(userAccount.getUsername());
        userAccountDTO.setFirstname(userAccount.getFirstname());
        userAccountDTO.setLastname(userAccount.getLastname());
        userAccountDTO.setAbout(userAccount.getAbout());
        return userAccountDTO;
    }
}
