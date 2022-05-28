package git.dimitrikvirik.springjs.facade;

import git.dimitrikvirik.springjs.model.domain.UserAccount;
import git.dimitrikvirik.springjs.model.dto.UserAccountDTO;
import git.dimitrikvirik.springjs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public UserAccountDTO getUser(String username){
        UserAccount userAccount = userService.getByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found")
        );
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUsername(userAccount.getUsername());
        userAccountDTO.setFirstname(userAccount.getFirstname());
        userAccountDTO.setLastname(userAccount.getLastname());
        userAccountDTO.setEmail(userAccount.getEmail());
        return  userAccountDTO;
    }

}
