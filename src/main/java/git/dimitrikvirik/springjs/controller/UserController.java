package git.dimitrikvirik.springjs.controller;

import git.dimitrikvirik.springjs.facade.UserFacade;
import git.dimitrikvirik.springjs.model.domain.UserAccount;
import git.dimitrikvirik.springjs.model.dto.UserAccountDTO;
import git.dimitrikvirik.springjs.model.param.UserUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/{username}")
    public ResponseEntity<UserAccountDTO> getUser(@PathVariable String username){
        return new ResponseEntity<>(userFacade.getUser(username), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    @PreAuthorize("@authService.sameUsername(#username)")
    public ResponseEntity<UserAccountDTO> updateUser(@PathVariable String username ,@Valid @RequestBody UserUpdateParam userUpdateParam){
        return new ResponseEntity<>(userFacade.updateUser(username, userUpdateParam), HttpStatus.OK);
    }


}
