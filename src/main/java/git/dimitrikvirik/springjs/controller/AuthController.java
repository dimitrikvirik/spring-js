package git.dimitrikvirik.springjs.controller;


import git.dimitrikvirik.springjs.model.param.RegisterParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {


    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterParam registerParam){
        return "";
    }

}
