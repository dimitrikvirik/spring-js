package git.dimitrikvirik.springjs.model.dto;

import git.dimitrikvirik.springjs.model.domain.UserAccount;
import lombok.Data;

@Data
public class UserAccountDTO {

    private String username;

    private String email;

    private String profile;

    private String about;

    public static UserAccountDTO from(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
       userAccountDTO.setProfile(userAccount.getProfile());
        userAccountDTO.setEmail(userAccount.getEmail());
        userAccountDTO.setUsername(userAccount.getUsername());
        userAccountDTO.setAbout(userAccount.getAbout());
        return userAccountDTO;
    }
}
