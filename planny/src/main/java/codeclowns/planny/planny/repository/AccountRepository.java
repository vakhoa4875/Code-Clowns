package codeclowns.planny.planny.repository;

import codeclowns.planny.planny.data.entity.AccountE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountE, Integer> {
    AccountE findByEmailOrUsername(String email, String username);

    @Procedure(name = "InsertAccountAndUser")
    void insertAccountAndUser(String username, String password, String email, String sub, Boolean isEnabled, String fullName);

    @Query(value = "Select a.* " +
            "from account a join [user] u " +
            "on a.username = u.username and a.email = u.email " +
            "where a.is_enabled = 1",
            nativeQuery = true)
    List<Object[]> findAllAccounts();
}