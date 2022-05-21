package git.dimitrikvirik.springjs.repository;

import git.dimitrikvirik.springjs.model.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}