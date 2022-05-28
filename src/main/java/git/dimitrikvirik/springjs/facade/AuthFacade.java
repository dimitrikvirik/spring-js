package git.dimitrikvirik.springjs.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import git.dimitrikvirik.springjs.model.domain.UserAccount;
import git.dimitrikvirik.springjs.model.mapper.KeycloakMapper;
import git.dimitrikvirik.springjs.model.param.LoginParam;
import git.dimitrikvirik.springjs.model.param.ReLoginParam;
import git.dimitrikvirik.springjs.model.param.RegisterParam;
import git.dimitrikvirik.springjs.service.AuthService;
import git.dimitrikvirik.springjs.service.KeycloakService;
import git.dimitrikvirik.springjs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthService authService;
    private final UserService userService;
    private final KeycloakService keycloakService;

    public Void register(RegisterParam registerParam) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(registerParam.getEmail());
        userAccount.setUsername(registerParam.getUsername());
        userAccount.setFirstname(registerParam.getFirstname());
        userAccount.setLastname(registerParam.getLastname());
        UserAccount userAccountSaved = userService.save(userAccount);
        UserRepresentation userRepresentation = keycloakService.create(KeycloakMapper.toRepresentation(
                registerParam.getFirstname(),
                registerParam.getLastname(),
                registerParam.getUsername(),
                registerParam.getPassword(),
                userAccountSaved.getId()
        ));

        userAccount.setKeycloakId(userRepresentation.getId());
        userService.save(userAccount);
        return null;
    }


    public String login(LoginParam loginParam) {
        try {
            HttpResponse<String> login = keycloakService.login(loginParam.getUsername(), loginParam.getPassword(), loginParam.getRememberMe());
            if (login.getStatus() == 401) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username or password");
            }
            return login.getBody();
        }
        catch (UnirestException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Server error! please try again or contact the administration!");
        }
    }

    public String reLogin(ReLoginParam reLoginParam) {
        try {
            HttpResponse<String> reLogin = keycloakService.reLogin(reLoginParam.getRefresh_token());
            if (reLogin.getStatus() == 401) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong refresh token ");
            }
            return reLogin.getBody();
        }
        catch (UnirestException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Server error! please try again or contact the administration!");
        }
    }

    public Void logout() {
        authService.logout(authService.getSessionId());
        return null;
    }
}
