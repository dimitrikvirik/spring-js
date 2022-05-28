package git.dimitrikvirik.springjs.controller;


import git.dimitrikvirik.springjs.facade.AuthFacade;
import git.dimitrikvirik.springjs.model.domain.UserAccount;
import git.dimitrikvirik.springjs.model.param.LoginParam;
import git.dimitrikvirik.springjs.model.param.ReLoginParam;
import git.dimitrikvirik.springjs.model.param.RegisterParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthFacade authFacade;



    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterParam registerParam){
      return new ResponseEntity<>(authFacade.register(registerParam),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginParam loginParam){
      return new ResponseEntity<>(authFacade.login(loginParam), HttpStatus.OK);
    }

    @PostMapping("/re-login")
    public ResponseEntity<String> reLogin(@RequestBody @Valid ReLoginParam reLoginParam){
        return new ResponseEntity<>(authFacade.reLogin(reLoginParam), HttpStatus.OK);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(){
        return new ResponseEntity<>(authFacade.logout(), HttpStatus.OK);
    }


}
