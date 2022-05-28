package git.dimitrikvirik.springjs.service;

import git.dimitrikvirik.springjs.model.domain.UserAccount;
import git.dimitrikvirik.springjs.model.dto.UserAccountDTO;
import git.dimitrikvirik.springjs.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAccountRepository userAccountRepository;


    @Transactional
    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Transactional
    public Optional<UserAccount> getByUsername(String username){
      return   userAccountRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<UserAccount> get(long id) {
        return userAccountRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public UserAccount getWithoutOptional(long id) {
        return userAccountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %s not found", id)));
    }



}
